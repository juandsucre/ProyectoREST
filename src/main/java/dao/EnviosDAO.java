package dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Envios;
import util.HibernateUtil;

public class EnviosDAO {
	SessionFactory sesFactory;
	Session sesion;
	
	public EnviosDAO() {
		sesFactory = HibernateUtil.getSessionFactory();
	}
	
	public Envios add(Envios env) {
		Envios envio = null;
		try {
			sesion = sesFactory.openSession();
			sesion.save(env);
		} catch (HibernateException ex) {
			System.err.println("Error en add Envio: "+ex.getMessage());
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return envio;
	}
	
	public Envios delete(Envios env) {
		Envios result = null;
		Transaction tx = null;
		try {
			sesion = sesFactory.openSession();
			tx = sesion.beginTransaction();
			sesion.delete(env);
			tx.commit();
			result = env;
		} catch (HibernateException ex) {
			System.out.println("Error en delete Envio" + ex.getMessage());
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return result;
	}
	
	public Envios update(Envios env) {
		Envios res = null;
		Transaction tx = null;
		try {
			sesion = sesFactory.openSession();
			tx = sesion.beginTransaction();
			sesion.update(env);
			tx.commit();
			res = env;
		} catch (HibernateException ex) {
			System.out.println("Error al modificar el envio" + ex.getMessage());
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return res;
	}
	
	public Envios getById(Integer id){
		Envios res = null;
		try {
			sesion = sesFactory.openSession();
			res = sesion.get(Envios.class, id);
		} catch (HibernateException ex) {
			System.err.println("Error GetById: "+ex.getMessage());
		}finally {
			sesion.close();
		}
		return res;
	}
	
	public ArrayList<Envios> getAll(){
		sesion = sesFactory.openSession();
		String hql = "FROM Envios";
		Query q = sesion.createQuery(hql);
		ArrayList<Envios> res = (ArrayList<Envios>)q.list();
		sesion.close();//se cierra esta conexion
		return res;
	}
	
}
