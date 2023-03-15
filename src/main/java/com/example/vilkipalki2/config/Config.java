package com.example.vilkipalki2.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@Log
public class Config implements WebMvcConfigurer {

    @Autowired
    Environment env;

    public static String imageUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String activeProfile = env.getProperty("spring.profiles.active");

        if(activeProfile.equals("dev")) imageUploadPath = "C:/Program Files/Apache Software Foundation/Tomcat 10.1/webapps/vilkipalki/WEB-INF/classes/static/images";
        else if(activeProfile.equals("prod")) imageUploadPath = "/tomcat/webapps/vilkipalki/WEB-INF/classes/static/images";
        else if(activeProfile.equals("docker")) imageUploadPath = "/usr/local/tomcat/webapps/vilkipalki/WEB-INF/classes/static/images";
//        else if(activeProfile.equals("docker")) imageUploadPath = "C:/Program Files/Apache Software Foundation/Tomcat 10.1/webapps/vilkipalki/WEB-INF/classes/static/images";
        else imageUploadPath = "undefined";

        log.info(imageUploadPath);

        registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/", "classpath:/public/",
                "classpath:/resources/", "classpath:/META-INF/resources/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + imageUploadPath + "/");

    }

    //докер контейнер использует БД хоста
    @Bean
    @Profile("docker")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://host.docker.internal:3306/avada_kino"); //если коннект из контейнера к БД хоста
//        dataSource.setUrl("jdbc:mysql://localhost:3306/avada_kino");  //если коннект из хоста к БД контейнера
        dataSource.setUrl("jdbc:mysql://db:3306/avada_kino"); //если коннект идёт внутри сети контейнеров, db = mysql image name
        dataSource.setUsername("avada_kino");
        dataSource.setPassword("OUlIg40qOZ");
        log.info(dataSource.toString());
        return dataSource;
    }
}
