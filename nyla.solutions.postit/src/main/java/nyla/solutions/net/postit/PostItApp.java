package nyla.solutions.net.postit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nyla.solutions.net.postit.exception.PostItException;

@Controller
@EnableAutoConfiguration
@ComponentScan
@ImportResource("classpath:cache-config.xml")
@EnableGemfireRepositories
public class PostItApp
{
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

	
	@Autowired
	private PostItService postItService;

	public static void main(String[] args)
	{
		SpringApplication.run(PostItApp.class, args);
	}

}
