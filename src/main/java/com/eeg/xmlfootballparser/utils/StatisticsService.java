package com.eeg.xmlfootballparser.utils;

import com.eeg.xmlfootballparser.exceptions.XmlValidatorException;
import com.eeg.xmlfootballparser.models.Player;
import com.eeg.xmlfootballparser.models.SortPlayer;
import com.eeg.xmlfootballparser.models.Team;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StatisticsService {

    private final XmlParser xmlParser;

    @Value("${statistic}")
    private String statistic;

    public StatisticsService(XmlParser xmlParser){
        this.xmlParser = xmlParser;
    }

    /**
     * Validator method to verify input statistic exists within the file.
     * param statistic - {@link String}, denoting the statistic.
     * */
    private void validateNonEmptyInput(String statistic) throws XmlValidatorException {
        if (statistic == null || statistic.equals(""))
            throw new XmlValidatorException("Empty statistic input!");
    }

    /**
     * Validator method to verify input statistic exists within the file.
     * param statistic - {@link String}, denoting the statistic.
     * param players - {@link List} of type {@link Player}. Contains all the players (regardless of team).
     * */
    private void validateExistingStatistic(String statistic, List<Player> players) throws XmlValidatorException {
        long count = players.stream().filter(player -> player.getStatistics().containsKey(statistic)).count();
        if (count < 1)
            throw new XmlValidatorException("Your input statistics does not exist [" + statistic + "]!");
    }

    /**
     * Main runner method. Validates and calls the statistic logic.
     * */
    public void statisticReporter() throws ParserConfigurationException, IOException, SAXException, XmlValidatorException {
        List<Team<Player>> teamsList = this.xmlParser.deserialize();
        List<Player> playersList = Stream.concat(teamsList.get(0).getPlayers().stream(), teamsList.get(1).getPlayers().stream()).collect(Collectors.toList());

        System.out.println("Received statistic [" + this.statistic + "]");

        //Validates Input
        this.validateNonEmptyInput(this.statistic);
        this.validateExistingStatistic(this.statistic, playersList);

        //Reports Statistics
        this.statisticSorter(playersList, this.statistic);
        this.statisticPlayerSum(teamsList, this.statistic);
        this.statisticTeam(teamsList, this.statistic);
    }

    /**
     * Orders all players (descending) by the input statistic.
     * param players - {@link List} of type {@link Player}. Contains all the players (regardless of team).
     * param statistic - {@link String}, denoting the statistic.
     * */
    private void statisticSorter(List<Player> players, String statistic){
        List<SortPlayer> sortedPlayers = players.stream().map(x -> new SortPlayer(x, statistic)).collect(Collectors.toList());
        AtomicInteger i = new AtomicInteger();
        sortedPlayers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .subList(0,5)
                .forEach(x -> System.out.printf("%o.%s %s - %o%n", i.getAndIncrement()+1, x.getFirstName(), x.getLastName(), x.getStatistics().get(statistic)));
    }

    /**
     * Sums the statistic, grouping by team.
     * param teams - {@link List} of type {@link Team} containing {@link Player}. Contains all the players (regardless of team).
     * param statistic - {@link String}, denoting the statistic.
     * */
    private void statisticPlayerSum(List<Team<Player>> teams, String statistic){
        for(Team<Player> team : teams){
            int statisticSum = 0;
            for(Player player : team.getPlayers()){
                if (player.getStatistics().containsKey(statistic))
                    statisticSum += player.getStatistics().get(statistic);
            }
            System.out.printf("%s; %s - %o%n", team.getSide(), team.getName(), statisticSum);
        }
    }

    /**
     * Retrieves the statistic from the teams section.
     * param teams - {@link List} of type {@link Team} containing {@link Player}. Contains all the players (regardless of team).
     * param statistic - {@link String}, denoting the statistic.
     * */
    private void statisticTeam(List<Team<Player>> teams, String statistic){
        for(Team<Player> team : teams){
            if (team.getStatistics().containsKey(statistic))
                System.out.printf("%s; %s - %f%n", team.getSide(), team.getName(), team.getStatistics().get(statistic));
        }
    }
}