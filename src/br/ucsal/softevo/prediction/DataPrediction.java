package br.ucsal.softevo.prediction;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.softevo.model.AnalysisData;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import br.ucsal.softevo.buffer.FileWritterCsv;

public class DataPrediction {
	public static void doPrediction(List<AnalysisData> data) throws Exception {
		DataSource dataInStore = new DataSource("medicaoSemPrevisao.csv");
		Instances instance = dataInStore.getDataSet();
		
		MultilayerPerceptron model = new MultilayerPerceptron();
		double prediction[];
		ArrayList<Integer> newData = new ArrayList<Integer>();
		
		for (int i = 1; i < 6; i++) {
			instance.setClassIndex(i);
			model.buildClassifier(instance);

			Instance newInstance = instance.lastInstance();
			prediction = model.distributionForInstance(newInstance);
			newData.add((int) Math.round(prediction[0]));
		}
		
		data.add(new AnalysisData(28, newData.get(1), newData.get(0), newData.get(2), newData.get(3), newData.get(4)));
		FileWritterCsv.writeCsv(data, "predição");
	}
}
