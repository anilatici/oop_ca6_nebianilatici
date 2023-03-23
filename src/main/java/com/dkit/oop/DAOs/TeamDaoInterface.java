package com.dkit.oop.DAOs;

import com.dkit.oop.DTOs.Team;
import com.dkit.oop.Exceptions.DaoException;
import java.util.List;

public interface TeamDaoInterface
{
    public List<Team> findAllTeams() throws DaoException;

}