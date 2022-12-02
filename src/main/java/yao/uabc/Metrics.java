package yao.uabc;

import yao.uabc.algorithm.Prediction;

import java.util.ArrayList;

public class Metrics {
    private final ArrayList<Prediction> predictions;
    public Metrics(ArrayList<Prediction> predictions) {
        this.predictions = predictions;
        printMetrics();
    }

    private void printMetrics() {
        var truePositives = 0;
        var trueNegatives = 0;
        var falsePositives = 0;
        var falseNegatives = 0;

        for (var prediction : predictions) {
            if (prediction.isCorrect()) {
                    truePositives ++;
                    trueNegatives += 4;

            } else {
                    trueNegatives += 3;
                    falseNegatives++;
                    falsePositives++;
            }

        }

        var accuracy = (double) (truePositives + trueNegatives) / (truePositives + trueNegatives + falsePositives + falseNegatives) * 100;
        var precision = (double) truePositives / (truePositives + falsePositives);
        var recall = (double) truePositives / (truePositives + falseNegatives);
        var f1Score = (double) 2 * precision * recall / (precision + recall);

        System.out.printf("Accuracy: %.4f\n", accuracy);
        System.out.printf("Precision: %.3f\n", precision);
        System.out.printf("Recall: %.3f\n", recall);
        System.out.printf("F1-Score: %.3f\n", f1Score);

    }
}
