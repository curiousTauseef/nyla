package nyla.solutions.core.patterns.creational.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import nyla.solutions.core.data.NumberedProperty;
import nyla.solutions.core.security.user.data.UserProfile;

public class JavaBeanGeneratorCreatorTest
{
	@Test
	public void testFixedProperties()
	{
		JavaBeanGeneratorCreator<UserProfile> creator = new JavaBeanGeneratorCreator<UserProfile>(UserProfile.class);
		creator.fixedProperties("email");
		
		assertTrue(creator.getRandomizeProperties().contains("firstName"));
		
	}
	/**
	 * Test generator
	 */
	@Test
	public void testCreateString()
	{
		
		JavaBeanGeneratorCreator<UserProfile> creator = new JavaBeanGeneratorCreator<UserProfile>(UserProfile.class);
		creator.randomizeProperty("email");
		
		UserProfile userProfile = creator.create();
		assertNotNull(userProfile);
		
		assertTrue(userProfile.getEmail() != null && userProfile.getEmail().length() > 0 );
		
	}//------------------------------------------------
	/**
	 * Test generator
	 */
	@Test
	public void testCreateInt()
	{
		
		JavaBeanGeneratorCreator<NumberedProperty> creator = new JavaBeanGeneratorCreator<NumberedProperty>(NumberedProperty.class);
		creator.randomizeProperty("number");
		
		NumberedProperty numberedProperty = creator.create();
		assertNotNull(numberedProperty);
		
		assertTrue(numberedProperty.getValue() != null && numberedProperty.getNumber() > 0);
	}//------------------------------------------------
	
	/**
	 * Test generator
	 */
	@Test
	public void testCreateLong()
	{
		JavaBeanGeneratorCreator<LongObject> creator = new JavaBeanGeneratorCreator<LongObject>(LongObject.class);
		creator.randomizeProperty("id");
		creator.randomizeProperty("longId");
		
		LongObject lo = creator.create();
		
		assertNotNull(lo);
		assertTrue(lo.getId() > 0);
		assertNotNull(lo.getLongId());
		
		LongObject lo2 = creator.create();
		assertNotEquals(lo.getId(), lo2.getId());
		assertNotEquals(lo.getLongId(), lo2.getLongId());
	}
	
	@Test
	public void testCreateProtoype()
	{
		UserProfile protoype = new UserProfile();
		protoype.setFirstName("Imani");
		protoype.setLastName("Green");
		
		JavaBeanGeneratorCreator<UserProfile> creator = new JavaBeanGeneratorCreator<UserProfile>(protoype);
		
		creator.randomizeProperty("email");
		
		UserProfile u1 = creator.create();
		assertEquals(u1.getFirstName(),protoype.getFirstName());
		assertEquals(u1.getLastName(),protoype.getLastName());
		assertNotNull(u1.getEmail());
		assertTrue(u1.getEmail().length() > 0);
	}
	
	public static class LongObject
	{
		
		/**
		 * @return the id
		 */
		public long getId()
		{
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(long id)
		{
			this.id = id;
		}

		private long id;
		
		
		/**
		 * @return the longId
		 */
		public Long getLongId()
		{
			return longId;
		}

		/**
		 * @param longId the longId to set
		 */
		public void setLongId(Long longId)
		{
			this.longId = longId;
		}

		private Long longId;
	}

}
