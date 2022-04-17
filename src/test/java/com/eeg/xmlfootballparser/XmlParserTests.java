package com.eeg.xmlfootballparser;

import com.eeg.xmlfootballparser.enums.Position;
import com.eeg.xmlfootballparser.enums.Side;
import com.eeg.xmlfootballparser.enums.Status;
import com.eeg.xmlfootballparser.models.Player;
import com.eeg.xmlfootballparser.models.Team;
import com.eeg.xmlfootballparser.utils.XmlParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class XmlParserTests {

    @Autowired
    private XmlParser xmlParser;

    /**
     * Verifies the file contains exactly 2 teams.
     * */
    @Test
    void noOfTeams() throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = xmlParser.deserialize();
        assertThat(teams.size()).isEqualTo(2);
    }

    /**
     * Verifies the file contains exactly 36 players.
     * */
    @Test
    void noOfPlayersPerTeam() throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = xmlParser.deserialize();
        Team<Player> team1 = teams.get(0);
        assertThat(team1.getPlayers().size()).isEqualTo(18);
        Team<Player> team2 = teams.get(1);
        assertThat(team2.getPlayers().size()).isEqualTo(18);
    }

    /**
     * Verifies the file contains 1 captain per team.
     * */
    @Test
    void containsCaptainPerTeam() throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = xmlParser.deserialize();
        List<Player> playersTeam1 = teams.get(0).getPlayers();
        List<Player> playersTeam2 = teams.get(1).getPlayers();
        long captainCount;
        captainCount = playersTeam1.stream().filter(Player::isCaptain).count();
        assertThat(captainCount).isEqualTo(1);
        captainCount = playersTeam2.stream().filter(Player::isCaptain).count();
        assertThat(captainCount).isEqualTo(1);
    }

    /**
     * Verifies the file contains 1 goaler per team.
     * */
    @Test
    void containsGoalerPerTeam() throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = xmlParser.deserialize();
        List<Player> playersTeam1 = teams.get(0).getPlayers();
        List<Player> playersTeam2 = teams.get(1).getPlayers();
        long goalerCount;
        goalerCount = playersTeam1.stream().filter(player -> player.getPosition().equals(Position.GOALKEEPER)).count();
        assertThat(goalerCount).isEqualTo(1);
        goalerCount = playersTeam2.stream().filter(player -> player.getPosition().equals(Position.GOALKEEPER)).count();
        assertThat(goalerCount).isEqualTo(1);
    }

//    /**
//     * Verifies the file contains 1 manager per team.
//     * */
//    @Test
//    void containsManagerPerTeam() throws ParserConfigurationException, IOException, SAXException {
//        List<Team<Player>> teams = xmlParser.deserialize();
//        List<Player> playersTeam1 = teams.get(0).getPlayers();
//        List<Player> playersTeam2 = teams.get(1).getPlayers();
//        long managerCount;
//        managerCount = playersTeam1.stream().filter(player -> player.getPosition().equals(Position.MANAGER)).count();
//        assertThat(managerCount).isEqualTo(1);
//        managerCount = playersTeam2.stream().filter(player -> player.getPosition().equals(Position.MANAGER)).count();
//        assertThat(managerCount).isEqualTo(1);
//    }

    private boolean enumPositionChecker(Enum<Position> playerEnum){
        return Arrays.asList(Position.values()).contains(playerEnum);
    }

    /**
     * Verifies the file contains supported {sub}positions.
     * */
    @Test
    void containsASupportedPosition() throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = xmlParser.deserialize();
        List<Player> playersTeam1 = teams.get(0).getPlayers();
        for (Player player : playersTeam1) {
            assertThat(enumPositionChecker(player.getPosition())).isEqualTo(true);
            if (player.getSubPosition() != null)
                assertThat(enumPositionChecker(player.getSubPosition())).isEqualTo(true);
        }
        List<Player> playersTeam2 = teams.get(1).getPlayers();
        for (Player player : playersTeam2) {
            assertThat(enumPositionChecker(player.getPosition())).isEqualTo(true);
            if (player.getSubPosition() != null)
                assertThat(enumPositionChecker(player.getSubPosition())).isEqualTo(true);
        }
    }

    private boolean enumSideChecker(Enum<Side> teamEnum){
        return Arrays.asList(Side.values()).contains(teamEnum);
    }

    /**
     * Verifies the file contains supported team sides.
     * */
    @Test
    void containsASupportedSide() throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = xmlParser.deserialize();
        for (Team<Player> team : teams)
            assertThat(enumSideChecker(team.getSide())).isEqualTo(true);
    }

    private boolean enumStatusChecker(Enum<Status> statusEnum){
        return Arrays.asList(Status.values()).contains(statusEnum);
    }

    /**
     * Verifies the file contains supported team sides.
     * */
    @Test
    void containsASupportedStatus() throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = xmlParser.deserialize();
        List<Player> playersTeam1 = teams.get(0).getPlayers();
        for (Player player : playersTeam1)
            assertThat(enumStatusChecker(player.getStatus())).isEqualTo(true);
        List<Player> playersTeam2 = teams.get(1).getPlayers();
        for (Player player : playersTeam2)
            assertThat(enumStatusChecker(player.getStatus())).isEqualTo(true);
    }
}
