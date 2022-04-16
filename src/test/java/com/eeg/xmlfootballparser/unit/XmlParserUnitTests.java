package com.eeg.xmlfootballparser.unit;

import com.eeg.xmlfootballparser.utils.XmlParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XmlParserUnitTests {

    private XmlParser xmlParser;

    public XmlParserUnitTests(XmlParser xmlParser){
        this.xmlParser = xmlParser;
    }

    @Test
    void testDeserialize() {
    }
}
