package com.dkit.oop.DAOs;

import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import java.util.List;

public interface TeamDaoInterface
{
    List<Team> findAllTeams() throws DaoException;
    Team findTeamByName(String name) throws DaoException;
}