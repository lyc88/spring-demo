package cn.edu.cqvie.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 声明式事务
 *
 * 环境搭建：
 * 1、导入相关依赖
 *    导入数据源、数据库驱动、Spring-Jdbc 模块
 * 2、配置数据源、JdbcTemplate(Spring提供的简化数据库操作工具)操作数据
 * 3、给方法标注@Transactionional 表示当前方法是一个事务方法
 * 4、@EnableTransactionManagement 开启基于注解事务管理功能
 *     @EnableXX
 * 5、配置事务管理器来管理事务
 */
@EnableTransactionManagement
@Configuration
@ComponentScan("cn.edu.cqvie.tx")
public class TxConfig {

    /**
     * 数据源
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root123");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        //dataSource() Spring 在配置类中 @Configuration 会特殊处理，多次调用方法只是去容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    /**
     * 注册事务管理器到容器中
     *
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }
}
