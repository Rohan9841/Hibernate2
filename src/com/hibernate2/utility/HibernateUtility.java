package com.hibernate2.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {

	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	private static Session session;
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("com/hibernate2/configuration/hibernate.cfg.xml");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			factory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Session getSession() {
		try {
			if(threadLocal.get() == null) {
				session = factory.openSession();
				threadLocal.set(session);
				return session;
			}else {
				session = threadLocal.get();
				return session;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void closeSession() {
		session.close();
	}
}
