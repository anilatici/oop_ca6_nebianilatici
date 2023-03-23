package com.dkit.oop.DAOs;

import com.dkit.oop.DAOs.MySqlDao;
import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlTeamDao extends MySqlDao implements TeamDaoInterface {

    @Override
    public List<Team> findAllTeams() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
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
}
