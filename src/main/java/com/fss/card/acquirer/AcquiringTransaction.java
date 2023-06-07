package com.fss.card.acquirer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HexFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.util.iso8583.EncoderDecoder;
import org.util.iso8583.ISO8583Loggging;
import org.util.iso8583.ISO8583Message;
import org.util.iso8583.api.ISOFormat;

import com.fss.card.acquirer.format.IntegraFormat;
import com.fss.card.acquirer.format.TangoFormat;
import com.fss.card.entity.TransactionHistory;
import com.fss.card.hsm.HsmHandler;
import com.fss.card.repository.TransactionRepository;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AcquiringTransaction implements Runnable {
	public static final Logger logger = LoggerFactory.getLogger(AcquiringTransaction.class);

	@Value("${tangoIp}")
	private String tangoIp;

	@Value("${tangoPort}")
	private Integer tangoPort;

	@Value("${sourceKey}")
	public String sourceKey;

	@Value("${targetKey}")
	public String targetKey;

	private final Socket socket;

	public AcquiringTransaction(Socket socket) {
		this.socket = socket;
	}

	private static final ISOFormat tangoFormat = TangoFormat.getInstance();
	private static final ISOFormat integraFormat = IntegraFormat.getInstance();

	@Autowired
	private TransactionRepository repository;

	@Override
	public void run() {
		try {
			EncoderDecoder.log = true;
			byte[] isoByteArray = EncoderDecoder.readNextMessageBytes(integraFormat, socket.getInputStream());
			final ISO8583Message request = EncoderDecoder.decode(integraFormat, isoByteArray);
			logger.info("isoByteArray :: {}", HexFormat.of().formatHex(isoByteArray));
			logger.info("IntegraRequest :: {}", ISO8583Loggging.log(request));
			final ISO8583Message tangoRequest = new ISO8583Message();
			tangoRequest.setNetHeader("ISO016000050");
			tangoRequest.put(000, "0200");
			tangoRequest.put(002, request.get(002));
			tangoRequest.put(003, request.get(003));
			tangoRequest.put(004, request.get(004));
			tangoRequest.put(007, request.get(007));
			tangoRequest.put(11, request.get(11));
			tangoRequest.put(12, request.get(12));
			tangoRequest.put(13, request.get(13));
			String track2Data = request.get(35);
			String expirationDate = null;
			if (track2Data.contains("=") && track2Data.indexOf("=") > -1) {
				track2Data = track2Data.substring(track2Data.indexOf("=") + "=".length());
				expirationDate = track2Data.substring(0, 4);
			} else if (track2Data.contains("d") && track2Data.indexOf("d") > -1) {
				track2Data = track2Data.substring(track2Data.indexOf("d") + "d".length());
				expirationDate = track2Data.substring(0, 4);
			} else if (track2Data.contains("D") && track2Data.indexOf("D") > -1) {
				track2Data = track2Data.substring(track2Data.indexOf("D") + "D".length());
				expirationDate = track2Data.substring(0, 4);
			}
			tangoRequest.put(14, expirationDate);
			tangoRequest.put(17, request.get(17));
			tangoRequest.put(18, request.get(18));
			// tangoRequest.put(19, request.get(19));
			tangoRequest.put(22, request.get(22));
			tangoRequest.put(23, request.get(23));
			tangoRequest.put(25, request.get(25));
			tangoRequest.put(32, request.get(32));
			tangoRequest.put(35, request.get(35));
			tangoRequest.put(37, request.get(37));
			tangoRequest.put(40, request.get(40));
			tangoRequest.put(41, request.get(41));
			tangoRequest.put(42, request.get(42));
			tangoRequest.put(43, request.get(43));
			tangoRequest.put(48, "00000000000000000000000B40000000000000000000");
			tangoRequest.put(49, request.get(49));
			tangoRequest.put(55, request.get(55));
			tangoRequest.put(60, "KVFIKVFI+000");
			tangoRequest.put(61, request.get(61));
			String pinData = request.get(52);
			String cardNo = request.get(002);
			String hsmData = HsmHandler.translatePinBlock(pinData, sourceKey, cardNo, targetKey);
			logger.info("HSM output data :: {}", hsmData);
			try {
				if (hsmData != null) {
					if (hsmData.substring(6, 8).equalsIgnoreCase("00")) {
						tangoRequest.put(52, hsmData.substring(8).toUpperCase());
					} else {
						request.put(000, "0210");
						request.put(38, "000000");
						request.put(39, "96");
						byte[] integraBytes = EncoderDecoder.encode(integraFormat, request);
						OutputStream output = socket.getOutputStream();
						output.write(integraBytes);
						output.flush();
					}
				}
			} catch (Exception e) {
				logger.error("Exception inside AcquiringTransaction : hsmData is null :: {}", e.getMessage());
				e.printStackTrace();
			}

			logger.info("TangoRequest :: " + ISO8583Loggging.log(tangoRequest));
			logger.info("tangoIp :: {}", tangoIp + " tangoPort :: {}", tangoPort);

			Socket socketNew = new Socket(tangoIp, tangoPort);
			InputStream in = socketNew.getInputStream();
			OutputStream out = socketNew.getOutputStream();
			byte[] bt = EncoderDecoder.encode(tangoFormat, tangoRequest);
			out.write(bt);
			out.flush();

			byte[] newBt = EncoderDecoder.readNextMessageBytes(tangoFormat, in);
			newBt = Arrays.copyOfRange(newBt, 0, newBt.length - 1);
			final ISO8583Message tangoResponse = EncoderDecoder.decode(tangoFormat, newBt);
			logger.info("TangoResponse :: {}", ISO8583Loggging.log(tangoResponse));

			// Response for Integra
			final ISO8583Message integraResponse = new ISO8583Message();

			integraResponse.put(000, tangoResponse.get(000));
			integraResponse.put(001, tangoResponse.get(001));
			integraResponse.put(002, tangoResponse.get(002));
			integraResponse.put(003, tangoResponse.get(003));
			integraResponse.put(004, tangoResponse.get(004));
			integraResponse.put(007, tangoResponse.get(007));
			integraResponse.put(011, tangoResponse.get(011));
			integraResponse.put(012, tangoResponse.get(012));
			integraResponse.put(013, tangoResponse.get(013));
			integraResponse.put(18, tangoResponse.get(18));
			integraResponse.put(22, tangoResponse.get(22));
			integraResponse.put(25, tangoResponse.get(25));
			integraResponse.put(32, tangoResponse.get(32));
			integraResponse.put(37, tangoResponse.get(37));
			integraResponse.put(38, tangoResponse.get(38));
			integraResponse.put(39, tangoResponse.get(39));
			integraResponse.put(44, tangoResponse.get(44));
			integraResponse.put(55, tangoResponse.get(55));
			integraResponse.put(100, tangoResponse.get(100));
			integraResponse.put(102, tangoResponse.get(102));
			logger.info("IntegraResponse :: " + ISO8583Loggging.log(integraResponse));
			byte[] integraBytes = EncoderDecoder.encode(integraFormat, integraResponse);
			OutputStream output = socket.getOutputStream();
			output.write(integraBytes);
			output.flush();

			saveTransaction(tangoRequest, tangoResponse);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception inside [AcquiringTransaction:run] :: {}", e.getMessage());
		}
	}

	private void saveTransaction(ISO8583Message tangoRequest, ISO8583Message tangoResponse) {
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setMti("0200");
		transactionHistory.setCardNo(tangoRequest.get(002));
		transactionHistory.setProcessingCode(tangoRequest.get(003));
		transactionHistory.setTransactionAmount(tangoRequest.get(004));
		transactionHistory.setTransactionDateTime(tangoRequest.get(007));
		transactionHistory.setStan(tangoRequest.get(11));
		transactionHistory.setLocalTransactionTime(tangoRequest.get(12));
		transactionHistory.setLocalTransactionDate(tangoRequest.get(13));
		transactionHistory.setExpirationDate(tangoRequest.get(14));
		transactionHistory.setBusinessDate(tangoRequest.get(17));
		transactionHistory.setMerchantCatCode(tangoRequest.get(18));
		transactionHistory.setCountryCode("");
		transactionHistory.setPointOfServiceEntryMode(tangoRequest.get(22));
		transactionHistory.setAppPanSeqNo(tangoRequest.get(23));
		transactionHistory.setPointOfServiceConditionCode(tangoRequest.get(25));
		transactionHistory.setAcquiringInstitutionIdentificationCode(tangoRequest.get(32));
		transactionHistory.setTrack2Data(tangoRequest.get(35));
		transactionHistory.setRetrievalRefNo(tangoRequest.get(37));
		transactionHistory.setServiceRestrictionCode(tangoRequest.get(40));
		transactionHistory.setTerminalName(tangoRequest.get(41));
		transactionHistory.setTerminalCode(tangoRequest.get(42));
		transactionHistory.setLocation(tangoRequest.get(43));
		transactionHistory.setAdditionalData(tangoRequest.get(48));
		transactionHistory.setCurrencyCode(tangoRequest.get(49));
		transactionHistory.setPersonalIdentificationNoData(tangoRequest.get(52));
		transactionHistory.setEmvChipData(tangoRequest.get(55));
		transactionHistory.setPrivate1(tangoRequest.get(61));
		transactionHistory.setPrivate2(tangoRequest.get(62));
		transactionHistory.setResMTI("0210");
		transactionHistory.setAuthIdentificationResponse(tangoResponse.get(38) != null ? tangoResponse.get(38) : "");
		transactionHistory.setResponseCode(tangoResponse.get(39) != null ? tangoResponse.get(39) : "");
		if (transactionHistory.getResponseCode() != null
				&& transactionHistory.getResponseCode().equalsIgnoreCase("00")) {
			transactionHistory.setStatus("Success");
		} else {
			transactionHistory.setStatus("Failed");
		}
		Date date = new Date();
		transactionHistory.setCreatedOn(new Timestamp(date.getTime()));
		transactionHistory.setModifiedOn(new Timestamp(date.getTime()));
		transactionHistory.setCreatedBy("*");
		transactionHistory.setModifiedBy("*");
		transactionHistory.setDeleteFlag("F");
		repository.save(transactionHistory);
	}
}
