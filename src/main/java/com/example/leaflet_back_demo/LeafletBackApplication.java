package com.example.leaflet_back_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class LeafletBackApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(LeafletBackApplication.class, args);
//    }
//}


@SpringBootApplication
public class LeafletBackApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LeafletBackApplication.class, args);
    }

    /**
     * 重写configure方法，加载启动类
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LeafletBackApplication.class);
    }
}
