package com.dkit.oop.DAOs;

import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import com.dkit.oop.Query;

import java.util.List;

public interface TeamDaoInterface
{
    Team findTeamById(Query query) throws DaoException;
    List<Team> findAllTeams() throws DaoException;
    Team findTeamByName(String teamName) throws DaoException;
    List<Team> findTeamsByCountry(String teamCountry) throws DaoException;
    List<Team> findTeamsByPowerUnit(String teamPowerUnit) throws DaoException;
    List<Team> findTeamsOverBudget(float teamBudget) throws DaoException;
    List<Team> findTeamsUnderBudget(float teamBudget) throws DaoException;
    List<Team> findTeamsOverWins(int teamWins) throws DaoException;
    List<Team> findTeamsUnderWins(int teamWins) throws DaoException;
    void deleteTeamByName(String teamName) throws DaoException;
    void deleteTeamById(int teamId) throws DaoException;
    void insertNewTeam(Team t) throws DaoException;
    public List<Team> listTeamsByBudget() throws DaoException;

}