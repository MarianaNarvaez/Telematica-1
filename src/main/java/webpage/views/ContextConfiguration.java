package webpage.views;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
class ContextConfiguration {

	@Bean
	@Primary
	public HikariDataSource primaryDataSource() {
		final HikariDataSource ds = new HikariDataSource();
		ds.setMaximumPoolSize(10);
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setJdbcUrl("jdbc:oracle:thin:@10.131.137.187/music");
		ds.setUsername("postgres");
		ds.setPassword("eafit.2016");
		ds.setConnectionTimeout(20000);
		return ds;
	}	
	
	@Bean
	public HikariDataSource secondaryDataSource() {
		final HikariDataSource ds = new HikariDataSource();
		ds.setMaximumPoolSize(10);
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setJdbcUrl("jdbc:oracle:thin:@10.131.137.218/music");
		ds.setUsername("postgres");
		ds.setPassword("eafit.2016");
		ds.setConnectionTimeout(20000);
		return ds;
	}	
}
