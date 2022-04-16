package com.eeg.xmlfootballparser.utils;

import com.eeg.xmlfootballparser.models.Player;
import com.eeg.xmlfootballparser.models.Team;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticsService implements ApplicationRunner {

    private final XmlParser xmlParser;

    public StatisticsService(XmlParser xmlParser){
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Team<Player>> teamsList = this.xmlParser.deserialize();
        for (Team<Player> team : teamsList)
            System.out.println(team.toString());
    }
}
