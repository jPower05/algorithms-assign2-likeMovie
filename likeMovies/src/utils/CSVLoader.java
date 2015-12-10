package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.Movie;
import models.Rating;
import models.User;
import edu.princeton.cs.introcs.In;

public class CSVLoader
{
 public List<User> loadUsers(String filename) throws Exception
 {
	 
	 ArrayList <User> userResult = new ArrayList<User>();
	 File usersFile = new File("DatFiles/users5.dat");
	 In inUsers = new In(usersFile);
	 
	 //each field is separated(delimited) by a '|'
     String delims = "[|]";
     while (!inUsers.isEmpty()) 
     {
         // get user and rating from data source
         String userDetails = inUsers.readLine();

         // parse user details string
         String[] userTokens = userDetails.split(delims);

         // output user data to console.
         if (userTokens.length == 7) 
         {
        	 //adds a new User to an ArrayList of Users
             userResult.add(new User(userTokens[1], userTokens[2], Integer.parseInt(userTokens[3]), userTokens[4], userTokens[5]));

         }
     } 
     return userResult;
	 
 
 }

 public List<Movie> loadMovies (String filename) throws Exception
 {
	 
	 ArrayList <Movie> movieResult = new ArrayList<Movie>();
	 File itemsFile = new File("DatFiles/items5.dat");
	 In inItems = new In(itemsFile);
	 
	//each field is separated(delimited) by a '|'
     String delims = "[|]";
     while (!inItems.isEmpty()) 
     {
         // get user and rating from data source
         String itemDetails = inItems.readLine();

         // parse user details string
         String[] itemTokens = itemDetails.split(delims);

         // output user data to console.
         if (itemTokens.length == 5) 
         {
        	 //adds a new User to an ArrayList of Users
             movieResult.add(new Movie(itemTokens[1], itemTokens[2], itemTokens[3]));
         }
     }
     return movieResult;
 }  

 
 public List<Rating> loadRatings (String filename) throws Exception
 {
	 ArrayList <Rating> ratingResult = new ArrayList<Rating>();
	 File ratingsFile = new File("DatFiles/ratings5.dat");
	 In inRatings = new In(ratingsFile);
	 
	//each field is separated(delimited) by a '|'
     String delims = "[|]";
     
     while (!inRatings.isEmpty()) 
     {
         // get user and rating from data source
         String ratingDetails = inRatings.readLine();

         // parse user details string
         String[] ratingTokens = ratingDetails.split(delims);

         // output user data to console.
         if (ratingTokens.length == 5) 
         {
        	 //adds a new User to an ArrayList of Users
             ratingResult.add(new Rating(Integer.parseInt(ratingTokens[2])));
         }
     }
     return ratingResult;
 }



   
}