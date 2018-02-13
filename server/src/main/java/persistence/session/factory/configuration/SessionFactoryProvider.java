package persistence.session.factory.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * class which is used to provide session factory
 */
public class SessionFactoryProvider {

    private SessionFactoryProvider() {
    }

    private static SessionFactory sf;

    public static SessionFactory provide() {
        if (sf != null) {
            return sf;
        } else {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
            sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            return sf;
        }
    }
}
