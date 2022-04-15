package com.eeg.xmlfootballparser.models;

import com.eeg.xmlfootballparser.enums.Position;
import com.eeg.xmlfootballparser.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Builder
@Getter
@Setter
@ToString
public class Player {
    private String playerRef;
    private boolean isCaptain;
    private Position position;
    private short shirtNumber;
    private Status status;
    private String firstName;
    private String lastName;
    private HashMap<String, Short> statistics;
}
