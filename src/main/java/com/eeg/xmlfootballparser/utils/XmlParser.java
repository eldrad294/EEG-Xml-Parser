package com.eeg.xmlfootballparser.utils;

import com.eeg.xmlfootballparser.enums.Position;
import com.eeg.xmlfootballparser.enums.Side;
import com.eeg.xmlfootballparser.enums.Status;
import com.eeg.xmlfootballparser.models.Player;
import com.eeg.xmlfootballparser.models.Team;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class XmlParser implements ApplicationRunner {

    @Value("${xml-path}")
	private String xmlPath;

    private Document loadFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(xmlPath));
        doc.getDocumentElement().normalize();
        return doc;
    }

    private List<Team<Player>> deserialize() throws ParserConfigurationException, IOException, SAXException {
        Document doc = loadFile();
        NodeList teamData = doc.getElementsByTagName("TeamData");

        List<Team<Player>> teamsList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        for (int i=0; i<teamData.getLength(); i++) {
            NodeList teamDataNodes = teamData.item(i).getChildNodes();

//            for (int z=0;z<=teamDataNodes.getLength();z++){
//                if (z==0){
//
//                }
//            }

            for (int j=0; j<=teamDataNodes.getLength();j++ ){
                NamedNodeMap playerAttributes = teamDataNodes.item(i).getAttributes();
                HashMap<String, Short> statsMap = new HashMap<>();
                NodeList stats = teamDataNodes.item(i).getChildNodes();

                for (int z=0;z<stats.getLength();z++)
                    statsMap.put(stats.item(z).getNodeName(), Short.parseShort(stats.item(z).getNodeValue()));

                Player player;
                if (playerAttributes.item(0).getNodeName().equals("Captain")){
                    player = Player.builder()
                            .isCaptain(true)
                            .playerRef(playerAttributes.item(1).getNodeValue())
                            .position(Position.valueOf(playerAttributes.item(2).getNodeValue().toUpperCase()))
                            .shirtNumber(Short.parseShort(playerAttributes.item(3).getNodeValue()))
                            .status(Status.valueOf(playerAttributes.item(4).getNodeValue().toUpperCase()))
                            .statistics(statsMap)
                            .build();
                }else{
                    player = Player.builder()
                            .isCaptain(false)
                            .playerRef(playerAttributes.item(0).getNodeValue())
                            .position(Position.valueOf(playerAttributes.item(1).getNodeValue().toUpperCase()))
                            .shirtNumber(Short.parseShort(playerAttributes.item(2).getNodeValue()))
                            .status(Status.valueOf(playerAttributes.item(3).getNodeValue().toUpperCase()))
                            .statistics(statsMap)
                            .build();
                }
                playerList.add(player);
            }
            NamedNodeMap teamAttributes = teamData.item(i).getAttributes();
            Team<Player> team = Team.builder()
                    .players(playerList)
                    .score(Short.parseShort(teamAttributes.item(0).getNodeValue()))
                    .side(Side.valueOf(teamAttributes.item(1).getNodeValue()))
                    .teamRef(teamAttributes.item(2).getNodeValue())
                    //.statistics()
                    .build();
            teamsList.add(team);
        }
        return teamsList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Team<Player>> teamsList = deserialize();
        for (Team<Player> team : teamsList)
            System.out.println(team.toString());
    }
}