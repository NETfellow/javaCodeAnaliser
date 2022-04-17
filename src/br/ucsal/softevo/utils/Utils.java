package br.ucsal.softevo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static void prinResult(int monthNumber, int numbersOfLinesOfCode, int numbersOfMethods, int numbersOfClasses) {
		String result = "Mês " + monthNumber + ":\nLOC -> " + numbersOfLinesOfCode + "\nMetodos totais -> " + numbersOfMethods
				+ "\nClasses -> " + numbersOfClasses + "\n\n";
		System.out.println(result);
	}
	
	public static List<String> setupFileNameList() {
		final List<String> FILE_NAMES = new ArrayList<String>();
		FILE_NAMES.add("DispatchQueue.txt");
		FILE_NAMES.add("FileLoader.txt");
		FILE_NAMES.add("FileLog.txt");
		FILE_NAMES.add("FileUploadOperation.txt");
		FILE_NAMES.add("UserConfig.txt");
		FILE_NAMES.add("Utilities.txt");
		return FILE_NAMES;
	}
	
	public static String getPath(int monthNumber, String fileName) {
		return "https://raw.githubusercontent.com/estermabel/evs-dataset-codigo-fonte/main/Dataset/" + monthNumber + "/"
				+ fileName;
	}
	
	public static Boolean doesLineMatchesRegex(String line, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(line);
		return matcher.find();
	}
	
	public static BufferedReader getUrlContentReader(String path) {
		URL url;
		URLConnection urlConnection;
		BufferedReader bufferedReader = null;
		try {
			url = new URL(path);
			urlConnection = url.openConnection();
			bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferedReader;
	}

	public static Boolean checkComments(String line) {
		String singleComment = "//";
		String miltiLineCommentBody1 = " *";
		String miltiLineCommentBody2 = "*";
		String multiLineCommentStart = "/*";
		String multiLineCommentEnd = "*/";

		return ((line.contains(singleComment) || line.startsWith(miltiLineCommentBody1)
				|| line.startsWith(miltiLineCommentBody2) || line.contains(multiLineCommentStart)
				|| line.contains(multiLineCommentEnd) || line.isEmpty()));
	}
}
