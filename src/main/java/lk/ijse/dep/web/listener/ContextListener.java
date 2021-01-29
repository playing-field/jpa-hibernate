package lk.ijse.dep.web.listener;

import lk.ijse.dep.web.util.HibernateUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.*;

@WebListener
public class ContextListener implements ServletContextListener {

    org.slf4j.Logger logger = LoggerFactory.getLogger(ContextListener.class);

    public ContextListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Properties prop = new Properties();
        try {
            logger.info("Session factory is being initialized");
            EntityManagerFactory emf;
            prop.load(ContextListener.class.getResourceAsStream("/application.properties"));
            emf = Persistence.createEntityManagerFactory("dep-6",prop);
            System.out.println(emf);
            sce.getServletContext().setAttribute("emf",emf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        HibernateUtil.getSessionFactory().close();
        EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("emf");
        emf.close();
        logger.info("Session factory is being shut down");
    }
}
