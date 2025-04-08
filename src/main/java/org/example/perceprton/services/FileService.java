package org.example.perceprton.services;

import org.example.perceprton.model.DataSet;
import org.example.perceprton.repositories.RawDataSetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private static final String DELIMITER = ",";

    @Value("${iris}")
    private String filename;
    private final RawDataSetRepository rawDataSetRepository;

    public FileService(RawDataSetRepository rawDataSetRepository){
        this.rawDataSetRepository = rawDataSetRepository;
    }

    public void readFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = br.readLine();
            String[] values;
            double[] vector;
            while ((line = br.readLine()) != null) {
                values = line.split(DELIMITER);
                vector = new double[]{
                        Double.parseDouble(values[0]),
                        Double.parseDouble(values[1]),
                        Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]),
                };

                rawDataSetRepository.addDataSet(new DataSet(vector,values[4]));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<DataSet> getRawDataSetRepositoryList(){
        return rawDataSetRepository.getRawDataSetList();
    }
}
