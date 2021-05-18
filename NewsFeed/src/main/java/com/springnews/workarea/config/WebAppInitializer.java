package com.springnews.workarea.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//super.onStartup(servletContext);
		
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
		
		registerHiddenFieldFilter(servletContext);
	}
	
	 private void registerHiddenFieldFilter(ServletContext aContext) {
	        aContext.addFilter("hiddenHttpMethodFilter", new     HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*"); 
	    }

}
