package com.eeg.xmlfootballparser.integration;

import com.eeg.xmlfootballparser.utils.XmlParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XmlParserIntegrationTests {

    private XmlParser xmlParser;

    public XmlParserIntegrationTests(XmlParser xmlParser){
        this.xmlParser = xmlParser;
    }

    @Test
    void noOfTeams() {
    }

    @Test
    void noOfPlayersPerTeam() {
    }

    @Test
    void containsCaptainPerTeam() {
    }

    @Test
    void containsGoalerPerTeam() {
    }

    @Test
    void containsManagerPerTeam() {
    }
}
