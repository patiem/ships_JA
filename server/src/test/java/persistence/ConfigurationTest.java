package persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class ConfigurationTest {
    private SessionFactory sf;

    @BeforeTest
    public void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public void testConnection() {

    }
}
