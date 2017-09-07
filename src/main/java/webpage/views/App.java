package webpage.views;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "webpage")
@EntityScan("webpage.views.entities")
@EnableJpaRepositories("webpage.model")
public class App {
	public static final String songsPath1 = (new File(File.separator+"server1")
			.getAbsolutePath());
	public static final String songsPath2 = (new File(File.separator+"server2")
			.getAbsolutePath());

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
