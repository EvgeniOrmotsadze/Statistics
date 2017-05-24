package com.britebill.interview.statistics.writers;

import com.britebill.interview.statistics.beans.Statistics;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;


public class XmlStatisticsWriter implements StatisticsWriter {

    public void write(Statistics statistics, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Statistics.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(statistics, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
