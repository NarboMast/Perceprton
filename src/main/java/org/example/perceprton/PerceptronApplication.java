package org.example.perceprton;

import org.example.perceprton.controller.PerceptronController;
import org.example.perceprton.model.Perceptron;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class PerceptronApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PerceptronApplication.class, args);
        PerceptronController controller = context.getBean(PerceptronController.class);

        Perceptron perceptron = new Perceptron(0.001);
        perceptron.train(controller.getTrainDataSetList());
        System.out.println("Weight after train: " + Arrays.toString(perceptron.getWeights()));
        System.out.println("Threshold after train: " + perceptron.getThreshold());
        System.out.println("Total train iterations: " + perceptron.getTrainIterations());

        int[] realClasses = perceptron.getDataSetListByTypeAsInt(controller.getTestDataSetList());
        int[] predicted = perceptron.predict(controller.getTestDataSetList());

        System.out.println("Accuracy of test case: "+EvaluationMetrics.measureAccuracy(realClasses,predicted));
    }

}
