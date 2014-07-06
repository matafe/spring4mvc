package com.matafe.springmvc.web.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


//import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.matafe.springmvc.core.config.AppConfig;

/**
 * @author Mauricio T. Ferraz
 */
public class SpringWebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class, SecurityConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	// Instead we used SecurityWebApplicationInitializer
	/*
	 * @Override protected Filter[] getServletFilters() { return new
	 * Filter[]{new DelegatingFilterProxy("springSecurityFilterChain")}; }
	 */
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		super.onStartup(servletContext);
		FilterRegistration.Dynamic reg = servletContext.addFilter(
				"SiteMeshFilter",
				"com.opensymphony.sitemesh.webapp.SiteMeshFilter");
		EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST,
				DispatcherType.FORWARD);
		reg.addMappingForUrlPatterns(disps, true, "/*");
	}

}
