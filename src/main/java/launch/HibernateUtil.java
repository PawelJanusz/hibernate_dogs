package launch;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;


    static {
        try {
            System.out.println("Konfiguracja hibernate");

            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();

        }catch (HibernateException ex){
            System.err.println(ex.getMessage());
            throw ex;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
