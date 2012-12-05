package edu.wpi.cs.wpisuitetng.modules.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.DataStore;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.mockobjects.MockDataStore;
import edu.wpi.cs.wpisuitetng.modules.core.entitymanagers.UserManager;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

public class UserManagerTest {

	UserManager test;
	User temp;
	User conflict;
	Gson json;
	Session tempSession;
	
	@Before
	public void setUp()
	{
		test = new UserManager(MockDataStore.getMockDataStore());
		temp = new User("test","test","test",0);
		conflict = new User("steve", "steve",null, 0);
		tempSession = new Session(temp);
		json = new Gson();
	}
	
	
	
	@Test
	public void testMakeEntity() {
		User u = null;
		try {
			u = test.makeEntity(new Session(temp), json.toJson(temp, User.class));
		} catch (WPISuiteException e) {
			fail("unexpected exception");
		}
		assertEquals(u,temp);
	}
	
	@Test(expected = ConflictException.class)
	public void testMakeEntityExists() throws WPISuiteException {
		test.makeEntity(tempSession, json.toJson(conflict, User.class));
	}
	
	@Test(expected = BadRequestException.class)
	public void testMakeEntityBadJson() throws WPISuiteException {
		test.makeEntity(tempSession, "Garbage");
	}

	@Test
	public void testGetEntitySessionString() {
		fail("GetAll is not yet implemented");
	}

	@Test(expected = NotFoundException.class)
	public void testGetEntityStringEmptyString() throws NotFoundException {
		test.getEntity("");
	}
	
	@Test
	public void testGetEntityStringUserExists() {
		User[] u = null;
		try {
			u = test.getEntity("steve");
		} catch (NotFoundException e) {
			fail("unexpected exception");
		}
		assertEquals(conflict, u[0]);
	}
	
	@Test(expected = NotFoundException.class)
	public void testGetEntityStringUserDNE() throws NotFoundException {
		test.getEntity("jefferythegiraffe");
	}

	@Ignore
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testSave() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDeleteEntity() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testCount() {
		fail("Not yet implemented");
	}

}
