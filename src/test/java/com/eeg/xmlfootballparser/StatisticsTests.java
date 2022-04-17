package com.eeg.xmlfootballparser;

import com.eeg.xmlfootballparser.models.Player;
import com.eeg.xmlfootballparser.models.Team;
import com.eeg.xmlfootballparser.models.reporting.TeamSum;
import com.eeg.xmlfootballparser.utils.StatisticsService;
import com.eeg.xmlfootballparser.utils.XmlParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Compares the player summation stats with those declared within the file on a team level.
 * */
@SpringBootTest
public class StatisticsTests {

    @Autowired
    private XmlParser xmlParser;
    @Autowired
    private StatisticsService statisticsService;

    /**
     * Verifies the file contains exactly 2 teams.
     * */
    private void statisticMatcher(String statistic) throws ParserConfigurationException, IOException, SAXException {
        List<Team<Player>> teams = this.xmlParser.deserialize();
        List<TeamSum> playerSumList = this.statisticsService.statisticPlayerSum(teams, statistic);
        List<TeamSum> teamSumList = this.statisticsService.statisticTeam(teams, statistic);
        assertThat(playerSumList.size()).isEqualTo(teamSumList.size());
        for (int i=0;i<playerSumList.size();i++)
            assertThat(playerSumList.get(i).getStatisticSum()).isEqualTo(teamSumList.get(i).getStatisticSum());
    }

    @Test
    void match_duel_lost() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("duel_lost");
    }

    @Test
    void match_blocked_scoring_att() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("blocked_scoring_att");
    }

    @Test
    void match_leftside_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("leftside_pass");
    }

    @Test
    void match_poss_won_att_3rd() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("poss_won_att_3rd");
    }

    @Test
    void match_dispossessed() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("dispossessed");
    }

//    @Test
//    void match_accurate_cross() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("accurate_cross");
//    }

    @Test
    void match_att_rf_total() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_rf_total");
    }

    @Test
    void match_att_bx_right() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_bx_right");
    }

//    @Test
//    void match_six_yard_block() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("six_yard_block");
//    }

//    @Test
//    void match_accurate_pass() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("accurate_pass");
//    }

    @Test
    void match_won_tackle() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("won_tackle");
    }

//    @Test
//    void match_att_assist_setplay() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_assist_setplay");
//    }

    @Test
    void match_total_final_third_passes() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_final_third_passes");
    }

    @Test
    void match_rightside_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("rightside_pass");
    }

//    @Test
//    void match_attempts_conceded_ibox() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("attempts_conceded_ibox");
//    }

//    @Test
//    void match_touches() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("touches");
//    }

    @Test
    void match_total_fwd_zone_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_fwd_zone_pass");
    }

    @Test
    void match_att_assist_openplay() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_assist_openplay");
    }

    @Test
    void match_won_contest() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("won_contest");
    }

    @Test
    void match_accurate_fwd_zone_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_fwd_zone_pass");
    }

    @Test
    void match_total_chipped_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_chipped_pass");
    }

    @Test
    void match_effective_head_clearance() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("effective_head_clearance");
    }

    @Test
    void match_lost_corners() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("lost_corners");
    }

    @Test
    void match_saves() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("saves");
    }

    @Test
    void match_ontarget_scoring_att() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("ontarget_scoring_att");
    }

    @Test
    void match_total_scoring_att() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_scoring_att");
    }

    @Test
    void match_blocked_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("blocked_pass");
    }

//    @Test
//    void match_attempts_conceded_obox() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("attempts_conceded_obox");
//    }

    @Test
    void match_poss_won_def_3rd() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("poss_won_def_3rd");
    }

//    @Test
//    void match_accurate_back_zone_pass() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("accurate_back_zone_pass");
//    }

    @Test
    void match_passes_right() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("passes_right");
    }

    @Test
    void match_total_throws() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_throws");
    }

    @Test
    void match_att_obox_target() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_obox_target");
    }

//    @Test
//    void match_successful_open_play_pass() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("successful_open_play_pass");
//    }

//    @Test
//    void match_total_back_zone_pass() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("total_back_zone_pass");
//    }

    @Test
    void match_accurate_layoffs() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_layoffs");
    }

    @Test
    void match_total_long_balls() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_long_balls");
    }

    @Test
    void match_accurate_keeper_throws() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_keeper_throws");
    }

    @Test
    void match_att_obx_centre() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_obx_centre");
    }

    @Test
    void match_att_openplay() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_openplay");
    }

    @Test
    void match_poss_won_mid_3rd() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("poss_won_mid_3rd");
    }

    @Test
    void match_put_through() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("put_through");
    }

//    @Test
//    void match_att_ibox_target() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_ibox_target");
//    }

    @Test
    void match_head_clearance() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("head_clearance");
    }

    @Test
    void match_goal_kicks() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("goal_kicks");
    }

//    @Test
//    void match_att_lf_total() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_lf_total");
//    }
//
//    @Test
//    void match_open_play_pass() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("open_play_pass");
//    }

    @Test
    void match_aerial_won() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("aerial_won");
    }

//    @Test
//    void match_total_pass() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("total_pass");
//    }

