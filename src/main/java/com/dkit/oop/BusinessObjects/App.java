package com.dkit.oop.BusinessObjects;

import com.dkit.oop.DAOs.MySqlTeamDao;
import com.dkit.oop.DAOs.TeamDaoInterface;
import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import com.sun.tools.jdeprscan.scan.Scan;

import java.util.List;
import java.util.Scanner;

public class App
{
    private static void menuPrint()
    {
        System.out.println("Welcome to Formula 1");
        System.out.println("-----------------------------");
        System.out.println("1) Display All Teams");
        System.out.println("2) Find Team By Name");
        System.out.println("3) Find Teams By Country");
        System.out.println("4) Find Teams By Power Unit");
        System.out.println("5) Find Teams Over Budget");
        System.out.println("6) Find Teams Under Budget");
        System.out.println("7) Find Teams Over Wins");
        System.out.println("8) Find Teams Under Wins");
        System.out.println("9) Exit");
        System.out.println("-----------------------------");
        System.out.println("Enter the number of the option you want to select:");

    }

    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        TeamDaoInterface ITeamDao = new MySqlTeamDao();
        boolean menuLoop = true;
        int menuOption;

        try
        {
            while (menuLoop) {
                menuPrint();
                while (!kb.hasNextInt()) {
                    System.out.println("Invalid input, please enter a number");
                    kb.nextLine();
                }
                menuOption = kb.nextInt();
                kb.nextLine();
                if (menuOption < 1 || menuOption > 9) {
                    System.out.println("Invalid option, please try again");
                    continue;
                }

                switch (menuOption) {


                    case 1:
                        System.out.println("Displaying All Teams");
                        List<Team> teamsAll = ITeamDao.findAllTeams();
                        System.out.println(teamsAll+"\n");
                        break;

                    case 2:
                        System.out.println("Displaying Team By Name");
                        Team t = ITeamDao.findTeamByName();
                        System.out.println(t+"\n");
                        break;

                    case 3:
                        System.out.println("Displaying Teams By Country");
                        List<Team> teamsByCountry = ITeamDao.findTeamsByCountry();
                        System.out.println(teamsByCountry+"\n");
                        break;

                    case 4:
                        System.out.println("Displaying Teams By Power Unit");
                        List<Team> teamsByPowerUnit = ITeamDao.findTeamsByPowerUnit();
                        System.out.println(teamsByPowerUnit+"\n");
                        break;

                    case 5:
                        System.out.println("Displaying Teams Over Budget");
                        List<Team> teamsOverBudget = ITeamDao.findTeamsOverBudget();
                        System.out.println(teamsOverBudget+"\n");
                        break;

                    case 6:
                        System.out.println("Displaying Teams Under Budget");
                        List<Team> teamsUnderBudget = ITeamDao.findTeamsUnderBudget();
                        System.out.println(teamsUnderBudget+"\n");
                        break;

                    case 7:
                        System.out.println("Displaying Teams Over Wins");
                        List<Team> teamsOverWins = ITeamDao.findTeamsOverWins();
                        System.out.println(teamsOverWins+"\n");
                        break;

                    case 8:
                        System.out.println("Displaying Teams Under Wins");
                        List<Team> teamsUnderWins = ITeamDao.findTeamsUnderWins();
                        System.out.println(teamsUnderWins+"\n");
                        break;
                    case 9:
                        menuLoop = false;
                        break;

                    default:
                        System.out.println("Invalid option, please try again");
                        break;
                }
            }
/*            if(teams.isEmpty() )
                System.out.println("There are no Teams");
            else {
                for (Team team : teams)
                    System.out.println("Team: " + team.toString());
            }*/
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}