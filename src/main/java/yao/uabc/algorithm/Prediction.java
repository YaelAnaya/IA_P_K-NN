package yao.uabc.algorithm;

import java.util.Objects;

/**
 * Esta clase modela el resultado de una predicción, representando el valor esperado y el valor predicho.
 * */
public record Prediction(Integer expected, Integer predicted) {
public boolean isCorrect() {
        return expected.equals(predicted);
    }

}
