package com.fss.card.hsm;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.HexFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HSMSender {
	public static final Logger logger = LoggerFactory.getLogger(HSMSender.class);

	public String send(String input) {
		logger.info("Inside [HSMSender] send method ");
		
		//System.arraycopy(input, 0, inputData, 2, input.length());
		try (Socket socket = new Socket("10.44.120.42", 4000)) {
			socket.getOutputStream().write(input.getBytes());
			socket.getOutputStream().flush();
			InputStream inStream = socket.getInputStream();
			ByteArrayOutputStream responseBt = new ByteArrayOutputStream();
			do {
				int temp = inStream.read();
				responseBt.write(temp);
			} while (inStream.available() > 0);
			return HexFormat.of().formatHex(responseBt.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[HSMSender] Exception :: {}", e.getMessage());
			return null;
		}
	}
}
