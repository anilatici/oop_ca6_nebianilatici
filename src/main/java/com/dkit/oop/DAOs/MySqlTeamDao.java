package com.dkit.oop.DAOs;

import com.dkit.oop.Comparators.TeamBudgetComparator;
import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import com.sun.tools.jdeprscan.scan.Scan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class MySqlTeamDao extends MySqlDao implements TeamDaoInterface {

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
    public Team findTeamById(int teamID) throws DaoException {
        Team t = null;
        try {
            connection = this.getConnection();
            String query = "select * from teams where id = " + teamID;
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
            throw new DaoException("findTeamByIDResultSet() " + e.getMessage());
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
                throw new DaoException("findTeamByID() " + e.getMessage());
            }
        }
        return t;
    }
    @Override
    public Team findTeamByName(String teamName) throws DaoException {
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
    public List<Team> findTeamsByCountry(String teamCountry) throws DaoException {
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
    public List<Team> findTeamsByPowerUnit(String teamPowerUnit) throws DaoException {
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
    public List<Team> findTeamsOverBudget(float teamBudget) throws DaoException {

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
    public List<Team> findTeamsUnderBudget(float teamBudget) throws DaoException {

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
    public List<Team> findTeamsOverWins(int teamWins) throws DaoException {

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
    public List<Team> findTeamsUnderWins(int teamWins) throws DaoException {

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


    //create a function to delete a team from the database table using the team id
    @Override
    public void deleteTeamById(int teamId) throws DaoException {

        try
        {
            connection = this.getConnection();

            String query = "delete from teams where id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, teamId);

            //Using a PreparedStatement to execute SQL...
            ps.executeUpdate();
        } catch (SQLException e)
        {
            throw new DaoException("deleteTeamByIdResultSet() " + e.getMessage());
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
                throw new DaoException("deleteTeamById() " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteTeamByName(String teamName) throws DaoException {

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

    @Override
    public void insertNewTeam(Team t) throws DaoException {
        try
        {
            connection = this.getConnection();

            String query = "insert into teams (name, country, powerUnit, budget, wins) values (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, t.getName());
            ps.setString(2, t.getCountry());
            ps.setString(3, t.getPowerUnit());
            ps.setFloat(4, t.getBudget());
            ps.setInt(5, t.getWins());

            //Using a PreparedStatement to execute SQL...
            ps.executeUpdate();
        } catch (SQLException e)
        {
            throw new DaoException("insertNewTeamResultSet() " + e.getMessage());
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
                throw new DaoException("insertNewTeam() " + e.getMessage());
            }
        }
    }

    @Override
    public List<Team> listTeamsByBudget() throws DaoException {
        MySqlTeamDao teamDao = new MySqlTeamDao();
        List<Team> teamsList = teamDao.findAllTeams();
        teamsList.sort(new TeamBudgetComparator());
        return teamsList;
    }


}
