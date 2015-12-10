package models;
import static org.junit.Assert.*;
import org.junit.Test;


public class RatingTest 
{
	Rating test = new Rating (5);
	
	@Test
	public void testCreate()
	{
		assertEquals (5,      test.rating);
	}





}
