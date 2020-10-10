package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
  static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
  public Film findFilmById(int filmId) throws SQLException;
  public Actor findActorById(int actorId) throws SQLException;
  public List<Actor> findActorsByFilmId(int filmId) throws SQLException;
  public List<Film> findFilmByKeyword(String keyword) throws SQLException;
}
