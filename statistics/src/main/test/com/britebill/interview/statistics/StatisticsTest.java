package com.britebill.interview.statistics;

import com.britebill.interview.statistics.beans.Statistics;
import com.britebill.interview.statistics.calculator.StatisticsCalculator;
import com.britebill.interview.statistics.writers.JsonStatisticsWriter;
import com.britebill.interview.statistics.writers.XmlStatisticsWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class StatisticsTest {

    Statistics statistic;
    File fileXml;
    File fileJson;
    List<String> testList;
    StatisticsCalculator statisticsCalculator;

    @Before
    public void init() {
        statistic = new Statistics();
        statistic.setAverageCharactersPerWord(5);
        statistic.setMostRepeatedWord("game");
        statistic.setTotalNumberOfUniqueWords(20);
        statistic.setTotalNumberOfWords(30);
        fileXml = new File("output.xml");
        fileJson = new File("output.json");
        statisticsCalculator = new StatisticsCalculator();
        testList = new ArrayList<String>() {{add("test"); add("test"); add("test2"); add("testtest"); add("buddy");}};
    }

    @Test
    public void testXmlGeneration() throws JAXBException {

        XmlStatisticsWriter xmlStatisticsWriter = new XmlStatisticsWriter();
        xmlStatisticsWriter.write(statistic, fileXml);  // create file

        JAXBContext jaxbContext = JAXBContext.newInstance(Statistics.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        // create unmarshal from jaxb
        Statistics xmlStatistic = (Statistics) jaxbUnmarshaller.unmarshal(fileXml);
        //check if equals
        assertEquals(xmlStatistic.getAverageCharactersPerWord(), 5);
        assertEquals(xmlStatistic.getMostRepeatedWord(), "game");
        assertEquals(xmlStatistic.getTotalNumberOfUniqueWords(), 20);
        assertEquals(xmlStatistic.getTotalNumberOfWords(), 30);
    }

    @Test
    public void testJsonGeneration() throws JAXBException, IOException, ParseException {
        JsonStatisticsWriter jsonStatisticsWriter = new JsonStatisticsWriter();
        jsonStatisticsWriter.write(statistic, fileJson);
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileJson));
        JSONObject jsonObject = (JSONObject) object;

        //Reading the String
        Object totalNumberOfWords = jsonObject.get("totalNumberOfWords");
        Object totalNumberOfUniqueWords = jsonObject.get("totalNumberOfUniqueWords");
        Object averageCharactersPerWord = jsonObject.get("averageCharactersPerWord");
        Object mostRepeatedWord = jsonObject.get("mostRepeatedWord");

        assertEquals(Integer.parseInt(totalNumberOfWords.toString()), 30);
        assertEquals(Integer.parseInt(totalNumberOfUniqueWords.toString()), 20);
        assertEquals(Integer.parseInt(averageCharactersPerWord.toString()), 5);
        assertEquals(mostRepeatedWord.toString(), "game");
    }


    @Test
    public void testTotalNumberOfWords() {
        long averageCharactersPerWord = statisticsCalculator.getTotalNumberOfWords(testList);
        assertEquals(5,(int)averageCharactersPerWord);
    }

    @Test
    public void testTotalNumberOfUniqueWords() {
        long totalNumberOfUniqueWords = statisticsCalculator.getTotalNumberOfUniqueWords(testList);
        assertEquals(4,(int)totalNumberOfUniqueWords);
    }

    @Test
    public void testAverageCharactersPerWord() {
        long averageCharactersPerWord = statisticsCalculator.getAverageCharactersPerWord(testList);
        assertEquals(5,(int)averageCharactersPerWord);
    }

    @Test
    public void testMostRepeatedWord() {
        String mostRepeatedWord = statisticsCalculator.getMostRepeatedWord(testList);
        assertEquals("test",mostRepeatedWord);
    }

}
