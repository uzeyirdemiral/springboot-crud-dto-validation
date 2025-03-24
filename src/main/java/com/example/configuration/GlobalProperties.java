package com.example.configuration;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app") //karmaşık değerleri(List, Map, nesne yapıları) çağırmada kullanılır
// prefix=app genel olarak önde kullanılan kısaltmayı almamak için kullanılır
public class GlobalProperties {
//
//    @Value("${spring.datasource.url}") // basit tek değerleri çağırmada kullanılır
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;

    private List<Server> servers;

    @Value("${key}")
    private String key;

}
