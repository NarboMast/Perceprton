package org.example.perceprton.model;

import org.example.perceprton.EvaluationMetrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Perceptron {
    private static int trainIterations = 0;
    private static final int DIMENSION = 4;
    private static final double[] weights = new double[DIMENSION];
    private static double threshold = 0.01;
    private final double alpha;
    private double beta;

    public Perceptron(double alpha){
        this.alpha = alpha;
        Arrays.fill(weights, 0.1);
    }

    public void train(List<DataSet> trainDataSetList){
        boolean allCorrect;
        int[] realClasses = getDataSetListByTypeAsInt(trainDataSetList);

        do {
            allCorrect = true;

            for (DataSet current : trainDataSetList) {
                double[] input = current.getVector();
                int predictedY = calcY(input);
                int trueClass = getTypeAsInt(current.getType());

                if (predictedY != trueClass) {
                    allCorrect = false;
                    correctWeights(trueClass, predictedY, input);
                    correctThreshold(trueClass, predictedY);
                }
            }
            trainIterations++;

            int[] predicted = predict(trainDataSetList);
            System.out.println("Iteration: " + trainIterations + ". " + EvaluationMetrics.measureAccuracy(realClasses,predicted));
        } while (!allCorrect);
    }

    public int calcY(double[] input){
        double y = 0;
        for (int i = 0; i < DIMENSION; i++) {
            y += weights[i] * input[i];
        }
        y -= threshold;

        return stepFunction(y);
    }

    public int stepFunction(double y){
        if(y >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void correctWeights(int d, int y, double[] input){
        double inner = (d-y)*alpha;
        for (int i = 0; i < DIMENSION; i++) {
            weights[i] += input[i]*inner;
        }
    }

    public void correctThreshold(int d, int y){
        threshold += (d-y)*alpha;
    }

    public int getTypeAsInt(dataSetType type){
        if(type.equals(dataSetType.SETOSA)){
            return 1;
        } else {
            return 0;
        }
    }

    public int[] predict(List<DataSet> trainDataSetList){
        int[] res = new int[trainDataSetList.size()];
        for (int i = 0; i < trainDataSetList.size(); i++){
            int y = calcY(trainDataSetList.get(i).getVector());
            res[i] = y;
        }
        return res;
    }

    public int[] getDataSetListByTypeAsInt(List<DataSet> list){
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            res[i] = getTypeAsInt(list.get(i).getType());
        }
        return res;
    }

    public double[] getWeights(){
        return weights;
    }

    public double getThreshold(){
        return threshold;
    }

    public int getTrainIterations(){
        return trainIterations;
    }
}
