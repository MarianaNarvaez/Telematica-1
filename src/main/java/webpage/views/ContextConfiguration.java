package webpage.views;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
class ContextConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties(prefix="datasource.primary")
	public DataSource primaryDataSource() {
//		final HikariDataSource ds = new HikariDataSource();
//		ds.setMaximumPoolSize(10);
//		ds.setDriverClassName("org.postgresql.Driver");
//		ds.setJdbcUrl("jdbc:postgresql://10.131.137.187/music");
//		ds.setUsername("postgres");
//		ds.setPassword("eafit.2016");
//		ds.setConnectionTimeout(20000);
//		return ds;
		return DataSourceBuilder.create().build();
	}	
	
	@Bean
	@ConfigurationProperties(prefix="datasource.secondary")
	public DataSource secondaryDataSource() {
//		final HikariDataSource ds = new HikariDataSource();
//		ds.setMaximumPoolSize(10);
//		ds.setDriverClassName("org.postgresql.Driver");
//		ds.setJdbcUrl("jdbc:postgresql://10.131.137.218/music");
//		ds.setUsername("postgres");
//		ds.setPassword("eafit.2016");
//		ds.setConnectionTimeout(20000);
//		return ds;
		return DataSourceBuilder.create().build();
	}	
}
