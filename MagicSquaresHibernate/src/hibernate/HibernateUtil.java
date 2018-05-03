package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for hibernate.
 * 
 * @author Nikita Pavlov
 *
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);
	static {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.xml")
					.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		} catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			LOGGER.warn(th.getMessage());
			throw new ExceptionInInitializerError(th);

		}
	}

	/**
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {

		return sessionFactory;

	}
}