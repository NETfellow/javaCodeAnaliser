package br.ucsal.softevo.model;

public class AnalysisData {
	private int monthNumber;
	private int numbersOfClasses;
	private int numbersOfLinesOfCode;
	private int numbersOfMethods;
	private int numbersOfSuperClass;
	private int numbersOfSuperMethods;

	public AnalysisData(int monthNumber, int numbersOfClasses, int numbersOfLinesOfCode, int numbersOfMethods,
			int numbersOfSuperClass, int numbersOfSuperMethods) {
		super();
		this.monthNumber = monthNumber;
		this.numbersOfClasses = numbersOfClasses;
		this.numbersOfLinesOfCode = numbersOfLinesOfCode;
		this.numbersOfMethods = numbersOfMethods;
		this.numbersOfSuperClass = numbersOfSuperClass;
		this.numbersOfSuperMethods = numbersOfSuperMethods;
	}
	
	public int getMonthNumber() {
		return monthNumber;
	}
	
	public void setMonthNumber(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	public int getNumbersOfClasses() {
		return numbersOfClasses;
	}
	
	public void setNumbersOfClasses(int numbersOfClasses) {
		this.numbersOfClasses = numbersOfClasses;
	}
	
	public int getNumbersOfLinesOfCode() {
		return numbersOfLinesOfCode;
	}
	
	public void setNumbersOfLinesOfCode(int numbersOfLinesOfCode) {
		this.numbersOfLinesOfCode = numbersOfLinesOfCode;
	}
	
	public int getNumbersOfMethods() {
		return numbersOfMethods;
	}
	
	public void setNumbersOfMethods(int numbersOfMethods) {
		this.numbersOfMethods = numbersOfMethods;
	}
	
	public int getNumbersOfSuperClass() {
		return numbersOfSuperClass;
	}
	
	public void setNumbersOfSuperClass(int numbersOfSuperClass) {
		this.numbersOfSuperClass = numbersOfSuperClass;
	}
	
	public int getNumbersOfSuperMethods() {
		return numbersOfSuperMethods;
	}
	
	public void setNumbersOfSuperMethods(int numbersOfSuperMethods) {
		this.numbersOfSuperMethods = numbersOfSuperMethods;
	}

	@Override
	public String toString() {
		return  monthNumber + "," + numbersOfLinesOfCode + "," + numbersOfClasses
				+ "," + numbersOfMethods + "," + numbersOfSuperClass
				+ "," + numbersOfSuperMethods + "\n";
	}
}
