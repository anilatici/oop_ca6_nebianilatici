package com.dkit.oop.Comparators;

import com.dkit.oop.DTOs.Team;
import java.util.Comparator;

public class TeamWinsComparator implements Comparator<Team> {
    @Override
    public int compare(Team t1, Team t2) {
        return Integer.compare(t1.getWins(), t2.getWins());
    }
}
