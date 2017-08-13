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
	public static final String songsPath = (new File("src" + File.separator + "songs")
			.getAbsolutePath());

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
