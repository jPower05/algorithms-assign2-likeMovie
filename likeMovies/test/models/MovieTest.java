package models;
import static org.junit.Assert.*;

import org.junit.Test;

import static models.Fixtures.movies;

public class MovieTest 
{
	@Test
	public void testCreate()
	{
	  assertEquals ("Toy Story (1995)", movies[0].name);
	  assertEquals ("01-Jan-1995", movies[0].year);
	  assertEquals ("http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)", movies[0].url);
	  }

	@Test
	public void testIds()
	{
	  assertNotEquals(movies[0].mid, movies[1].mid);
	}
	


}



