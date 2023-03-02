package com.grang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String osName = System.getProperty("os.name");
		String userName = System.getProperty("user.name");
		if (osName.contains("Mac")) {
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///Users/"+ userName + "/Pictures/grangImg/");
		} else if (osName.contains("Windows")) {
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///C:/"+ userName +"/Pictures/grangImg/");
		} else if (osName.contains("Linux")){
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///"+ userName +"/Pictures/grangImg/");
		}

		if (osName.contains("Mac")) {
			registry.addResourceHandler("/profile/**").addResourceLocations("file:///Users/"+ userName + "/Pictures/profile/");
		} else if (osName.contains("Windows")) {
			registry.addResourceHandler("/profile/**").addResourceLocations("file:///C:/Users/"+ userName +"/Pictures/profile/");
		} else if (osName.contains("Linux")){
			registry.addResourceHandler("/profile/**").addResourceLocations("file:///"+ userName +"/Pictures/profile/");
		}
	}
}
