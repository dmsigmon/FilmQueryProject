package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

// where connectors driver etc
public class DatabaseAccessorObject implements DatabaseAccessor {

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
		String sql = "SELECT * FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			film = new Film();
			film.setId(filmId);
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
//			film.setReleaseYear(rs.getString("releaseYear"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating("rating");
			film.setSpecialFeatures("special_features");
			film.setLanguage(rs.getString("name"));
			film.setActors(findActorsByFilmId(filmId));
		}
		conn.close();
		stmt.close();
		rs.close();
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			actor = new Actor();
			actor.setId(actorId);
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));

		}

		conn.close();
		stmt.close();
		rs.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?;";
		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Actor actor = new Actor();
			actor.setId(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));

			actors.add(actor);
		}

		conn.close();
		stmt.close();
		rs.close();
		return actors;

	}

}
