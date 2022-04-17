package com.eeg.xmlfootballparser.models.reporting;


import com.eeg.xmlfootballparser.models.Player;

/**
 * Model class to capture the 1st requirement of the reporting criteria. Captures the following information:
 * 1) The input statisticFilter.
 * 2) Inherits from Player all player fields.
 *
 * Developer's Note: This class is somewhat redundant, and was only implemented to allow better unit testing of the StatisticsService class methods.
 * */
public class SortPlayer extends Player implements Comparable<Player>{

    private final String statisticFilter;

    public SortPlayer(Player player, String statisticFilter){
        super(player.getPlayerRef(), player.isCaptain(), player.getPosition(), player.getSubPosition(), player.getShirtNumber(), player.getStatus(), player.getFirstName(), player.getLastName(), player.getKnown(), player.getStatistics());
        this.statisticFilter = statisticFilter;
    }

    @Override
    public int compareTo(Player player) {
        short thisStatisticsCount = 0;
        short comparatorStatisticsCount = 0;
        if (this.getStatistics().containsKey(this.statisticFilter))
            thisStatisticsCount = this.getStatistics().get(this.statisticFilter);
        if (player.getStatistics().containsKey(this.statisticFilter))
            comparatorStatisticsCount = player.getStatistics().get(this.statisticFilter);
        return Integer.compare(thisStatisticsCount, comparatorStatisticsCount);
    }

}
