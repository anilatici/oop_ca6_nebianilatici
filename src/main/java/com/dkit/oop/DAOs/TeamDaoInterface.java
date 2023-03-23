package com.dkit.oop.DAOs;

import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import java.util.List;

public interface TeamDaoInterface
{
    List<Team> findAllTeams() throws DaoException;
    Team findTeamByName() throws DaoException;
    List<Team> findTeamsByCountry() throws DaoException;
    List<Team> findTeamsByPowerUnit() throws DaoException;
    List<Team> findTeamsOverBudget() throws DaoException;
    List<Team> findTeamsUnderBudget() throws DaoException;
    List<Team> findTeamsOverWins() throws DaoException;
    List<Team> findTeamsUnderWins() throws DaoException;
    void deleteTeamByName() throws DaoException;

}