package dao;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entities.Destinatarios;
import util.HibernateUtil;

public class DestinatariosDAO {
	SessionFactory sesFactory;
	Session sesion;
	
	public DestinatariosDAO() {
		sesFactory = HibernateUtil.getSessionFactory();
	}
	
	public Destinatarios add(Destinatarios des) {
		Destinatarios destino = null;
		try {
			sesion = sesFactory.openSession();
			sesion.save(des);
		} catch (HibernateException ex) {
			System.err.println("Error en add Destinatario: "+ex.getMessage());
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return destino;
	}
	
	public Destinatarios delete(Destinatarios des) {
		Destinatarios destino = null;
		Transaction tx = null;
		try {
			sesion = sesFactory.openSession();
			tx = sesion.beginTransaction();
			sesion.delete(des);
			tx.commit();
			destino = des;
		} catch (HibernateException ex) {
			System.err.println("Error en delete Destinatario: "+ex.getMessage());
		}catch (ConstraintViolationException ex) {
			System.out.println("NO SE PUEDE ELIMINAR, TIENE ENVIOS...");
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return destino;
	}
	
	public Destinatarios update(Destinatarios des) {
		Destinatarios destino = null;
		Transaction tx = null;
		try {
			sesion = sesFactory.openSession();
			tx = sesion.beginTransaction();
			sesion.update(des);
			tx.commit();
			destino = des;
		} catch (HibernateException ex) {
			System.err.println("Error en update Destinatario: "+ex.getMessage());
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return destino;
	}
	
	public Destinatarios getById(Integer id) {
		Destinatarios destino = null;
		try {
			sesion = sesFactory.openSession();
			destino = sesion.get(Destinatarios.class, id);
			sesion.close();
		} catch (Exception ex) {
			System.err.println("Error GetById: "+ex.getMessage());
		}
		return destino;
	}
	
	public ArrayList<Destinatarios> getAll(){
		sesion = sesFactory.openSession();//se abre una sesion con el seFactory
		String hql = "FROM Destinatarios";
		Query q = sesion.createQuery(hql);
		ArrayList<Destinatarios> res = (ArrayList<Destinatarios>)q.list();
		sesion.close();//se cierra esta conexion
		return res;
	}
		
}
