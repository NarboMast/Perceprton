package org.example.perceprton.repositories;

import org.example.perceprton.model.DataSet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDataSetRepository {
    private final List<DataSet> trainDataSetList;

    public TrainDataSetRepository(List<DataSet> dataSetList){
        this.trainDataSetList = dataSetList;
    }

    public void addDataSet(DataSet dataSet){
        trainDataSetList.add(dataSet);
    }

    public List<DataSet> getTrainDataSetList() {
        return trainDataSetList;
    }
}
