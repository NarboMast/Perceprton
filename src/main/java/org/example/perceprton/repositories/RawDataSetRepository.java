package org.example.perceprton.repositories;

import org.example.perceprton.model.DataSet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RawDataSetRepository {
    private final List<DataSet> rawDataSetList;

    public RawDataSetRepository(List<DataSet> dataSetList){
        this.rawDataSetList = dataSetList;
    }

    public void addDataSet(DataSet dataSet){
        rawDataSetList.add(dataSet);
    }

    public List<DataSet> getRawDataSetList() {
        return rawDataSetList;
    }
}
