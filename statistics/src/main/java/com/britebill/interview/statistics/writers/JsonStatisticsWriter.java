package com.britebill.interview.statistics.writers;

import com.britebill.interview.statistics.beans.Statistics;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class JsonStatisticsWriter implements StatisticsWriter {

    public void write(Statistics statistics, File file) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter();
        try {
            mapper.writeValue(file,statistics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
