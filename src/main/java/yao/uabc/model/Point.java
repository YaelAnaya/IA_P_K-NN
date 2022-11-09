package yao.uabc.model;

public class Point implements Data {
    private  Double[] coordinates;
    private  Integer spot;
    public Point(String ...values) {
        this.coordinates = new Double[4];
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = Double.parseDouble(values[i]);
        }
        this.spot = Integer.valueOf(values[4]);
    }

    public String toString(){
        return String.format("[Pitch: %.4f, Roll: %.4f, Y: %.4f, Z: %.4f ]\n", coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
    }

    @Override
    public Double[] getData() {
        return coordinates;
    }

    @Override
    public Integer getId() {
        return spot;
    }
}
