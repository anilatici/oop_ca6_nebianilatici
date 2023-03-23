package com.dkit.oop.BusinessObjects;

import com.dkit.oop.DAOs.MySqlTeamDao;
import com.dkit.oop.DAOs.TeamDaoInterface;
import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import com.sun.tools.jdeprscan.scan.Scan;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        App app = new App();
        start();
    }

    private static void start() {
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
                        menuDisplayOptions();
                        break;

                    case 2:
                        menuDeleteOptions();
                        break;

                    case 3:
                        ITeamDao.insertNewTeam(insertNewTeam());
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

    private static Team insertNewTeam() {
        Scanner kb = new Scanner(System.in);

        String teamName = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Enter Team Name:");
            teamName = kb.nextLine();

            // Validate that the team name is not empty
            if (teamName.isEmpty()) {
                System.out.println("Team name cannot be empty. Please enter a valid team name.");
            }
            // Validate that the team name does not contain digits
            else if (teamName.matches(".*\\d.*")) {
                System.out.println("Team name cannot contain digits. Please enter a valid team name.");
            }
            else {
                validInput = true;
            }
        }

        String teamCountry = "";
        validInput = false;
        while (!validInput) {
            System.out.println("Enter Team Country:");
            teamCountry = kb.nextLine();

            // Validate that the team country is not empty
            if (teamCountry.isEmpty()) {
                System.out.println("Team country cannot be empty. Please enter a valid team country.");
            }
            // Validate that the team country does not contain digits
            else if (teamCountry.matches(".*\\d.*")) {
                System.out.println("Team country cannot contain digits. Please enter a valid team country.");
            }
            else {
                validInput = true;
            }
        }

        String teamPowerUnit = "";
        validInput = false;
        while (!validInput) {
            System.out.println("Enter Team Power Unit:");
            teamPowerUnit = kb.nextLine();

            // Validate that the team power unit is not empty
            if (teamPowerUnit.isEmpty()) {
                System.out.println("Team power unit cannot be empty. Please enter a valid team power unit.");
            }
            // Validate that the team power unit does not contain digits
            else if (teamPowerUnit.matches(".*\\d.*")) {
                System.out.println("Team power unit cannot contain digits. Please enter a valid team power unit.");
            }
            else {
                validInput = true;
            }
        }

        int teamWins = 0;
        validInput = false;
        while (!validInput) {
            System.out.println("Enter Team Wins:");
            try {
                teamWins = Integer.parseInt(kb.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Team wins must be an integer. Please enter a valid number:");
            }
        }

        int teamBudget = 0;
        validInput = false;
        while (!validInput) {
            System.out.println("Enter Team Budget:");
            try {
                teamBudget = Integer.parseInt(kb.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Team budget must be an integer. Please enter a valid number:");
            }
        }

        return new Team(teamName, teamCountry, teamPowerUnit, teamWins, teamBudget);
    }

    private static void menuPrint()
    {
        System.out.println("Welcome to Formula 1");
        System.out.println("-----------------------------");
        System.out.println("1) Display Options");
        System.out.println("2) Delete Options");
        System.out.println("3) Insert New Team");
        System.out.println("9) Exit");
        System.out.println("-----------------------------");
        System.out.println("Enter the number of the option you want to select:");

    }
    public static void menuDisplayOptions ()
    {
        boolean menuLoop = true;
        Scanner kb = new Scanner(System.in);
        TeamDaoInterface ITeamDao = new MySqlTeamDao();
        int displayOption = 0;
        try{
            while (menuLoop) {
                System.out.println("Display Options:");
                System.out.println("1) Display All Teams");
                System.out.println("2) Find Team By ID");
                System.out.println("3) Find Team By Name");
                System.out.println("4) Find Teams By Country");
                System.out.println("5) Find Teams By Power Unit");
                System.out.println("6) Find Teams Over Budget");
                System.out.println("7) Find Teams Under Budget");
                System.out.println("8) Find Teams Over Wins");
                System.out.println("9) Find Teams Under Wins");
                System.out.println("10) Return to Main Menu");
                System.out.println("Enter the number of the option you want to select:");
                while (!kb.hasNextInt()) {
                    System.out.println("Invalid input, please enter a number");
                    kb.nextLine();
                }
                displayOption = kb.nextInt();

                if (displayOption < 1 || displayOption > 10) {
                    System.out.println("Invalid option, please try again");
                    continue;
                }

                kb.nextLine(); // consume the newline
                switch (displayOption) {
                    case 1:
                        System.out.println("Displaying All Teams");
                        List<Team> teamsAll = ITeamDao.findAllTeams();
                        System.out.println(teamsAll + "\n");
                        break;

                    case 2:
                        System.out.println("Displaying Team By ID");
                        Team t = ITeamDao.findTeamById();
                        System.out.println(t + "\n");
                        break;

                    case 3:
                        System.out.println("Displaying Team By Name");
                        Team t1 = ITeamDao.findTeamByName();
                        System.out.println(t1 + "\n");
                        break;

                    case 4:
                        System.out.println("Displaying Teams By Country");
                        List<Team> teamsByCountry = ITeamDao.findTeamsByCountry();
                        System.out.println(teamsByCountry + "\n");
                        break;

                    case 5:
                        System.out.println("Displaying Teams By Power Unit");
                        List<Team> teamsByPowerUnit = ITeamDao.findTeamsByPowerUnit();
                        System.out.println(teamsByPowerUnit + "\n");
                        break;

                    case 6:
                        System.out.println("Displaying Teams Over Budget");
                        List<Team> teamsOverBudget = ITeamDao.findTeamsOverBudget();
                        System.out.println(teamsOverBudget + "\n");
                        break;

                    case 7:
                        System.out.println("Displaying Teams Under Budget");
                        List<Team> teamsUnderBudget = ITeamDao.findTeamsUnderBudget();
                        System.out.println(teamsUnderBudget + "\n");
                        break;

                    case 8:
                        System.out.println("Displaying Teams Over Wins");
                        List<Team> teamsOverWins = ITeamDao.findTeamsOverWins();
                        System.out.println(teamsOverWins + "\n");
                        break;

                    case 9:
                        System.out.println("Displaying Teams Under Wins");
                        List<Team> teamsUnderWins = ITeamDao.findTeamsUnderWins();
                        System.out.println(teamsUnderWins + "\n");
                        break;

                    case 10:
                        System.out.println("Returning to Main Menu");
                        menuLoop = false;
                        break;

                    default:
                        System.out.println("Invalid option, please try again");
                        break;
                }
            }
        }
        catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void menuDeleteOptions()
    {
        boolean menuLoop = true;
        Scanner kb = new Scanner(System.in);
        TeamDaoInterface ITeamDao = new MySqlTeamDao();
        int deleteOption = 0;
        try {
            while (menuLoop) {
                System.out.println("Delete Options:");
                System.out.println("1) Delete Team By ID");
                System.out.println("2) Delete Team By Name");
                System.out.println("3) Return to Main Menu");
                System.out.println("Enter the number of the option you want to select:");
                while (!kb.hasNextInt()) {
                    System.out.println("Invalid input, please enter a number");
                    kb.nextLine();
                }
                deleteOption = kb.nextInt();

                if (deleteOption < 1 || deleteOption > 3) {
                    System.out.println("Invalid option, please try again");
                    continue;
                }

                kb.nextLine(); // consume the newline
                switch (deleteOption) {
                    case 1:
                        System.out.println("Deleting Team By ID");
                        Team t = ITeamDao.findTeamById();
                        System.out.println("Are you sure that you want to delete " + t.getName() + "?");
                        if (kb.nextLine().equalsIgnoreCase("yes"))
                        {
                            ITeamDao.deleteTeamById();
                            System.out.println(t + " has been deleted");
                            break;
                        }
                        else
                        {
                            System.out.println("Returning to Main Menu");
                            menuLoop = false;
                            break;
                        }


                    case 2:
                        System.out.println("Deleting Teams By Name");
                        Team t1 = ITeamDao.findTeamByName();
                        System.out.println("Are you sure that you want to delete " + t1.getName() + "?");
                        if (kb.nextLine().equalsIgnoreCase("yes"))
                        {
                            ITeamDao.deleteTeamByName();
                            System.out.println(t1 + " has been deleted");
                            break;
                        }
                        else
                        {
                            System.out.println("Returning to Main Menu");
                            menuLoop = false;
                            break;
                        }

                    case 3:
                        System.out.println("Returning to Main Menu");
                        menuLoop = false;
                        break;

                    default:
                        System.out.println("Invalid option, please try again");
                        break;

                }
            }

        }
        catch (DaoException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

