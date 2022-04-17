package br.ucsal.softevo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ucsal.softevo.buffer.FileWritterCsv;
import br.ucsal.softevo.model.AnalysisData;
import br.ucsal.softevo.prediction.DataPrediction;
import br.ucsal.softevo.utils.Utils;

public class Main {
	final public static String LINES_OF_CODE_REGEX = "^/\\\\b([a-z0-9]+)\\\\b(?<!//|\\\\|\\\\*?|\\\\*)";
	final public static String CLASS_REGEX = "class";
	final public static String METHOD_REGEX = "(?s)\\s*(public|private|protected).*\\s+\\w+?\\(\\s*\\).*";
	final public static String CONSTRUCTOR_REGEX = "(private|protected|internal|public) [A-Za-z0-9_]+[(]";
	final public static String DUPLICATED_METHOD_REGEX = "(\\(+?)([^\\=;]*?)(\\{+?)";
	final public static String OPENING_CHAR = "{";
	final public static String CLOSING_CHAR = "}";
	
	static List<AnalysisData> resultSet = new ArrayList<AnalysisData>();

	public static int numbersOfClasses = 0;
	public static int numbersOfLinesOfCode = 0;
	public static int numbersOfMethods = 0;
	public static int numbersOfSuperClass = 0;
	public static int numbersOfSuperMethods = 0;
	
	public static int openBracketCount = 0;
	public static int closedBracketCount = 0;
	
	public static int openBracketClassCount = 0;
	public static int closedBracketClassCount = 0;
	
	public static int methodLineCount = 0;
	public static int classLineCount = 0;
	
	public static void main(String[] args) throws Exception {
		getInfo();
	}

	public static void getInfo() throws Exception {
		int maxMonth = 27;
		for (int monthNumber = 1; monthNumber <= maxMonth; monthNumber++) {
			for (String file : Utils.setupFileNameList()) {
				getCodeAnalysisResult(Utils.getUrlContentReader(Utils.getPath(monthNumber, file)), monthNumber);
			}
			resultSet.add(new AnalysisData(monthNumber,numbersOfClasses, numbersOfLinesOfCode, numbersOfMethods, numbersOfSuperClass, numbersOfSuperMethods));
			
			// Showing results and saving them into a CSV File
			Utils.prinResult(monthNumber, numbersOfLinesOfCode, numbersOfMethods, numbersOfClasses);
			FileWritterCsv.writeCsv(resultSet, "medicaoSemPrevisao");
			
			// Reseting count
			numbersOfClasses = 0;
			numbersOfLinesOfCode = 0;
			numbersOfMethods = 0;
			numbersOfSuperClass = 0;
			numbersOfSuperMethods = 0;
		}
		DataPrediction.doPrediction(resultSet);
	}

	public static void getCodeAnalysisResult(BufferedReader bufferedReader, Integer monthNumber) throws IOException {
		try {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (!Utils.checkComments(line)) {
					if (!Utils.doesLineMatchesRegex(line, LINES_OF_CODE_REGEX)) {
						numbersOfLinesOfCode++;
					}
				}
				if (Utils.doesLineMatchesRegex(line, METHOD_REGEX)) {
					numbersOfMethods++;
				}
				if (line.contains(CLASS_REGEX)) {
					numbersOfClasses++;
				}	
				numbersOfSuperClass += evaluateClassLine(line);
				numbersOfSuperMethods += evaluateMethod(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
	}
	
	public static int evaluateClassLine(String lineToBeEvaluated) {
		Pattern openBracketPattern = Pattern.compile("[{]");
		Matcher openBracketMatcher = openBracketPattern.matcher(lineToBeEvaluated);
		Pattern closedBracketPattern = Pattern.compile("[}]");
        Matcher closeBracketMatcher = closedBracketPattern.matcher(lineToBeEvaluated);
        Pattern classPattern = Pattern.compile(CLASS_REGEX);
        Matcher classMatcher = classPattern.matcher(lineToBeEvaluated);
        Boolean isClassMethod = false;
        
        if (classMatcher.find() || openBracketClassCount > 0) { 
        	isClassMethod = true;
        }
        if (isClassMethod) {
        	classLineCount++;
        }
        if (openBracketMatcher.find() && !classMatcher.find()) {
        	openBracketClassCount++;
        }
        if (closeBracketMatcher.find()) {
        	closedBracketClassCount++;
        }
        if (openBracketClassCount == closedBracketClassCount) {
        	isClassMethod = false;
        }
        if (!isClassMethod) {
        	openBracketClassCount = 0;
        	closedBracketClassCount = 0;
        	classLineCount = 0;
        }
        if (classLineCount == 801) {
        	return 1;
        }
        return 0;
	}

	public static int evaluateMethod(String lineToBeEvaluated) {
		Pattern methodPattern = Pattern.compile(METHOD_REGEX);
		Matcher methodMatcher = methodPattern.matcher(lineToBeEvaluated);
		Pattern openBracketPattern = Pattern.compile("[{]");
		Matcher openBracketMatcher = openBracketPattern.matcher(lineToBeEvaluated);
		Pattern closedBracketPattern = Pattern.compile("[}]");
        Matcher closeBracketMatcher = closedBracketPattern.matcher(lineToBeEvaluated);
        Pattern classPattern = Pattern.compile(CLASS_REGEX);
        Matcher classMatcher = classPattern.matcher(lineToBeEvaluated);
        Boolean isGodMethod = false;
        
        if (methodMatcher.find() || openBracketCount > 0) { 
        	isGodMethod = true;
        }
        if (isGodMethod) {
        	methodLineCount++;
        }
        if (openBracketMatcher.find() && !classMatcher.find()) {
        	openBracketCount++;
        }
        if (closeBracketMatcher.find()) {
        	closedBracketCount++;
        }
        if (openBracketCount == closedBracketCount) {
        	isGodMethod = false;
        }
        if (!isGodMethod) {
        	openBracketCount = 0;
        	closedBracketCount = 0;
        	methodLineCount = 0;
        }
        if (methodLineCount == 128) {
        	return 1;
        }
        return 0;
	}
}