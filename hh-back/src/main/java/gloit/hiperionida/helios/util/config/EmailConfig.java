package gloit.hiperionida.helios.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Value("${helios.app.mail.host}")
    private String host;
    @Value("${helios.app.mail.port}")
    private Integer port;
    @Value("${helios.app.mail.username}")
    private String username;
    @Value("${helios.app.mail.password}")
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSender mailSender = new JavaMailSenderImpl();
        ((JavaMailSenderImpl) mailSender).setHost(this.host);
        ((JavaMailSenderImpl) mailSender).setPort(this.port);

        ((JavaMailSenderImpl) mailSender).setUsername(this.username);
        ((JavaMailSenderImpl) mailSender).setPassword(this.password);

        Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();

        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.starttls.required", "false");

        return mailSender;
    }

}