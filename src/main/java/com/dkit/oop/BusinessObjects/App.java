package com.dkit.oop.BusinessObjects;

import com.dkit.oop.DAOs.MySqlTeamDao;
import com.dkit.oop.DAOs.TeamDaoInterface;
import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        TeamDaoInterface ITeamDao = new MySqlTeamDao();

        try
        {
            System.out.println("\nCall findAllTeams()");
            List<Team> teams = ITeamDao.findAllTeams();     // call a method in the DAO

            if(teams.isEmpty() )
                System.out.println("There are no Teams");
            else {
                for (Team team : teams)
                    System.out.println("Team: " + team.toString());
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}