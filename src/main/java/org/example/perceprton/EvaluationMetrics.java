package org.example.perceprton;

public class EvaluationMetrics {
    public static double measureAccuracy(int[] realClasses, int[] predicted){
        int correct = 0;
        for (int i = 0; i < realClasses.length; i++)
            if(realClasses[i] == predicted[i]) correct++;
        return (double) correct /realClasses.length;
    }
}
