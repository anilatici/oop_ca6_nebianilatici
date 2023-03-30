package com.dkit.oop.Comparators;
import com.dkit.oop.DTOs.Team;
import java.util.Comparator;

public class TeamBudgetComparator implements Comparator<Team> {
    @Override
    public int compare(Team t1, Team t2) {
        return Float.compare(t2.getBudget(), t1.getBudget());
    }
}
