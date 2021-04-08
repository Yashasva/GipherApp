package com.stackroute.giphy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.stackroute.giphy.jwt.JwtFilter;



@SpringBootApplication
public class FeedbackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsconfig=new CorsConfiguration();
		corsconfig.setAllowCredentials(true);
		corsconfig.addAllowedOrigin("*");
		corsconfig.addAllowedMethod("*");
		corsconfig.addAllowedHeader("*");
		urlconfig.registerCorsConfiguration("/**", corsconfig);
	
		FilterRegistrationBean filterbean=new FilterRegistrationBean(new CorsFilter(urlconfig));
		filterbean.setFilter(new JwtFilter());
		
		filterbean.addUrlPatterns("/api/v1/*");
		return filterbean;
	
	}

}
