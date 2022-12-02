package yao.uabc.algorithm;


import yao.uabc.model.Data;
import yao.uabc.utilities.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Esta clase modela al clasificador K-NN (K-Nearest Neighbors).
 * En este clasificador se utiliza la distancia euclidiana para calcular la distancia entre dos puntos y asi determinar la clase a la que pertenece un punto.
 * */
public class KNNClassifier {
    private int k;
    private List<Data> trainingSet;

    public KNNClassifier(int k) {
        this.k = k;
    }

    /**
     * Este método carga el conjunto de entrenamiento en memoria.
     * */
    public void train(List<Data> trainingSet) {
        this.trainingSet = trainingSet;
    }

    /**
     * Este método realiza la predicción de la clase a la que pertenece un punto.
     * @param data El punto a predecir.
     * @return El resultado de la predicción.
     * */
    public Prediction predict(Data data) {
        var results = new ArrayList<KDistance>();
        // Se calcula la distancia entre cada elemento del conjunto de entrenamiento y el elemento a predecir.
        for (var trainingData : trainingSet) {
            var distance = calculateDistance(data, trainingData);
            results.add(new KDistance(distance, trainingData.getId()));
        }
        // Se ordenan los resultados de menor a mayor distancia.
        results.sort(KDistance::compareTo);

        // Se obtienen los k elementos más cercanos.
        var neighbors = results.subList(0, k);
        // Se obtiene la clase más común entre los k elementos más cercanos.
        var predicted =  ListUtils.mostCommonItem(neighbors.stream().map(KDistance::spot).toList());
        var expected = data.getId();
        return new Prediction(expected, predicted);
    }

    /**
     * Este método calcula la distancia euclidiana entre dos puntos.
     * @param to El punto al que se le calculará la distancia.
     * @param from El punto desde el que se calculará la distancia.
     *
     * @return La distancia euclidiana entre los dos puntos.
     */
    private Double calculateDistance(Data to, Data from) {
        var toData = to.getData();
        var fromData = from.getData();
        var sum = 0.0;
        for (int i = 0; i < toData.length; i++) {
            sum += Math.pow(toData[i] - fromData[i], 2);
        }
        return Math.sqrt(sum);
    }



}
