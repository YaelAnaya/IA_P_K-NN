package yao.uabc;

import yao.uabc.algorithm.KNNClassifier;
import yao.uabc.algorithm.Prediction;
import yao.uabc.utilities.FileManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Esta clase se carga el dataset en memoria, se entrena el K-NN y se realiza la predicción.
 * Una vez terminado el proceso, se imprimen las métricas de desempeño que se obtuvieron.
 * */
public class Application {
    public static void main(String[] args) {

        var dataset = FileManager.loadDataset("trainer", "participante_yh13_training.csv");
        // Se mezcla el dataset.
        System.out.println("Randomizing the dataset...");
        Collections.shuffle(dataset, new Random(1));


        // Se divide el dataset en dos partes, el 80% para entrenamiento y el 20% para pruebas.
        var trainingPercentage = (int) Math.round(dataset.size() * 0.20);

        var trainingSet = dataset.subList(0, trainingPercentage);
        var testingSet = dataset.subList(trainingPercentage, dataset.size());

        // Se entrena el K-NN con el conjunto de entrenamiento.
        var classifier = new KNNClassifier(3);
        classifier.train(trainingSet);

        // Se realiza la predicción con el conjunto de pruebas.
        var predictions = new ArrayList<Prediction>();

        for (var data : testingSet) {
            predictions.add(classifier.predict(data));
        }
        // se muestran los correctos e incorrectos
        var correctos = predictions.stream().filter(Prediction::isCorrect).count();
        var incorrectos = predictions.stream().filter(p -> !p.isCorrect()).count();
        System.out.println("Correctos: " + correctos);
        System.out.println("Incorrectos: " + incorrectos);

        // Se imprimen las métricas de desempeño.
        new Metrics(predictions);


    }
}
