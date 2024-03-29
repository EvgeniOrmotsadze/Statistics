package com.britebill.interview.main;

import com.britebill.interview.statistics.beans.Statistics;
import com.britebill.interview.statistics.calculator.StatisticsCalculator;
import com.britebill.interview.statistics.writers.StatisticsWriter;
import com.britebill.interview.transformers.TransformerData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

public class Application {

    public static void main (String []args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context/context.xml");
        // Read file
        FileDataReader fileDataReader = context.getBean("fileDataReader",FileDataReader.class);
        List<String> data = fileDataReader.readData();

        // Calculate statistics
        // Tip: Use statistics.xsd to generate a Statistic bean
        Statistics statistic = new Statistics();
        StatisticsCalculator statisticsCalculator = new StatisticsCalculator();
        statistic.setMostRepeatedWord(statisticsCalculator.getMostRepeatedWord(data));
        statistic.setTotalNumberOfWords(statisticsCalculator.getTotalNumberOfWords(data));
        statistic.setTotalNumberOfUniqueWords(statisticsCalculator.getTotalNumberOfUniqueWords(data));
        statistic.setAverageCharactersPerWord(statisticsCalculator.getAverageCharactersPerWord(data));

        // Write statistic files
        StatisticsWriter xmlStatistics = (StatisticsWriter) context.getBean("xmlTextStatistics");
        xmlStatistics.write(statistic, new File("output.xml"));
        StatisticsWriter jsonStatistics = (StatisticsWriter) context.getBean("jsonTextStatistics");
        jsonStatistics.write(statistic, new File("output.json"));

        // Transform data
        TransformerData transformerData = (TransformerData) context.getBean("transformerData");
        List<String> transformedData = transformerData.transformData(data);
        transformedData.stream().forEach(d -> System.out.println (d));
    }

}
