package org.example.perceprton.controller;

import org.example.perceprton.model.DataSet;
import org.example.perceprton.services.FileService;
import org.example.perceprton.services.PrepareDataSet;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PerceptronController {
    private final FileService fileService;
    private final PrepareDataSet prepareDataSet;

    public PerceptronController(FileService fileService, PrepareDataSet prepareDataSet){
        this.fileService = fileService;
        this.prepareDataSet = prepareDataSet;
        start();
    }

    private void start(){
        fileService.readFile();
        prepareDataSet.trainTestSplit(fileService.getRawDataSetRepositoryList());
    }

    public List<DataSet> getRawDataSetList(){
        return fileService.getRawDataSetRepositoryList();
    }

    public List<DataSet> getTestDataSetList(){
        return prepareDataSet.getTestDataSetList();
    }

    public List<DataSet> getTrainDataSetList(){
        return prepareDataSet.getTrainDataSetList();
    }
}
