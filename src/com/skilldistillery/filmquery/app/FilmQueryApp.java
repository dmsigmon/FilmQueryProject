package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//
//		Actor actor = db.findActorById(1);
//		System.out.println(actor);
//
//		List<Actor> actors = new ArrayList<Actor>();
//		actors = db.findActorsByFilmId(1);
//		for (Actor actor2 : actors) {
//			System.out.println(actor2);
//		}
//	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		try {
			boolean flag = true;
			while (flag) {
				System.out.println("---Choose a menu Item---");
				System.out.println("1. Look up Film by ID");
				System.out.println("2. Look up Film by a Key Word");
				System.out.println("3. Exit the Application");

				int menuItem = input.nextInt();
				if (menuItem == 1) {
					System.out.println("Enter a film ID ");
					int filmId = input.nextInt();
					if (db.findFilmById(filmId) == null) {
						System.out.println("Movie not found");
					} else {
						System.out.println(db.findFilmById(filmId));
					}

				} else if (menuItem == 2) {
					System.out.println("Search by Keyword");
					String keyword = input.next();
					List<Film> keywordFilms = (ArrayList<Film>) db.findFilmByKeyword(keyword.toUpperCase());
					if (keywordFilms == null) {
						System.out.println("Movie not found");

					} else {
						for (Film film : keywordFilms) {
							System.out.println(film);
						}

					}
				} else if (menuItem == 3) {
					flag = false;
				} else {
					System.out.println("choose a valid option");
				}
			}
		} catch (Exception e) {
			System.out.println("Enter Correct Input");
		}

	}

}
