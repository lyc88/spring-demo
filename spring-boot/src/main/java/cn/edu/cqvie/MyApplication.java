package cn.edu.cqvie;

import cn.edu.cqvie.echo.DefaultEchoService;
import cn.edu.cqvie.echo.EchoService;
import cn.edu.cqvie.echo.EchoWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.PostConstruct;

@EnableWebSocket
@SpringBootApplication
public class MyApplication implements WebSocketConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MyApplication.class);

    public MyApplication() {
    }

    public static void main(String[] args) {
        System.out.println(MyApplication.class.getClassLoader());
        SpringApplication.run(MyApplication.class, args);
    }

    @PostConstruct
    private static void myLog() {
        logger.trace("Trace Message");
        logger.debug("Debug Message");
        logger.info("Info Message");
        logger.warn("Warn Message");
        logger.error("Error Message");
    }

    @Bean
    public EchoService echoService() {
        return new DefaultEchoService("You said \"%s\"!");
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler(echoService());
    }

    /**
     * 注册 WebSocket
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/echo").withSockJS();
    }
}
