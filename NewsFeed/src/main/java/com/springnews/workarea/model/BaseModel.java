package com.springnews.workarea.model;

import java.util.List;
import java.util.function.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BaseModel {
	private static SessionFactory factory;
	private static List<News> news;
	private static int idNews;
	
	public static Session  config(Class<?> c) {
		factory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(c).buildSessionFactory(); //it is important to check which class is specified (важно проверять какой класс указан для таблицы)
		Session session = factory.getCurrentSession();
		return session;
	}
	
	@SuppressWarnings("unchecked")
	public static List<News> getNews(int count) {
		Session session = config(News.class);
		try {
			session.beginTransaction();
			List<News> param = session.createQuery("from News").list();
			int size = param.size();
			if(count>size) count=size;
			news = param.subList(0, count);
			session.getTransaction().commit();
			//news = session.createQuery("from News").list().subList(0, count); //we specify the claas and not the name of the table (указываем класс, а не имя таблицы!!!)
		}
		finally{
			factory.close();
		}
		return news;
	}
	
	public static News getIdNews(final int id) {
		idNews = id;
		News idNews = news.stream().filter(new Predicate<News>() {
			@Override
			public boolean test(News news) {
				return news.getId() == id;
			}
		}).findAny().orElse(null);
		return idNews;
	}
	
	public static void addNews(News newsSend) {
		Session session = config(News.class);
		try {
			session.beginTransaction();
			session.save(newsSend);//Save date for request
			session.getTransaction().commit();//doing date insertion
		}
		finally{
			factory.close();
		}
	}
	
	public static void updateNews(String heding, String news) {
		Session session = config(News.class);
		try{
			session.beginTransaction();
			session.createQuery("update News set heading='" + heding + "', news ='" + news + "' where id=" + idNews).executeUpdate();
			session.getTransaction().commit();//doing date insertion
		}
		finally{
			factory.close();
		}
	}
	
	public static void deleteNews(int id) {
		Session session = config(News.class);
		try{
			session.beginTransaction();
			session.createQuery("delete from News where id="+id).executeUpdate();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static boolean authorization(InputParam inputParam) {
		Session session = config(InputParam.class);
		List<InputParam> param;
		try {
			session.beginTransaction();
			param = session.createQuery("from InputParam").list();
			session.getTransaction().commit(); //doing data insertion
		}
		finally{
			factory.close();
		}
		for(InputParam ipar : param) {
			if((ipar.getLogin()).equals(inputParam.getLogin())&&(ipar.getPassword())
					.equals(inputParam.getPassword())) return false;
		}
		return true;
	}
}
