package com.fss.card.test;

public class Test1 {
	public static void main(String[] args) {
		String track2Data = "6081200300002529=25106204920000000000";
		String expirationDate = null;
		track2Data = track2Data.substring(track2Data.indexOf("=") + "=".length());
		expirationDate = track2Data.substring(0, 4);
		System.out.println(expirationDate);
	}
}
