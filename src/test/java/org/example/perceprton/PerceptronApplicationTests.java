package org.example.perceprton;

import org.example.perceprton.controller.PerceptronController;
import org.example.perceprton.model.DataSet;
import org.example.perceprton.services.PrepareDataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class PerceptronApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private PerceptronController controller;

    @Autowired
    private PrepareDataSet prepareDataSet;

    @Test
    void testFirstOccurrenceSearch() {
        List<DataSet> dataSetList = controller.getRawDataSetList();

        assertFalse(dataSetList.isEmpty(), "DataSet list should not be empty");

        int result = prepareDataSet
                .firstOccurrenceSearch(dataSetList, 0, dataSetList.size() - 1);

        System.out.println("First occurrence index: " + result);
    }

    @Test
    void testTrainTestSplit() {
        List<DataSet> dataSetList = controller.getRawDataSetList();

        prepareDataSet.trainTestSplit(dataSetList);
        System.out.println("Test list: "+prepareDataSet.getTestDataSetList().size());
        System.out.println("Train list: "+prepareDataSet.getTrainDataSetList().size());
    }

}
