package com.hhu.enums;

interface A extends Cloneable {
	
}

public class Test {
	
	static int g = 8;

	public static void main(String[] args) {
		int code = FaultDiagnosisEnums.FILTERPRESSURE_CONTINUERISE.getCode();
		String info = FaultDiagnosisEnums.FILTERPRESSURE_CONTINUERISE.getFaultTypes();
		String cause = FaultDiagnosisEnums.FILTERPRESSURE_CONTINUERISE.getFaultCause();
		System.out.println(code + ":" + info + "--" + cause);
	}

}
