package com.eeg.xmlfootballparser.models;

import com.eeg.xmlfootballparser.enums.Position;
import com.eeg.xmlfootballparser.enums.Status;
import lombok.*;

import java.util.HashMap;

/**
 * JAVA POJO (Team). Utilizes lombok to avoid common boilerplate code.
 * */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Player{
    private String playerRef;
    private boolean isCaptain;
    private Position position;
    private Position subPosition;
    private short shirtNumber;
    private Status status;
    private String firstName;
    private String lastName;
    private String known;
    private HashMap<String, Short> statistics;  // Maybe to consider INT / BIGINT / LONG for statistics of a larger size.
}
