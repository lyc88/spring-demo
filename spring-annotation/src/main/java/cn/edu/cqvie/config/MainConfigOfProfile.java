package cn.edu.cqvie.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.edu.cqvie.bean.Yellow;

/**
 * @Profile: 指定在具体的环境场景下注册组件, 如果没有这个指定，任何环境下都能注册这个组 默认是 default 环境
 * @author ZHENG SHAOHONG
 */
@Profile("test")
@Configuration
@PropertySource("classpath:dbconfig.properties")
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

	@Value("${db.user}")
	private String username;
	private String driverClass;

	@Profile("test")
	@Bean
	public Yellow yellow() {
		return new Yellow();
	}
	
	@Profile("default")
	@Bean("devDataSource")
	public DataSource dataSourceDev(@Value("${db.password}") String password) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");

		return dataSource;
	}

	@Profile("test")
	@Bean("testDataSource")
	public DataSource dataSourceTest(@Value("${db.password}") String password) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");

		return dataSource;
	}

	@Profile("prod")
	@Bean("prodDataSource")
	public DataSource dataSourceProd(@Value("${db.password}") String password) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");

		return dataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.driverClass = resolver.resolveStringValue("${db.driverClas}");
	}
}
