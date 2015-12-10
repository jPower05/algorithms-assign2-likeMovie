package models;


import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import models.User;

import org.junit.Test;

import static models.Fixtures.users;

public class UserTest
{
  User testUser = new User ("Joe","Blogs", 23,"M", "fireman");

  @Test
  public void testCreate()
  {
    assertEquals ("Joe",            testUser.firstName);
    assertEquals ("Blogs",          testUser.lastName);
    assertEquals (23,               testUser.age);   
    assertEquals ("M",              testUser.gender);  
    assertEquals ("fireman",        testUser.occupation);
  }
  
  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.length, ids.size());  
  }

  
  
  @Test
  public void testEquals()
  {
    User testUser2 = new User ("Joe","Blogs", 23,"M", "fireman"); 
    User john   = new User ("John", "Blogs", 27, "M", "busDriver");

    assertEquals(testUser, testUser);
    assertEquals(testUser, testUser2);
    assertNotEquals(testUser, john);
  } 
  
}