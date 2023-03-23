package com.dkit.oop.DAOs;

import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import com.sun.tools.jdeprscan.scan.Scan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MySqlTeamDao extends MySqlDao implements TeamDaoInterface {
    Scanner kb = new Scanner(System.in);
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    @Override
    public List<Team> findAllTeams() throws DaoException {

        List<Team> teamsList = new ArrayList<>();
        try
        {
            connection = this.getConnection();

            String query = "select * from teams";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                Team t = new Team(id, name, country, powerUnit, wins, budget);
                teamsList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllTeamsResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllTeams() " + e.getMessage());
            }
        }
        return teamsList;     // may be empty
    }

    @Override
    public Team findTeamByName() throws DaoException {
        System.out.println("Enter team name:");
        String teamName = kb.nextLine();
        Team t = null;
        try {
            connection = this.getConnection();
            String query = "select * from teams where name like '%" + teamName + "%'";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                t = new Team(id, name, country, powerUnit, wins, budget);
            }
        } catch (SQLException e) {
            throw new DaoException("findTeamByNameResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findTeamByName() " + e.getMessage());
            }
        }
        return t;
    }

    @Override
    public List<Team> findTeamsByCountry() throws DaoException {
        System.out.println("Enter country:");
        String teamCountry = kb.nextLine();
        List<Team> teamsList = new ArrayList<>();
        try
        {
            connection = this.getConnection();

            String query = "select * from teams where country like '%" + teamCountry + "%'" ;
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                Team t = new Team(id, name, country, powerUnit, wins, budget);
                teamsList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findTeamsByCountryResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findTeamsByCountry() " + e.getMessage());
            }
        }
        return teamsList;
    }

    @Override
    public List<Team> findTeamsByPowerUnit() throws DaoException {
        System.out.println("Enter power unit:");
        String teamPowerUnit = kb.nextLine();
        List<Team> teamsList = new ArrayList<>();
        try
        {
            connection = this.getConnection();

            String query = "select * from teams where powerUnit like '%" + teamPowerUnit + "%'" ;
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                Team t = new Team(id, name, country, powerUnit, wins, budget);
                teamsList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findTeamsByPowerUnitResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findTeamsByPowerUnit() " + e.getMessage());
            }
        }
        return teamsList;
    }

    @Override
    public List<Team> findTeamsOverBudget() throws DaoException {
        System.out.println("Enter budget: ");
        while (!kb.hasNextFloat()) { //check if input is a float
            System.out.println("Invalid input, please enter a number");
            kb.nextLine();
        }
        float teamBudget = kb.nextFloat();
        List<Team> teamsList = new ArrayList<>();
        try
        {
            connection = this.getConnection();

            String query = "select * from teams where budget > " + teamBudget;
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                Team t = new Team(id, name, country, powerUnit, wins, budget);
                teamsList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findTeamsOverBudgetResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findTeamsOverBudget() " + e.getMessage());
            }
        }
        return teamsList;
    }
    @Override
    public List<Team> findTeamsUnderBudget() throws DaoException {
        System.out.println("Enter budget: ");
        while (!kb.hasNextFloat()) { //check if input is a float
            System.out.println("Invalid input, please enter a number");
            kb.nextLine();
        }
        float teamBudget = kb.nextFloat();
        List<Team> teamsList = new ArrayList<>();
        try
        {
            connection = this.getConnection();

            String query = "select * from teams where budget < " + teamBudget;
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                Team t = new Team(id, name, country, powerUnit, wins, budget);
                teamsList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findTeamsUnderBudgetResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findTeamsUnderBudget() " + e.getMessage());
            }
        }
        return teamsList;
    }

    @Override
    public List<Team> findTeamsOverWins() throws DaoException {
        System.out.println("Enter wins: ");
        while (!kb.hasNextInt()) { //check if input is an int
            System.out.println("Invalid input, please enter a number");
            kb.next();
        }
        int teamWins = kb.nextInt();
        List<Team> teamsList = new ArrayList<>();
        try
        {
            connection = this.getConnection();

            String query = "select * from teams where wins > " + teamWins;
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                Team t = new Team(id, name, country, powerUnit, wins, budget);
                teamsList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findTeamsOverWinsResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findTeamsOverWins() " + e.getMessage());
            }
        }
        return teamsList;
    }
    @Override
    public List<Team> findTeamsUnderWins() throws DaoException {
        System.out.println("Enter wins: ");
        while (!kb.hasNextInt()) { //check if input is an int
            System.out.println("Invalid input, please enter a number");
            kb.next();
        }
        int teamWins = kb.nextInt();
        List<Team> teamsList = new ArrayList<>();
        try
        {
            connection = this.getConnection();

            String query = "select * from teams where wins < " + teamWins;
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String powerUnit = resultSet.getString("powerUnit");
                int wins = resultSet.getInt("wins");
                float budget = resultSet.getFloat("budget");
                Team t = new Team(id, name, country, powerUnit, wins, budget);
                teamsList.add(t);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findTeamsUnderWinsResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findTeamsUnderWins() " + e.getMessage());
            }
        }
        return teamsList;
    }

    @Override
    public void deleteTeamByName() throws DaoException {
        System.out.println("Enter team name: ");
        String teamName = kb.next();
        try
        {
            connection = this.getConnection();

            String query = "delete from teams where name = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, teamName);

            //Using a PreparedStatement to execute SQL...
            ps.executeUpdate();
        } catch (SQLException e)
        {
            throw new DaoException("deleteTeamByNameResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("deleteTeamByName() " + e.getMessage());
            }
        }
    }


}
