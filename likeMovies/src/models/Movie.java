package models;
import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import utils.ToJsonString;

import com.google.common.base.Objects;


public class Movie 
{
	public static Long   counter = 0l;
	public List<Rating> route = new ArrayList<>();
	
	public Long mid;
	public String name;
	public String year;
	public String url;
	
	public 	Movie()
	{
	}
	  
	public Movie (String name, String year, String url)
	{
		this.mid        = counter++;
		this.name  = name;
		this.year = year;
		this.url = url;
	}
	
	public String toString()
	{
	    return new ToJsonString(getClass(), this).toString();
	}
	
	@Override  
	  public int hashCode()  
	  {  
	     return Objects.hashCode(this.mid, this.name, this.year, this.url);  
	  } 
	  
	@Override
	public boolean equals(final Object obj)   
	{
	  if (obj instanceof Movie)
	  {
	    final Movie other = (Movie) obj;
	      return Objects.equal(name, other.name) 
	    	  && Objects.equal(url, other.url) 
	          && Objects.equal(year, other.year)
	          && Objects.equal(route,   other.route);
	    }
	    else
	    {
	      return false;
	    }
	  }
}
