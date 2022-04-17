package br.ucsal.softevo.buffer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

import br.ucsal.softevo.model.AnalysisData;

public class FileWritterCsv {
	public static FileWriter outputFile;
	
	public static void writeCsv(List<AnalysisData> resultSet, String fileName) throws IOException {
		File file = new File(System.getProperty("user.dir") + "/" + fileName + ".csv");
		if (!file.exists()) {
			file.createNewFile();
		}
		outputFile = new FileWriter(file);
		CSVWriter writer = new CSVWriter(outputFile, CSVWriter.NO_ESCAPE_CHARACTER,
	            CSVWriter.NO_QUOTE_CHARACTER,
	            CSVWriter.NO_ESCAPE_CHARACTER,
	            "");
		String[] header = { "MES,", "LOC,", "CLASSES,", "METODOS,", "CLASSE DEUS,", "METODO DEUS,", "\n" };
		writer.writeNext(header);
		String[] convertedResult = new String[resultSet.size()];
		for (int i = 0; i < convertedResult.length; i++) {
			convertedResult[i] = resultSet.get(i).toString();
		}
		String[] data = convertedResult;
		writer.writeNext(data, false);
		writer.close();
	}
}
