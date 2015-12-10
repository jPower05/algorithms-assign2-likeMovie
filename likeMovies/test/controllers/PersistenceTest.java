package controllers;
import controllers.MovieApi;
import static org.junit.Assert.*;

import java.io.File;

import models.Movie;
import models.Rating;
import models.User;

import org.junit.Test;

import utils.Serializer;
import utils.XMLSerializer;
import static models.Fixtures.users;
import static models.Fixtures.movies;
import static models.Fixtures.ratings;

public class PersistenceTest
{
  MovieApi movieApi;
  
  void populate (	MovieApi movieApi)
  {
    for (User user : users)
    {
      movieApi.createUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
    }
    User user1 = movieApi.getUser(users[0].id);
    Movie movie = movieApi.addMovie(user1.id, movies[0].name, movies[0].year, movies[0].url);
    movieApi.addMovie(user1.id, movies[1].name, movies[1].year, movies[1].url);
    User user2 = movieApi.getUser(users[1].id);
    movieApi.addMovie(user2.id, movies[2].name, movies[2].year, movies[2].url);
    movieApi.addMovie(user2.id, movies[3].name, movies[3].year, movies[3].url);
    
    for (Rating rating : ratings)
    {
      movieApi.addRating(movie.mid, rating.rating);
    }
  }
  
  @Test
  public void testPopulate()
  { 
    movieApi = new MovieApi(null);
    assertEquals(0, movieApi.getUsers().size());
    populate (movieApi);
    
    assertEquals(users.length, movieApi.getUsers().size());
    assertEquals(2, movieApi.getUser(users[0].id).movies.size());
    assertEquals(2, movieApi.getUser(users[1].id).movies.size());   
    Long movieID = movieApi.getUser(users[0].id).movies.keySet().iterator().next();
    assertEquals(ratings.length, movieApi.getMovie(movieID).route.size());   
  }

  void deleteFile(String fileName)
  {
    File datastore = new File ("testdatastore.xml");
    if (datastore.exists())
    {
      datastore.delete();
    }
  }
  
  @Test
  public void testXMLSerializer() throws Exception
  { 
    String datastoreFile = "testdatastore.xml";
    deleteFile (datastoreFile);
    
    Serializer serializer = new XMLSerializer(new File (datastoreFile));
    
    movieApi = new MovieApi(serializer); 
    populate(movieApi);
    movieApi.store();
    
    MovieApi movieApi2 =  new MovieApi(serializer);
    movieApi2.load();
    
    assertEquals (movieApi.getUsers().size(), movieApi2.getUsers().size());
    for (User user : movieApi.getUsers())
    {
      assertTrue (movieApi2.getUsers().contains(user));
    }
    deleteFile ("testdatastore.xml");
  }
  
  
}