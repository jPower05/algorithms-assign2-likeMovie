package controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.MovieApi;
import models.Rating;
import models.Movie;
import models.User;
import static models.Fixtures.users;
import static models.Fixtures.ratings;
import static models.Fixtures.movies;


public class APITest
{
  private MovieApi movieApi;

  @Before
  public void setup()
  {
    movieApi = new MovieApi();
    for (User user : users)
    {
      movieApi.createUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
    
    }
  }
  @After
  public void tearDown()
  {
    movieApi = null;
  }

  @Test
  public void testUser()
  {
    assertEquals (users.length, movieApi.getUsers().size());
    movieApi.createUser("Joe", "Blogs", 23, "M ", "fireman");
    assertEquals (users.length+1, movieApi.getUsers().size()); 
  }  
  
  @Test
  public void testUsers()
  {
    assertEquals (users.length, movieApi.getUsers().size());
  }
  
  @Test
  public void testDeleteUser()
  {
	  assertEquals (users.length, movieApi.getUsers().size());
	  User john  = movieApi.getUser(users[0].id);
	  movieApi.deleteUser(john.id);
	  assertEquals (users.length-1, movieApi.getUsers().size());
  }

  @Test
  public void testMovie()
  {
	  assertEquals (movies.length, movieApi.getMovies().size());
	  movieApi.createMovie("Cars", "2005", "www.cars.com");
	  assertEquals (users.length+1, movieApi.getMovies().size());
  }
  
  @Test
  public void testAddUserMovie()
  {
	  User john = movieApi.getUser(users[0].id);
	  Movie movie = movieApi.addMovie(john.id, movies[0].name, movies[0].year,movies[0].url);
	  Movie returnedMovie  = movieApi.getMovie(movie.mid);
	  assertEquals(movies[0],  returnedMovie);
	  assertNotSame(movies[0], returnedMovie);
	  
  }
  
  @Test
  public void testAddMovieWithSingleRating()
  {
    User john = movieApi.getUser(users[0].id);
    Long movieId = movieApi.addMovie(john.id, movies[0].name, movies[0].year, movies[0].url).mid;

    movieApi.addRating(movieId, ratings[0].rating);

    Movie movie = movieApi.getMovie(movieId);
    assertEquals (1, movie.route.size());
    assertEquals(4, ratings[0].rating,  movie.route.get(0).rating);   
  }

}


