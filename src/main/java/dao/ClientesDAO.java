package dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entities.Clientes;
import util.HibernateUtil;

public class ClientesDAO {
	SessionFactory sesFactory;
	Session sesion;
	
	public ClientesDAO() {
		sesFactory = HibernateUtil.getSessionFactory();
	}
	
	public Clientes add(Clientes cli) {
		Clientes res = null;
		try {
			sesion = sesFactory.openSession();//se abre una sesion con el seFactory
			sesion.save(cli);
			res = cli;
		} catch (HibernateException ex) {
			System.err.println("Error en add Cliente: "+ex.getMessage());
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return res;
	}
	
	public Clientes delete(Clientes cliente){
		Clientes result = null;
		Transaction tx = null;
		try {
			sesion = sesFactory.openSession();
			tx = sesion.beginTransaction();
			sesion.delete(cliente);
			tx.commit();
			result = cliente;
		} catch (HibernateException ex) {
			System.out.println("Error al dar de alta al cliente" + ex.getMessage());
		}finally {
			sesion.close();//se cierra esta conexion
		}
		return result;
	}

	public Clientes update(Clientes cli) {
		Clientes res = null;
		Transaction tx = null;
		try {
			sesion = sesFactory.openSession();
			tx = sesion.beginTransaction();
			sesion.update(cli);
			tx.commit();
			res = cli;
		} catch (HibernateException ex) {
			System.out.println("Error al modificar el cliente" + ex.getMessage());
		}finally {
			sesion.close();
		}
		return res;
	}
	
	public Clientes getById(Integer id) {//este metodo nos saca los clientes por ID
		Clientes res = null;
		try {
			sesion = sesFactory.openSession();//se abre una sesion con el sesFactory
			res = sesion.get(Clientes.class, id);
			sesion.close();//se cierra esta conexion
		} catch (HibernateException ex) {
			System.err.println("Error GetById: "+ex.getMessage());
		}
		return res;
	}
	
	public ArrayList<Clientes> getAll(){//este metodo nos saca todos los clientes
		sesion = sesFactory.openSession();//se abre una sesion con el seFactory
		String hql = "FROM Clientes";//el from hace referencia a la clase Cliente de java no de la BBDD
		Query q = sesion.createQuery(hql);
		ArrayList<Clientes> res = (ArrayList<Clientes>)q.list();
		sesion.close();//se cierra esta conexion
		return res;
	}
}
