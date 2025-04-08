package org.example.perceprton.model;

import java.util.Arrays;

public class DataSet {
    private final double[] vector;
    private final dataSetType type;

    public DataSet(double[] vector, String type){
        this.vector = vector;
        this.type = dataSetType.valueOf(type.toUpperCase());
    }

    public double[] getVector() {
        return vector;
    }

    public dataSetType getType() {
        return type;
    }

    @Override
    public String toString(){
        return Arrays.toString(vector) + " " + type;
    }
}
