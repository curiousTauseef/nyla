package nyla.solutions.formInjection.web.boot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import nyla.solutions.formInjection.data.DeleteForm;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.ManagedForm;
import nyla.solutions.formInjection.data.Question;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.global.security.user.data.User;
import nyla.solutions.global.security.user.data.UserProfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class FIWebApp
{

	public FIWebApp()
	{
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/")
	@ResponseBody
	String home()
	{
		return "Hello World!";
	}

	@RequestMapping("/testForm")
	@ResponseBody
	Form form(String id)
	{

		Form form = new Form();
		//form.setId(id);
		//form.setCreateDate(Calendar.getInstance().getTime());
		return form;
	}
	
	@RequestMapping("/testDeleteForm")
	@ResponseBody
	DeleteForm delete(String id)
	{

		DeleteForm form = new DeleteForm();
		//form.setId(id);
		//form.setCreateDate(Calendar.getInstance().getTime());
		return form;
	}
	
	@RequestMapping("/questionarie")
	@ResponseBody
	Questionaire questionarie(String id)
	{
		Questionaire questionaire = new Questionaire();
		
		Map<Integer,Question> map = new HashMap<Integer, Question>();
		Question q1 = new Question();
		q1.setQuestionText("Testing");
		map.put(1, q1);
		
		questionaire.setQuestions(map);
		
		return questionaire;
	}

	@RequestMapping("/testUser")
	@ResponseBody
	User user(String id)
	{

		User user = new UserProfile();
		user.setId(id);
		return user;
	}

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(FIWebApp.class, args);
	}
}
