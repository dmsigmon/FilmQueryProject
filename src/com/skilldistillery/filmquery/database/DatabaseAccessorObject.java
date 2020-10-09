package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

// where connectors driver etc
public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String sql = "SELECT * FROM film WHERE id = ?";
		String user = "student";
		String pass = "student";
		
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			film = new Film();
			film.setId(filmId);
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getString("releaseYear"));
			film.setLanguage(rs.getString("name"));
		}


		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		String sql = "";
		String user = "student";
		String pass = "student";
		
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		String sql = "";
		String user = "student";
		String pass = "student";
		
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		return null;
	}

}
