package br.com.devmedia.appfinal.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebXmlConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(MvcConfig.class);
        webContext.setServletContext(context);
        
        ServletRegistration.Dynamic dynamic = context.addServlet("dispatcher", new DispatcherServlet(webContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
        
        FilterRegistration.Dynamic encodingFilter = context.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }
}