package com.stackroute.giphy.jwtfilter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
public class JwtFilter extends GenericFilterBean{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httprequest=(HttpServletRequest) request;
		HttpServletResponse httpresponse=(HttpServletResponse) response;
		String authheader1=httprequest.getHeader("Authorization");
		System.out.println("inside out"+authheader1);
		httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
		httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");;
		httpresponse.setHeader("Access-Control-Allow-Credential", "true");
		httpresponse.setHeader("Access-Control-Allow-Headers", "*");
		if(httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())){
			chain.doFilter(httprequest,httpresponse);
			}
		else{
			String authheader=httprequest.getHeader("Authorization");
		System.out.println(authheader);
		if(authheader==null || (!authheader.startsWith("Bearer")))
			throw  new ServletException("JWT token is missing in authorization");
		else
		{
		String token=authheader.substring(7);
		try
		{
			JwtParser myparser=Jwts.parser().setSigningKey("ibmkey");
			Jwt jwtobj=myparser.parse(token);
			Claims claims=(Claims)jwtobj.getBody();
			String user=claims.getSubject();
			HttpSession session=httprequest.getSession();
			session.setAttribute("username", user);
		}
		catch(Exception excep)
		{
			throw new ServletException("Key mismatch" + excep.getMessage());
		}
		}
		chain.doFilter(httprequest, httpresponse);
	}
}}



//package com.stackroute.giphy.jwtfilter;
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.filter.GenericFilterBean;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//
//public class JwtFilter extends GenericFilterBean {
//
//
//	@Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//	    	HttpServletRequest httprequest=(HttpServletRequest) request;
//			HttpServletResponse httpresponse=(HttpServletResponse) response;
//			
//			
//			httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
//			httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");;
//			httpresponse.setHeader("Access-Control-Allow-Credential", "true");
//			httpresponse.setHeader("Access-Control-Allow-Headers", "*");
//			
//			
//			String authheader=httprequest.getHeader("Authorization");
//			
//			System.out.println(authheader);
//			
//			if(authheader==null || (!authheader.startsWith("Bearer")))
//			throw  new ServletException("JWT token is missing in authorization");
//			
//			else
//			{
//			
//			String token=authheader.substring(7);
//			
//			try
//			{
//				JwtParser myparser=Jwts.parser().setSigningKey("ibmkey");
//				
//				Jwt jwtobj=myparser.parse(token);
//				
//				Claims claims=(Claims)jwtobj.getBody();
//	
//				chain.doFilter(httprequest, httpresponse);
//			}
//			
//			catch(Exception excep)
//			{
//				throw new ServletException("Key mismatch" + excep.getMessage());
//			}
//		} 
//   }
//
//}
