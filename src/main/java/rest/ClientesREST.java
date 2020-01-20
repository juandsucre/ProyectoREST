package rest;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import dao.ClientesDAO;
import entities.Clientes;

@Path("clientes")
public class ClientesREST {
	ClientesDAO clienteDAO = new ClientesDAO();
	
	@GET//este metodo saca una lista json con todos los clientes
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Clientes> getAll(){
		return clienteDAO.getAll();
	}
	
	@GET//este metodo solo saca un cliente via id
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Clientes read(@PathParam("id") Integer id){
		return clienteDAO.getById(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Clientes read(Clientes cliente) {
		Clientes res;
		res = clienteDAO.add(cliente);
		return res;
	}
	
	@DELETE//este metodo elimina un cliente
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Clientes delete(Clientes cliente) {
		Clientes res;
		res = clienteDAO.delete(cliente);
		return res;
	}

	@PUT//este metodo modifica un cliente
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Clientes update(Clientes cliente) {
		Clientes res;
		res = clienteDAO.update(cliente);
		return res;
	}
	
	@GET//este metodo es para notificar que un cliente no existe o no se encuentra
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read2(@PathParam("id") Integer id) {
		Clientes c;
		Response r;
		c = clienteDAO.getById(id);
		if (c==null)
		{
			r =  Response.status(Response.Status.NOT_FOUND).entity("El cliente "+id+" no existe").build();
		}
		else
		{
			r =  Response.status(Response.Status.OK).entity(c).build();
		}
		return r;
	    
	}
}
