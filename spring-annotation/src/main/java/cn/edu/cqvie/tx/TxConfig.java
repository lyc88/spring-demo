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
 *
 *
 * 原理：
 *   1、@EnableTransactionManagement
 *       利用TransactionManagementConfigurationSelector给容器中导入导入组件
 *       导入两个组件：
 *           AutoProxyRegistrar
 *           ProxyTransactionManagementConfiguration
 *
 *  2、AutoProxyRegistrar：给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件
 *       InfrastructureAdvisorAutoProxyCreator？
 *       利用后置处理器机智在对象后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用。
 *
 *  3、ProxyTransactionManagementConfiguration 做了什么
 *       1、容器中注册事务增强器，
 *          1）、事务增强器要用事务注解信息 AnnotationTransactionAttributeSource 解析事务注解
 *          2）、事务拦截器：
 *               TransactionInterceptor 保存了事务属性信息，事务管理器。
 *               他是一个 MethodInterceptor ： 方法拦截器
 *               在目标方法执行的时候
 *                   执行我们这些拦截器链
 *                   事务拦截器的作用：
 *                       1、获取事务相关的属性
 *                       2、获取 PlatformTransactionManager ，如果事先没有指定任何 transactionManager 最后会在容器
 *                       中按类型获取一个 PlatformTransactionManager。
 *                       3、执行目标方法
 *                         如果异常，获取到事物管理器，回滚操作
 *                         如果正常，利用事务管理器，提交事务
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
