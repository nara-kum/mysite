package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:///C:/javaStudy/upload/");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//현재 os 정보
		String osName = System.getProperty("os.name").toLowerCase();
		String resourceLocation = "";
		
		if(osName.contains("win")) {//윈도우면
			resourceLocation = "file:///C:/javaStudy/galleryupload/";
		}else {//리눅스면
			resourceLocation = "file:/data/upload//";
		}
		
        registry.addResourceHandler("/galleryupload/**")
        		.addResourceLocations(resourceLocation);
    }
}

