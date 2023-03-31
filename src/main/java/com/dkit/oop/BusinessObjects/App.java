package com.dkit.oop.BusinessObjects;

import com.dkit.oop.DAOs.MySqlTeamDao;
import com.dkit.oop.DAOs.TeamDaoInterface;
import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import com.dkit.oop.Query;
import com.google.gson.GsonBuilder;
import com.sun.tools.jdeprscan.scan.Scan;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.*;
import java.net.Socket;
import java.util.Scanner;
import com.google.gson.Gson;

public class App
{
    public static void main(String[] args) throws DaoException {
        App app = new App();
        start();
    }

    private static void start() throws DaoException {


        Scanner in = new Scanner(System.in);

        /*try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please enter a command:  (\"Time\" to get time, or \"Echo message\" to get echo) \n>");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            if(command.startsWith("Time"))   //we expect the server to return a time
            {
                String timeString = socketReader.nextLine();
                System.out.println("Client message: Response from server Time: " + timeString);
            }
            else                            // the user has entered the Echo command or an invalid command
            {
                String input = socketReader.nextLine();
                System.out.println("Client message: Response from server: \"" + input + "\"");
            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }*/


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
                        System.out.println("Listing Teams By Budget");
                        List<Team> teamsByBudget = ITeamDao.listTeamsByBudget();
                        System.out.println(teamsByBudget+"\n");
                        break;

                    case 5:
                        System.out.println("Displaying Teams Over Budget");
                        break;

                    case 6:
                        System.out.println("Displaying Teams Under Budget");
                        break;

                    case 7:
                        System.out.println("Displaying Teams Over Wins");

                        break;

                    case 8:
                        System.out.println("Displaying Teams Under Wins");

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
        } catch (IOException e) {
            throw new RuntimeException(e);
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

        Team t1 = new Team(teamName, teamCountry, teamPowerUnit, teamWins, teamBudget);
        System.out.println("Team created: " + t1.toString());
        return t1;
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
    public static void menuDisplayOptions () throws IOException {
        Socket socket = new Socket("localhost", 8080);

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        BufferedReader bufferedReader = new BufferedReader(reader);



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

                        HashSet<Integer> teamIdCache = new HashSet<Integer>();
                        List<Team> teams = ITeamDao.findAllTeams();
                        for (Team t : teams) {
                            teamIdCache.add(t.getId());
                        }

                        int teamID;
                        do {
                            System.out.println("Enter team ID: ");
                            teamID = kb.nextInt();
                            if (!teamIdCache.contains(teamID)) {
                                System.out.println("Team ID does not exist. Please enter a valid team ID.");
                            }
                        } while (!teamIdCache.contains(teamID));

                        String sql = "SELECT * FROM teams WHERE id = ";
                        String parameters = Integer.toString(teamID);
                        Query query = new Query(sql, parameters);

                        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

                        String jsonQuery = gson.toJson(query);

                        writer.write("Find Team By ID: "+jsonQuery + "\n");
                        writer.flush();

                        // read response from server
                        String response = bufferedReader.readLine();
                        Team team = gson.fromJson(response, Team.class);
                        System.out.println(team);


                        break;

                    case 3:
                        System.out.println("Displaying Team By Name");
                        System.out.println("Enter team name:");
                        String teamName = kb.nextLine();
                        Team t1 = ITeamDao.findTeamByName(teamName);
                        System.out.println(t1 + "\n");
                        break;

                    case 4:
                        System.out.println("Displaying Teams By Country");
                        System.out.println("Enter country:");
                        String teamCountry = kb.nextLine();
                        List<Team> teamsByCountry = ITeamDao.findTeamsByCountry(teamCountry);
                        System.out.println(teamsByCountry + "\n");
                        break;

                    case 5:
                        System.out.println("Displaying Teams By Power Unit");
                        System.out.println("Enter power unit:");
                        String teamPowerUnit = kb.nextLine();
                        List<Team> teamsByPowerUnit = ITeamDao.findTeamsByPowerUnit(teamPowerUnit);
                        System.out.println(teamsByPowerUnit + "\n");
                        break;

                    case 6:
                        System.out.println("Displaying Teams Over Budget");
                        System.out.println("Enter budget: ");
                        while (!kb.hasNextFloat()) { //check if input is a float
                            System.out.println("Invalid input, please enter a number");
                            kb.nextLine();
                        }
                        float teamBudget = kb.nextFloat();
                        List<Team> teamsOverBudget = ITeamDao.findTeamsOverBudget(teamBudget);
                        System.out.println(teamsOverBudget + "\n");
                        break;

                    case 7:
                        System.out.println("Displaying Teams Under Budget");
                        System.out.println("Enter budget: ");
                        while (!kb.hasNextFloat()) { //check if input is a float
                            System.out.println("Invalid input, please enter a number");
                            kb.nextLine();
                        }
                        float teamBudget1 = kb.nextFloat();
                        List<Team> teamsUnderBudget = ITeamDao.findTeamsUnderBudget(teamBudget1);
                        System.out.println(teamsUnderBudget + "\n");
                        break;

                    case 8:
                        System.out.println("Displaying Teams Over Wins");
                        System.out.println("Enter wins: ");
                        while (!kb.hasNextInt()) { //check if input is an int
                            System.out.println("Invalid input, please enter a number");
                            kb.next();
                        }
                        int teamWins = kb.nextInt();
                        List<Team> teamsOverWins = ITeamDao.findTeamsOverWins(teamWins);
                        System.out.println(teamsOverWins + "\n");
                        break;

                    case 9:
                        System.out.println("Enter wins: ");
                        while (!kb.hasNextInt()) { //check if input is an int
                            System.out.println("Invalid input, please enter a number");
                            kb.next();
                        }
                        int teamWins1 = kb.nextInt();
                        System.out.println("Displaying Teams Under Wins");
                        List<Team> teamsUnderWins = ITeamDao.findTeamsUnderWins(teamWins1);
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
                        System.out.println("Enter team ID:");
                        int teamID = kb.nextInt();
                        Query query = new Query("SELECT * FROM team WHERE team_id = ?", Integer.toString(teamID));

                        Team t = ITeamDao.findTeamById(query);
                        System.out.println("Are you sure that you want to delete " + t.getName() + "?");
                        if (kb.nextLine().equalsIgnoreCase("yes"))
                        {
                            ITeamDao.deleteTeamById(teamID);
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
                        System.out.println("Enter team name:");
                        String teamName = kb.nextLine();
                        Team t1 = ITeamDao.findTeamByName(teamName);
                        System.out.println("Are you sure that you want to delete " + t1.getName() + "?");
                        if (kb.nextLine().equalsIgnoreCase("yes"))
                        {
                            ITeamDao.deleteTeamByName(teamName);
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

