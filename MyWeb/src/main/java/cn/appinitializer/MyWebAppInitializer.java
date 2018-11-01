package cn.appinitializer;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import cn.servlets.MyServlet;

/**
 * MyWebAppInitializer class
 *
 * @author Administrator
 * @date
 */
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic testSetvelt=servletContext.addServlet("myServlet",MyServlet.class);
        testSetvelt.setLoadOnStartup(1);
        testSetvelt.addMapping("/test");
    }
}
