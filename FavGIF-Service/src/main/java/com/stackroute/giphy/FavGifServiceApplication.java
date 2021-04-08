package com.stackroute.giphy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.GenericFilterBean;

import com.stackroute.giphy.jwtfilter.JwtFilter;


@SpringBootApplication
public class FavGifServiceApplication {
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
	public static void main(String[] args) {
		SpringApplication.run(FavGifServiceApplication.class, args);
	}
}
