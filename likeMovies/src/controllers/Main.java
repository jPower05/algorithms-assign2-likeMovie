package controllers;

import java.io.File;
import java.util.Collection;

import com.google.common.base.Optional;

import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.User;

/*************************************************************************
 * 
 * The <tt>Main.java</tt> class
 * <p>
 * A class that deals with the api commands using the cliche library.
 * 
 * 
 * @author James Power 20067779
 *************************************************************************/



public class Main
{
  public MovieApi movieApi;

  public Main() throws Exception
  {
    File datastore = new File("datastore.xml");
    Serializer serializer = new XMLSerializer(datastore);

    movieApi = new MovieApi(serializer);
    if (datastore.isFile())
    {
      movieApi.load();
    }
  }
  
  @Command(description="Get all users details")
  public void getUsers ()
  {
    Collection<User> users = movieApi.getUsers();
    System.out.println(users);
  }
  
  @Command(description="Add a new User")
  public void createUser(@Param(name="first name") String firstName, @Param(name="last name") String lastName,
  		               @Param(name="age") int age, @Param(name="gender") String gender,
  		               @Param(name="occupation") String occupation)
  {
  	User user = movieApi.createUser(firstName, lastName, age, gender, occupation);
  	System.out.println(user);
  }
  
  @Command(description="Deletes a user")
  public void deleteUser(@Param(name="id")long id)
  {
  	movieApi.deleteUser(id);
  	
  }
  
  @Command(description="Gets a user from a user id")
  public void getUser(@Param(name="user-id")long id)
  {
  	User user = movieApi.getUser(id);
  	System.out.println(user);
  		
  }
  
  
  
  
  
  @Command(description="Adds a movie")
  public void createMovie(@Param(name="name")String name, @Param(name="year") String year, 
  		                @Param(name="url") String url)
  {
  	Movie movie = movieApi.createMovie(name, year, url);
  	System.out.println(movie);
  	
  }
  
  @Command(description="Deletes a movie")
  public void deleteMovie(@Param(name="movie-id") long id)
  {
  	movieApi.deleteMovie(id);
  }
  
  @Command(description="Gets a movie from a movie id")
  public void getMovie(@Param(name="movie-id")long id)
  {
  	Movie movie = movieApi.getMovie(id);
  	System.out.println(movie);
  		
  }
  
  
  
  
  
  @Command(description="Add a rating to a movie")
  public void addRating(@Param(name="movie-id") long id, @Param(name="rating")int rating)
  {
  	Optional<Movie> movie = Optional.fromNullable(movieApi.getMovie(id));
  	if(movie.isPresent())
		{
  		movieApi.addRating(movie.get().mid, rating);
  		System.out.println("Given" + movie + "a rating of" + rating);
		      
		}
  }
  
  
  
  @Command(description="Add a movie to a user")
  public void addMovie(@Param(name="user-id") long id, @Param(name="movie-name")String name, 
		                @Param(name="year") String year, @Param(name="url")String url)
  {
	  Optional<User> user = Optional.fromNullable(movieApi.getUser(id));
	    if (user.isPresent())
	    {
	      movieApi.addMovie(id, name, year, url);
	    }
  }
  
  
  
  @Command(description="prime")
  public void prime () throws Exception
  {
	  movieApi.prime();
  }
  
  
  
  
  
  

  
  
  
  
  
  
  
  
  
  
  
  public static void main(String[] args) throws Exception
  {
    Main main = new Main();

    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions", main);
    shell.commandLoop();

    main.movieApi.store();
  }
}