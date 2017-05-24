package com.britebill.interview.transformers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TransformerDataTest {

    List<String> testList;
    TransformerData transformerData;

    @Before
    public void init(){
        transformerData = new TransformerData();
        testList = new ArrayList<String>() {{add("test"); add("test"); add("test2"); add("testtest"); add("buddy");}};
    }

    @Test
    public void testTransformer() {
        List<String> list = transformerData.transformData(testList);
        assertEquals(list.get(0),"TEST");
        assertEquals(list.get(list.size()-1),"TESTTEST");
    }

}