//    @Test
//    void match_att_lf_target() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_lf_target");
//    }

    @Test
    void match_total_launches() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_launches");
    }

//    @Test
//    void match_fwd_pass() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("fwd_pass");
//    }

    @Test
    void match_outfielder_block() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("outfielder_block");
    }

    @Test
    void match_touches_in_opp_box() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("touches_in_opp_box");
    }

    @Test
    void match_total_corners_intobox() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_corners_intobox");
    }

//    @Test
//    void match_att_bx_centre() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_bx_centre");
//    }

    @Test
    void match_ontarget_att_assist() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("ontarget_att_assist");
    }

    @Test
    void match_long_pass_own_to_opp() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("long_pass_own_to_opp");
    }

    @Test
    void match_accurate_chipped_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_chipped_pass");
    }

//    @Test
//    void match_duel_won() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("duel_won");
//    }

    @Test
    void match_successful_final_third_passes() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("successful_final_third_passes");
    }

//    @Test
//    void match_fk_foul_won() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("fk_foul_won");
//    }

    @Test
    void match_total_cross_nocorner() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_cross_nocorner");
    }

    @Test
    void match_keeper_throws() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("keeper_throws");
    }

    @Test
    void match_successful_put_through() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("successful_put_through");
    }

    @Test
    void match_total_tackle() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_tackle");
    }

    @Test
    void match_passes_left() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("passes_left");
    }

    @Test
    void match_att_rf_target() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_rf_target");
    }

    @Test
    void match_accurate_launches() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_launches");
    }

    @Test
    void match_poss_lost_all() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("poss_lost_all");
    }

    @Test
    void match_att_sv_low_centre() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_sv_low_centre");
    }

    @Test
    void match_accurate_long_balls() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_long_balls");
    }

    @Test
    void match_challenge_lost() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("challenge_lost");
    }

    @Test
    void match_total_cross() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_cross");
    }

//    @Test
//    void match_att_obox_blocked() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_obox_blocked");
//    }

//    @Test
//    void match_att_ibox_miss() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_ibox_miss");
//    }

    @Test
    void match_accurate_goal_kicks() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_goal_kicks");
    }

    @Test
    void match_saved_obox() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("saved_obox");
    }

    @Test
    void match_unsuccessful_touch() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("unsuccessful_touch");
    }

//    @Test
//    void match_shot_off_target() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("shot_off_target");
//    }

    @Test
    void match_poss_lost_ctrl() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("poss_lost_ctrl");
    }

    @Test
    void match_att_ibox_blocked() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("att_ibox_blocked");
    }

    @Test
    void match_aerial_lost() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("aerial_lost");
    }

//    @Test
//    void match_att_sv_low_right() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_sv_low_right");
//    }

    @Test
    void match_crosses_18yard() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("crosses_18yard");
    }

    @Test
    void match_final_third_entries() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("final_third_entries");
    }

//    @Test
//    void match_att_hd_total() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_hd_total");
//    }

    @Test
    void match_final_effective_clearance() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("effective_clearance");
    }

//    @Test
//    void match_final_fk_foul_lost() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("fk_foul_lost");
//    }

    @Test
    void match_final_won_corners() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("won_corners");
    }

//    @Test
//    void match_final_possession_percentage() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("possession_percentage");
//    }

    @Test
    void match_interception() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("interception");
    }

    @Test
    void match_attempted_tackle_foul() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("attempted_tackle_foul");
    }

    @Test
    void match_backward_pass() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("backward_pass");
    }

    @Test
    void match_interception_won() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("interception_won");
    }

    @Test
    void match_pen_area_entries() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("pen_area_entries");
    }

    @Test
    void match_accurate_throws() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("accurate_throws");
    }

    @Test
    void match_total_layoffs() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_layoffs");
    }

//    @Test
//    void match_big_chance_missed() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("big_chance_missed");
//    }
//
//    @Test
//    void match_att_hd_target() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_hd_target");
//    }

    @Test
    void match_total_contest() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_contest");
    }

    @Test
    void match_total_clearance() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_clearance");
    }

    @Test
    void match_long_pass_own_to_opp_success() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("long_pass_own_to_opp_success");
    }

//    @Test
//    void match_accurate_corners_intobox() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("accurate_corners_intobox");
//    }

    @Test
    void match_attempts_obox() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("attempts_obox");
    }

    @Test
    void match_total_att_assist() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("total_att_assist");
    }

//    @Test
//    void match_att_miss_high() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_miss_high");
//    }

//    @Test
//    void match_offtarget_att_assist() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("offtarget_att_assist");
//    }

    @Test
    void match_attempts_ibox() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("attempts_ibox");
    }

//    @Test
//    void match_att_obox_miss() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_obox_miss");
//    }

    @Test
    void match_corner_taken() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("corner_taken");
    }

//    @Test
//    void match_att_miss_high_right() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("att_miss_high_right");
//    }

    @Test
    void match_crosses_18yardplus() throws ParserConfigurationException, IOException, SAXException {
        statisticMatcher("crosses_18yardplus");
    }

//    @Test
//    void match_punches() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("punches");
//    }
//
//    @Test
//    void match_formation_used() throws ParserConfigurationException, IOException, SAXException {
//        statisticMatcher("formation_used");
//    }
}
