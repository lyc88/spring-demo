package cn.edu.cqvie;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.edu.cqvie.bean.Yellow;
import cn.edu.cqvie.config.MainConfigOfProfile;

public class IOCTestProfile {

	/**
	 * 使用命令行参数
	 */
	@Test
	public void test01() {
		//1、创建一个applicationContext
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		//2、设置需要激活的环境变量
		applicationContext.getEnvironment().setActiveProfiles("test");
		//3、注册主配置类
		applicationContext.register(MainConfigOfProfile.class);
		//4、刷新启动容器
		applicationContext.refresh();
		
		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		Stream.of(beanNamesForType).forEach(System.out::println);
		
		Yellow yellow = applicationContext.getBean(Yellow.class);
		System.out.println(yellow);
	}

}
