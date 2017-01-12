package nyla.solutions.net.postit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nyla.solutions.core.util.settings.Settings;
import nyla.solutions.global.net.email.Email;
import nyla.solutions.global.net.email.EmailTags;
import nyla.solutions.net.postit.exception.PostItException;
import nyla.solutions.spring.settings.EnvironmentSettings;

@RestController
@EnableAutoConfiguration
@ComponentScan
@ImportResource(locations = {"classpath:cache-config.xml"})
@EnableGemfireRepositories
@Configuration
public class PostItApp
{
	private static Settings settings = null;
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public Email getEmail()
	{
		Email email = new Email();		
		Settings settings = this.getSettings();
		email.setMailFromUser(settings.getProperty("mail.from"));
		email.setSmtpHost(settings.getProperty("mail.host"));
	
		boolean authenticationRequired = settings.getPropertyBoolean("mail.auth.required",false).booleanValue();
		email.setAuthenicationRequired(authenticationRequired);
		
		if(authenticationRequired)
			email.setMailFromPassword(settings.getPropertyPassword("mail.from.password"));
		
		
		return email;
	}//------------------------------------------------
	@Bean
	public Settings getSettings()
	{
		if(settings == null)
		{
			settings = new EnvironmentSettings(new StandardEnvironment());
		}
		
		return settings;
	}//------------------------------------------------
	@RequestMapping("/")
	@ResponseBody
	String home()
	{

		return "Hello World!";
	}//--------------------------------------------------------
	@RequestMapping("/postIt")
	@ResponseBody
	public void postIt()
	throws PostItException
	{
		
		this.postItService.post(null);
	}//--------------------------------------------------------
    

	/**
	 * @param pack
	 * @see nyla.solutions.net.postit.PostItMgr#sendIt(nyla.solutions.net.postit.Package)
	 */
	@PostMapping(path = "/sendIt", consumes = "application/json")
	public void sendIt(@RequestBody Package pack)
	{
		postItMgr.sendIt(pack);
	}//------------------------------------------------
	
	public static void main(String[] args)
	{
		System.out.println(System.getProperty("java.class.path"));
		SpringApplication.run(PostItApp.class, args);
	}

	@Autowired
	private PostItMgr postItMgr;
	
	@Autowired
	private PostItService postItService;
}
