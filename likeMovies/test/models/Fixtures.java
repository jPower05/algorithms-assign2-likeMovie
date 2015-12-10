package models;

public class Fixtures
{
  public static User[] users =
  {
    new User ("john", "blogs", 27, "M", "busDriver"),
    new User ("ciara", "blogs", 18, "F", "student"),
    new User ("shaun", "blogs", 17, "M", "student")
    
  };
  
  public static Rating[] ratings =
	  {
	    new Rating (4),
	    new Rating (3),
	    new Rating (4),
	    new Rating (2),
	    new Rating (0)
	  };

  
  public static Movie[]movies =
	  {
	    new Movie("Toy Story (1995)","01-Jan-1995","http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)"),
	    new Movie("GoldenEye (1995)","01-Jan-1995","http://us.imdb.com/M/title-exact?GoldenEye%20(1995)"),  
	    new Movie("Four Rooms (1995)","01-Jan-1995","http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)"),
	    new Movie("Richard III (1995)","22-Jan-1996","http://us.imdb.com/M/title-exact?Richard%20III%20(1995)")       
	  };
}