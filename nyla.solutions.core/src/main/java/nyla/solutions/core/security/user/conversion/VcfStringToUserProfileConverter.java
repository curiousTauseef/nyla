package nyla.solutions.core.security.user.conversion;

import java.util.HashMap;
import java.util.function.BiConsumer;

import nyla.solutions.core.operations.ClassPath;
import nyla.solutions.core.patterns.conversion.Converter;
import nyla.solutions.core.security.user.data.UserProfile;

/**
 * Support for vcards
 * 
 * begin:vcard
	version:3.0
	prodid:Microsoft-MacOutlook/f.15.1.160411
	UID:58126B0F-C7F1-4685-8949-DBB02E48258D
	fn;charset=utf-8:Estella Robinson
	n;charset=utf-8:Robinson;Estella;;;
	tel;charset=utf-8;type=home:(201) 435-9523
	end:vcard 
 * @author Gregory Green
 *
 */
public class VcfStringToUserProfileConverter implements Converter<String, UserProfile>
{
	/**
	 * Convert string to a user profiles
	 */
	public VcfStringToUserProfileConverter()
	{
		//email;charset=utf-8;type=internet;type=pref;type=other:ESmith@businessedge.com
		BiConsumer<String, UserProfile> 
		setEmail = (vcfLine,userProfile) -> {
			
			//find @
			int atIndex = vcfLine.lastIndexOf(":");
			
			String email = vcfLine.substring(atIndex+1);
			
			userProfile.setEmail(email);
		};
		strategies.put("email",setEmail);
		
		//n;charset=utf-8:Robinson;Estella;;;
		BiConsumer<String, UserProfile> 
		setName = (vcfLine,userProfile) -> {
			
			//find @
			int atIndex = vcfLine.lastIndexOf(":");
			
			String name = vcfLine.substring(atIndex+1);
			
			if(name == null || name.trim().length() == 0)
			{
				return; //no nothing
			}
			
			String [] tokens = name.split(";");
			
			if(tokens.length > 0 && tokens[0] != null && tokens[0].trim().length() > 0 )
				userProfile.setLastName(tokens[0]);
			
			
			if(tokens.length > 1 && tokens[1] != null && tokens[1].trim().length() > 0)
				userProfile.setFirstName(tokens[1]);
		};
		strategies.put("n",setName);
		
		//fn;charset=utf-8:1st Timothy
		BiConsumer<String, UserProfile> 
		setFirstName = (vcfLine,userProfile) -> {
					
					//find @
					int atIndex = vcfLine.lastIndexOf(":");
					
					String name = vcfLine.substring(atIndex+1);
					
					if(name == null || name.trim().length() == 0)
					{
						return; //no nothing
					}
					
					String previousFirstName = userProfile.getFirstName();
					
					//set if previous null
					if(previousFirstName == null || previousFirstName.trim().length() == 0)
					{
						userProfile.setFirstName(name);
					}
				};		
			strategies.put("fn",setFirstName);
	}//------------------------------------------------
	/**
	 * Convert from text from user profile
	 */
	@Override
	public UserProfile convert(String text)
	{
		if(text == null || text.trim().length() == 0)
			return null;
		
		
		String[] lines = text.split("\\\n");
		
		BiConsumer<String,UserProfile> strategy = null;
		UserProfile userProfile = null;
		
		for (String line : lines)
		{
			//get first term
			int index = line.indexOf(";");
			if(index < 0)
				continue; //skip line
			
			String term = line.substring(0, index);
			
			strategy = strategies.get(term);
			
			if(strategy == null)
				continue;
			
			if(userProfile == null)
			{
				userProfile = ClassPath.newInstance(this.userProfileClass);
			}
			
			strategy.accept(line, userProfile);
			
		}
		return userProfile;
	}//------------------------------------------------
	
	/**
	 * @return the userProfileClass
	 */
	public Class<? extends UserProfile> getUserProfileClass()
	{
		return userProfileClass;
	}//------------------------------------------------
	/**
	 * @param userProfileClass the userProfileClass to set
	 */
	public void setUserProfileClass(Class<? extends UserProfile> userProfileClass)
	{
		this.userProfileClass = userProfileClass;
	}//------------------------------------------------

	private Class<? extends UserProfile> userProfileClass = UserProfile.class;
	private HashMap<String,BiConsumer<String,UserProfile>> strategies = new HashMap<String,BiConsumer<String,UserProfile>>();

}
