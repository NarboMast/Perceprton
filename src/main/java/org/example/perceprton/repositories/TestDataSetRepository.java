package org.example.perceprton.repositories;

import org.example.perceprton.model.DataSet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDataSetRepository {
    private final List<DataSet> testDataSetList;

    public TestDataSetRepository(List<DataSet> dataSetList){
        this.testDataSetList = dataSetList;
    }

    public void addDataSet(DataSet dataSet){
        testDataSetList.add(dataSet);
    }

    public List<DataSet> getTestDataSetList() {
        return testDataSetList;
    }
}
