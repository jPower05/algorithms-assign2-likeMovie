package models;
import static com.google.common.base.MoreObjects.toStringHelper;


import utils.ToJsonString;

import com.google.common.base.Objects;

public class Rating 
{
	static Long   counter = 0l;
	  
	  public Long   id;
	  public int  rating;
	 
	 
	  public Rating()
	  {
	  }
	  
	  public Rating( int rating)
	  {
	    this.id        = counter++;
	    this.rating      = rating;
	  }
	  
	  
	  public String toString()
	  {
	    return new ToJsonString(getClass(), this).toString();
	  }
	  
	  @Override  
	  public int hashCode()  
	  {  
	     return Objects.hashCode(this.id, this.rating);  
	  } 
	  
	  @Override
	  public boolean equals(final Object obj)
	  {
	    if (obj instanceof Rating)
	    {
	      final Rating other = (Rating) obj;
	      return Objects.equal(rating, other.rating);
	    	
	    }
	    else
	    {
	      return false;
	    }
	  }
}
