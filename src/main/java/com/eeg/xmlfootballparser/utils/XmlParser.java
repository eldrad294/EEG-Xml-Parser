package com.eeg.xmlfootballparser.utils;

import com.eeg.xmlfootballparser.enums.Position;
import com.eeg.xmlfootballparser.enums.Side;
import com.eeg.xmlfootballparser.enums.Status;
import com.eeg.xmlfootballparser.models.Player;
import com.eeg.xmlfootballparser.models.Team;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class XmlParser {

    @Value("${xml-path}")
	private String xmlPath;

    /**
     * XML Constants
     * */
    private static final String PLAYER = "Player";
    private static final String POSITION = "Position";
    private static final String UID = "uID";
    private static final String FIRST = "First";
    private static final String KNOWN = "Known";
    private static final String LAST = "Last";
    private static final String TEAM_DATA = "TeamData";
    private static final String SCORE = "Score";
    private static final String SIDE = "Side";
    private static final String TEAM_REF = "TeamRef";
    private static final String MATCH_PLAYER = "MatchPlayer";
    private static final String STAT = "Stat";
    private static final String TYPE = "Type";
    private static final String CAPTAIN = "Captain";
    private static final String PLAYER_REF = "PlayerRef";
    private static final String SHIRT_NUMBER = "ShirtNumber";
    private static final String STATUS = "Status";
    private static final String SUB_POSITION = "SubPosition";
    private static final String TEAM = "Team";
    private static final String NAME = "Name";

    /**
     * Package Private
     * DOM File Loader. Loads physical file into memory as a document.
     * @return {@link Document} */
    private Document loadFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(this.xmlPath));
        doc.getDocumentElement().normalize();
        return doc;
    }

    /**
     * Package Private
     * Enriches the team object with metadata from a DOM document. This method is mutable, and will operate on the input parameter!
     * @param team - {@link Team} of type generics {@link Player}
     * @param doc - {@link Document}
     * */
    private void enrichTeam(Team<Player> team, Document doc){
        NodeList teamNodes = doc.getElementsByTagName(TEAM);
        for (int i=0; i<teamNodes.getLength(); i++) {
            Node teamNode = teamNodes.item(i);
            if (teamNode.getNodeType() == Node.ELEMENT_NODE){
                Element teamElement = (Element) teamNode;
                String uID = teamElement.getAttribute(UID);
                if (team.getTeamRef().equals(uID)) {
                    NodeList teamNameNode = teamElement.getElementsByTagName(NAME);
                    String teamName = teamNameNode.item(0).getTextContent();
                    team.setName(teamName);
                    break;
                }
            }
        }
    }

    /**
     * Package Private
     * Enriches the player object with metadata from a DOM document. This method is mutable, and will operate on the input parameter!
     * @param player - {@link Player}
     * @param doc - {@link Document}
     * */
    private void enrichPlayer(Player player, Document doc){
        NodeList playerNodes = doc.getElementsByTagName(PLAYER);
        for (int i=0; i<playerNodes.getLength(); i++) {
            Node playerNode = playerNodes.item(i);
            if (playerNode.getNodeType() == Node.ELEMENT_NODE){
                Element playerElement = (Element) playerNode;
                Position position = Position.valueOf(playerElement.getAttribute(POSITION).toUpperCase());
                String uID = playerElement.getAttribute(UID);
                if (player.getPlayerRef().equals(uID) && player.getPosition().equals(position)){
                    NodeList firstNameNode = playerElement.getElementsByTagName(FIRST);
                    NodeList knownNameNode = playerElement.getElementsByTagName(KNOWN);
                    NodeList lastNameNode = playerElement.getElementsByTagName(LAST);

                    String firstName = firstNameNode.item(0).getTextContent();
                    String lastName = lastNameNode.item(0).getTextContent();
                    String known = null;
                    if (knownNameNode.getLength() != 0)
                        known = knownNameNode.item(0).getTextContent();

                    player.setFirstName(firstName);
                    player.setLastName(lastName);
                    player.setKnown(known);
                    break;
                }
            }
        }
    }

    /**
     * Main class method. Deserializes the XML file contents into JAVA in-memory POJOs.
     * @return {@link List} of type {@link Team}, type generics {@link Player}.
     * */
    public List<Team<Player>> deserialize() throws ParserConfigurationException, IOException, SAXException {
        Document doc = loadFile();
        NodeList teamDataNodes = doc.getElementsByTagName(TEAM_DATA);

        List<Team<Player>> teamsList = new ArrayList<>();
        for (int i=0; i<teamDataNodes.getLength(); i++) {
            List<Player> playerList = new ArrayList<>();
            Node teamNode = teamDataNodes.item(i);
            if (teamNode.getNodeType() == Node.ELEMENT_NODE){
                Element teamNodeElement = (Element) teamNode;
                short score = Short.parseShort(teamNodeElement.getAttribute(SCORE));
                Side side = Side.valueOf(teamNodeElement.getAttribute(SIDE).toUpperCase());
                String teamRef = teamNodeElement.getAttribute(TEAM_REF);

                NodeList matchPlayers = teamNodeElement.getElementsByTagName(MATCH_PLAYER);
                for (int j=0; j<matchPlayers.getLength();j++ ){
                    Node playerNode = matchPlayers.item(j);
                    if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element playerNodeElement = (Element) playerNode;

                        NodeList playerStats = playerNodeElement.getElementsByTagName(STAT);
                        HashMap<String, Short> playerStatsMap = new HashMap<>();
                        for (int z=0;z<playerStats.getLength();z++){
                            Node statNode = playerStats.item(z);
                            if (statNode.getNodeType() == Node.ELEMENT_NODE){
                                Element statNodeElement = (Element) statNode;
                                playerStatsMap.put(statNodeElement.getAttribute(TYPE), Short.parseShort(statNode.getTextContent()));
                            }
                        }

                        Position subPosition = null;
                        if (playerNodeElement.hasAttribute(SUB_POSITION))
                            subPosition = Position.valueOf(playerNodeElement.getAttribute(SUB_POSITION).toUpperCase());

                        Player player = Player.builder()
                                .isCaptain(playerNodeElement.hasAttribute(CAPTAIN))
                                .playerRef(playerNodeElement.getAttribute(PLAYER_REF))
                                .position(Position.valueOf(playerNodeElement.getAttribute(POSITION).toUpperCase()))
                                .subPosition(subPosition)
                                .shirtNumber(Short.parseShort(playerNodeElement.getAttribute(SHIRT_NUMBER)))
                                .status(Status.valueOf(playerNodeElement.getAttribute(STATUS).toUpperCase()))
                                .statistics(playerStatsMap)
                                .build();
                        this.enrichPlayer(player, doc);
                        playerList.add(player);
                    }
                }

                HashMap<String, Float> teamStatsMap = new HashMap<>();
                NodeList teamStats = teamNodeElement.getElementsByTagName(STAT);
                for (int j=0; j<teamStats.getLength();j++ ){
                    Node statNode = teamStats.item(j);
                    if (statNode.getNodeType() == Node.ELEMENT_NODE){
                        Element statNodeElement = (Element) statNode;
                        teamStatsMap.put(statNodeElement.getAttribute(TYPE), Float.parseFloat(statNode.getTextContent()));
                    }
                }
                Team<Player> team = Team.builder()
                        .players(playerList)
                        .score(score)
                        .side(side)
                        .teamRef(teamRef)
                        .statistics(teamStatsMap)
                        .build();
                this.enrichTeam(team, doc);
                teamsList.add(team);
            }
        }
        return teamsList;
    }
}