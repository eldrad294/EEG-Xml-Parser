package com.eeg.xmlfootballparser.models.reporting;

import com.eeg.xmlfootballparser.enums.Side;
import lombok.Builder;
import lombok.Getter;

/**
 * Model class to capture the 2nd requirement of the reporting criteria. Captures the following information:
 * 1) Team side
 * 2) Team name
 * 3) Team statistic summation
 *
 * Developer's Note: This class is somewhat redundant, and was only implemented to allow better unit testing of the StatisticsService class methods.
 * */
@Builder
@Getter
public class TeamSum {

    private Side side;
    private String name;
    private Float statisticSum;

}
