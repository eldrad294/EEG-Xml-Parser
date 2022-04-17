package com.eeg.xmlfootballparser.models;

import com.eeg.xmlfootballparser.enums.Side;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

/**
 * JAVA POJO (Team). Utilizes lombok to avoid common boilerplate code.
 * */
@Builder
@Getter
@Setter
@ToString
public class Team<P extends Player> {
    private String teamRef;
    private short score;
    private Side side;
    private List<P> players;
    private HashMap<String, Float> statistics;   // I chose float since one of the team level statistics is a floating point value.
    private String name;
}
