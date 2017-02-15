package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import service.CalculateSatisfactionImpl;

@ComponentScan("service")
@PropertySource("app.properties")
@SpringBootApplication
public class ApplicationInitializer implements CommandLineRunner {
	
	@Autowired
	public Environment env;
	
	@Autowired
	private CalculateSatisfactionImpl calcSat;
	
	@Override
	public void run(String... args) {
		this.calcSat.getSatisfaction();
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationInitializer.class, args);
	}
}
