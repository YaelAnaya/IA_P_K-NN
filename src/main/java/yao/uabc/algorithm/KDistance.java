package yao.uabc.algorithm;
/**
 * Esta clase modela un par de datos representando la distancia entre dos puntos y el id al que pertenece dicha distancia.
 * */
public record KDistance(double distance, Integer spot) implements Comparable<KDistance> {
    @Override
    public int compareTo(KDistance o) {
        return Double.compare(this.distance, o.distance);
    }
}
