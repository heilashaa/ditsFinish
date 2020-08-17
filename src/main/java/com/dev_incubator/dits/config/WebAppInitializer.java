package com.dev_incubator.dits.config;

import com.dev_incubator.dits.util.PropertiesLoader;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        PropertiesLoader propertiesLoader = new PropertiesLoader("application.properties");
        servletContext.setInitParameter(
                StandardEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,
                propertiesLoader.getProperty(StandardEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "prod"));

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        FilterRegistration.Dynamic filterEncoding = servletContext.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
        dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");//todo error handler
        filterEncoding.setInitParameter("encoding", "UTF-8");
        filterEncoding.setInitParameter("forceEncoding", "true");
        filterEncoding.addMappingForServletNames(null, true, "DispatcherServlet");
    }
}
