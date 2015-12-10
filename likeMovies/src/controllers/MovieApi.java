package controllers;

import java.util.Collection;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.CSVLoader;
import utils.Serializer;
import models.Movie;
import models.Rating;
import models.User;


/*************************************************************************
 * The <tt>MovieApi.java</tt> class
 * <p>
 * A class that manages a movie Api system. 
 * 
 * 
 * @author James Power 20067779
 *************************************************************************/
public class MovieApi
{
  private Serializer serializer;
  
  private Map<Long,   User>   userIndex       = new HashMap<>();
  private Map<Long, Movie> movieIndex = new HashMap<>();
  
  
  public MovieApi()
  {}
  
  public MovieApi(Serializer serializer)
  {
    this.serializer = serializer;
  }
  
  @SuppressWarnings("unchecked")
  public void load() throws Exception
  {
    serializer.read();
    movieIndex = (Map<Long, Movie>) serializer.pop(); 
    userIndex       = (Map<Long, User>)     serializer.pop();
    //User.counter = (Long) serializer.pop();
  }
  
  public void store() throws Exception
  {
    serializer.push(userIndex);
    serializer.push(movieIndex);
    //serializer.push(User.counter);
    serializer.write(); 
    
  }
  
  
  
  
  
  public Collection<User> getUsers ()
  {
    return userIndex.values();
  }
  
  public  void deleteUsers() 
  {
    userIndex.clear();
  }
  
  public User createUser(String firstName, String lastName, int age, String gender, String occupation) 
  {
    User user = new User (firstName, lastName, age, gender, occupation);
    userIndex.put(user.id, user);
    return user;
  }
  
  public User getUser(Long id) 
  {
    return userIndex.get(id);
  }

  public void deleteUser(Long id) 
  {
    User user = userIndex.remove(id);
  }
  
  
  
  
  
  public Collection<Movie> getMovies()
  {
	  return movieIndex.values();
  }
  
  public Movie createMovie(String name, String year, String url)
  {
	  Movie movie = new Movie (name, year, url);
	  movieIndex.put(movie.mid, movie);
	  return movie;
  }
  
  public Movie addMovie(Long id, String name, String year, String url)
  {
	  Movie movie = null;
	  Optional<User> user = Optional.fromNullable(userIndex.get(id));
	  if (user.isPresent())
	  {
		  movie = new Movie (name, year, url);
		  user.get().movies.put(movie.mid, movie);
		  movieIndex.put(movie.mid,  movie);
	  }
	  return movie; 
  }
  
  public void deleteMovie(long id)
  {
	  movieIndex.remove(id);
  }
  
  public Movie getMovie (long id)
  {
	  return movieIndex.get(id);
  }

  
  
  
  
  
  public void addRating(long mid, int rating)
  {
	  
	  Optional<Movie> movie = Optional.fromNullable(movieIndex.get(mid));
	  if(movie.isPresent())
	  {
	      movie.get().route.add (new Rating(rating));
	      
	  }
	  
  }
  
  
  
  
 /* public Movie topTen(long mid, int rating)
  {
	  
	  Optional<Movie> movie = Optional.fromNullable(movieIndex.get(mid));
	  for (int i = 0 ; i < movieIndex.size() ; i++)
	  {
		 movie.get().route.get(rating); 
		 
		 
		 return movie;
	  }
   }*/	   
		 
		 
  public void prime() throws Exception
  {
	  CSVLoader loader = new CSVLoader();
	  List <User> users = loader.loadUsers("DatFiles/ratings5.dat");
	  for (User user : users)
	  {
		  userIndex.put(user.id,user);
	  }	 
		 
	  List <Movie> movies = loader.loadMovies("DatFiles/items5.dat");
	  for (Movie movie : movies)
	  {
		  movieIndex.put(movie.mid,movie);
	  }	 
   
  }
}
