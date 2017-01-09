package nyla.solutions.net.postit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nyla.solutions.global.net.email.Email;
import nyla.solutions.net.postit.exception.PostItException;

@RestController
@EnableAutoConfiguration
@ComponentScan
@ImportResource(locations = {"classpath:cache-config.xml"})
@EnableGemfireRepositories
@Configuration
public class PostItApp
{
	/**
	 * 
	 * @return
	 */
	@Bean
	public Email getEmail()
	{
		Email email = new Email();
		/*
		 * 
		 * mail.from=website@morningstarccc.org
			mail.from.password={cryption}-35 -57 79 -68 -25 124 94 109 66 75 -40 -37 -80 -109 -103 -52
			mail.host=smtp.1and1.com
			mail.auth.required=true
		 */
		email.setSmtpHost("smtp.1and1.com");
		email.setMailFromUser("marketing@TheRevelationSquad.com");
		email.setMailFromPassword("Jesus#1".toCharArray());
		email.setDefaultSubject("PostIt Email");
		email.setAuthenicationRequired(true);
		return email;
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
