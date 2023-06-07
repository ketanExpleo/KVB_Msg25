package com.fss.card.hsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HsmHandler {
	public static final Logger logger = LoggerFactory.getLogger(HsmHandler.class);
	
	public static String translatePinBlock(String pinData, String sourceKey, String cardNo, String targetKey) {
		String translatePinData = "EE060200"+pinData+sourceKey+"01"+getPAN12(cardNo)+"01"+targetKey;
		logger.info("[HsmHandler] translatePinData :: {}" , translatePinData);
		HSMSender hsmSender = new HSMSender();
		String output = hsmSender.send(translatePinData);
		//String outputData = HexFormat.of().formatHex(output);
		logger.info("[HsmHandler] outputData :: {}" , output);
		return output;
	}
	
	public static String getPAN12(String input) {
		logger.info("getPAN12 :: {}" , input.substring(input.length()-13, input.length()-1));
		return input.substring(input.length()-13, input.length()-1);
	}
}
