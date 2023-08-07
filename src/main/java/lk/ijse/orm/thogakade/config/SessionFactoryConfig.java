package lk.ijse.orm.thogakade.config;


import lk.ijse.orm.thogakade.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfig) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }
    private SessionFactoryConfig(){
           /* StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(Student.class).getMetadataBuilder().applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
           */ /*SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();*/
        sessionFactory = new Configuration().configure().addAnnotatedClass(Customer.class).buildSessionFactory();
    }


    public Session getSession() {
        return sessionFactory.openSession();
    }
}
