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
        var random = -1785392387304718916L;

        System.out.println("Randomizing the dataset...");
        Collections.shuffle(dataset, new Random(random));


        // Se divide el dataset en dos partes, el 80% para entrenamiento y el 20% para pruebas.
        var trainingPercentage = (int) Math.round(dataset.size() * 0.20);

        var trainingSet = dataset.subList(0, trainingPercentage);
        var testingSet = dataset.subList(trainingPercentage, dataset.size());

        // Se entrena el K-NN con el conjunto de entrenamiento.
        var classifier = new KNNClassifier(23);
        classifier.train(trainingSet);

        // Se realiza la predicción con el conjunto de pruebas.
        var predictions = new ArrayList<Prediction>();

        for (var data : testingSet) {
            predictions.add(classifier.predict(data));
        }

        // Se imprimen las métricas de desempeño.
        var accuracy = getAccuracy(predictions);
        System.out.println("Accuracy: " + accuracy);
        System.out.println("Correct: " + predictions.stream().filter(Prediction::isCorrect).count());
        System.out.println("Incorrect: " + (predictions.size() - predictions.stream().filter(Prediction::isCorrect).count()));

        var precision = getPrecision(predictions);

        var recall = getRecall(predictions);

        var f1Score = getF1Score(precision, recall);

        System.out.printf("Precision: %.2f%n", precision);
        System.out.printf("Recall: %.2f%n", recall);
        System.out.printf("F1 Score: %.2f%n", f1Score);
    }

    /**
     * Este método calcula la precisión del modelo.
     * https://www.themachinelearners.com/metricas-de-clasificacion/#Principales_Meacutetricas_de_clasificacioacuten
     * @param predictions Las predicciones realizadas por el modelo.
     * @return La precisión del modelo.
     * */
     private static double getAccuracy(ArrayList<Prediction> predictions) {
         var correct = predictions.stream().filter(Prediction::isCorrect).count();
         return (double) correct / predictions.size() * 100;
     }

     /**
      * Este método calcula el Fscore del modelo.
      * https://www.themachinelearners.com/metricas-de-clasificacion/#Principales_Meacutetricas_de_clasificacioacuten
      * @param precision La precisión del modelo.
      * @param recall El recall del modelo.
      * @return El Fscore del modelo.
      * */
    private static double getF1Score(double precision, double recall) {
        return 2 * ((precision * recall) / (precision + recall));
    }

    /**
     * Este método calcula el recall del modelo.
     * https://www.themachinelearners.com/metricas-de-clasificacion/#Principales_Meacutetricas_de_clasificacioacuten
     *
     * @param predictions Las predicciones realizadas por el modelo.
     * @return La precisión del modelo.
     * */
    private static double getRecall(ArrayList<Prediction> predictions) {
        var correct = predictions.stream().filter(Prediction::isCorrect).toList();
        var total = predictions.stream().filter(p -> p.expected() == 1).count();
        var correctTotal = correct.stream().filter(p -> p.expected() == 1).count();
        return (double) correctTotal / total;
    }

    /**
     * Este método calcula la precisión del modelo.
     * https://www.themachinelearners.com/metricas-de-clasificacion/#Principales_Meacutetricas_de_clasificacioacuten
     * @param predictions Las predicciones realizadas por el modelo.
     * @return La precisión del modelo.
     * */
    private static double getPrecision(ArrayList<Prediction> predictions) {
        var correct = predictions.stream().filter(Prediction::isCorrect).count();
        var incorrect = predictions.size() - correct;
        return (double) correct / (correct + incorrect);
    }




}
