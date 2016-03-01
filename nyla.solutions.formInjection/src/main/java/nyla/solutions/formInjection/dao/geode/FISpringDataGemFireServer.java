package nyla.solutions.formInjection.dao.geode;

import java.io.IOException;
import java.util.Properties;

import nyla.solutions.formInjection.dao.geode.data.FormRepository;
import nyla.solutions.formInjection.data.Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;


@Configuration
@EnableGemfireRepositories
@ImportResource({"classpath:fi.service.factory.xml"})
public class FISpringDataGemFireServer implements CommandLineRunner
{

	public FISpringDataGemFireServer()
	{
	}

	 @Bean
	    Properties gemfireProperties() {
	        Properties gemfireProperties = new Properties();
	        gemfireProperties.setProperty("name", "DataGemFireAccessApplication");
	        gemfireProperties.setProperty("mcast-port", "0");
	        gemfireProperties.setProperty("log-level", "config");
	        gemfireProperties.setProperty("start-locator", "10334");
	        return gemfireProperties;
	    }

//	    @Bean
//	    CacheFactoryBean gemfireCache() {
//	        CacheFactoryBean gemfireCache = new CacheFactoryBean();
//	        gemfireCache.setProperties(gemfireProperties());
//	        gemfireCache.setUseBeanFactoryLocator(false);
//	        return gemfireCache;
//	    }

//	    @Bean
//	    LocalRegionFactoryBean<String, Form> localRegionFactory(final GemFireCache cache) {
//	        LocalRegionFactoryBean<String, Form> formRegion = new LocalRegionFactoryBean<String, Form>();
//	        formRegion.setCache(cache);
//	        formRegion.setClose(false);
//	        formRegion.setName("form");
//	        formRegion.setPersistent(false);
//	        
//	        return formRegion;
//	    }

	    
	    @Autowired
	    FormRepository formRepository;

	    @Override
	    public void run(String... strings) throws Exception {
	     nyla.solutions.formInjection.dao.geode.data.Form form = new nyla.solutions.formInjection.dao.geode.data.Form();
	      form.setId("testing");

	        System.out.println("Before linking up with Gemfire...");
	        

	       //  formRepository.save(form);
	        

	        System.out.println("Lookup each person by name...");

	            System.out.println("\t" + formRepository.findOne(form.getId()));

	    }

	    public static void main(String[] args) throws IOException {
	        SpringApplication application = new SpringApplication(FISpringDataGemFireServer.class);
	        application.setWebEnvironment(false);
	        application.run(args);
	    }
	
}
