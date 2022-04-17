package com.eeg.xmlfootballparser.models;

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
