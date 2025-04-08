package org.example.perceprton.services;

import org.example.perceprton.model.DataSet;
import org.example.perceprton.model.dataSetType;
import org.example.perceprton.repositories.TestDataSetRepository;
import org.example.perceprton.repositories.TrainDataSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrepareDataSet {
    private final TestDataSetRepository testDataSetRepository;
    private final TrainDataSetRepository trainDataSetRepository;

    public PrepareDataSet(TestDataSetRepository testDataSetRepository,
                          TrainDataSetRepository trainDataSetRepository){
        this.testDataSetRepository = testDataSetRepository;
        this.trainDataSetRepository = trainDataSetRepository;
    }

    public void trainTestSplit(List<DataSet> rawDataSetList){
        int testSize = (rawDataSetList.size()/100) * 30;

        int middleLeft = firstOccurrenceSearch(rawDataSetList,0,rawDataSetList.size()-1);
        int middleRight = middleLeft + 1;

        for(int i = 0; i < testSize/2; i++){
            middleLeft--;
            middleRight++;

            testDataSetRepository.addDataSet(rawDataSetList.get(middleLeft));
            testDataSetRepository.addDataSet(rawDataSetList.get(middleRight));
        }

        while (middleLeft >= 0 && middleRight <= rawDataSetList.size()-1){
            trainDataSetRepository.addDataSet(rawDataSetList.get(middleLeft));
            trainDataSetRepository.addDataSet(rawDataSetList.get(middleRight));

            middleLeft--;
            middleRight++;
        }

        while(middleLeft >= 0){
            trainDataSetRepository.addDataSet(rawDataSetList.get(middleLeft));
            middleLeft--;
        }

        while(middleRight <= rawDataSetList.size()-1){
            trainDataSetRepository.addDataSet(rawDataSetList.get(middleRight));
            middleRight++;
        }
    }

    public int firstOccurrenceSearch(List<DataSet> rawDataSetList, int left, int right){
        if(left == right) return -1;

        int m = (left + right)/2;
        dataSetType a = rawDataSetList.get(m).getType();
        dataSetType b = rawDataSetList.get(m+1).getType();

        if(a.equals(dataSetType.SETOSA) && b.equals(dataSetType.VERSICOLOR)){
            return m;
        } else if (a.equals(dataSetType.SETOSA)) {
            return firstOccurrenceSearch(rawDataSetList,m+1,right);
        } else {
            return firstOccurrenceSearch(rawDataSetList,left,m);
        }
    }

    public List<DataSet> getTestDataSetList(){
        return testDataSetRepository.getTestDataSetList();
    }

    public List<DataSet> getTrainDataSetList(){
        return trainDataSetRepository.getTrainDataSetList();
    }
}
























