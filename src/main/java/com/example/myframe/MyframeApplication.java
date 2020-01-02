package com.example.myframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyframeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyframeApplication.class, args);
	}

	/**
	 * 启动类集成SpringBootServletInitializer，重写configure方法
	 * 在线上环境tomcat能找到 启动类
	 *
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyframeApplication.class);
	}
}
