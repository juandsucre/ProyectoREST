package rest;

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

import dao.DestinatariosDAO;
import entities.Destinatarios;

@Path("destinatarios")
public class DestinatariosREST {
	DestinatariosDAO destinatarioDAO = new DestinatariosDAO();
	
	@GET//este metodo saca una lista json con todos los destinatarios
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Destinatarios> getAll(){
		return destinatarioDAO.getAll();
	}
	
	@GET//este metodo solo saca un destino via id
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Destinatarios read(@PathParam("id") Integer id) {
		return destinatarioDAO.getById(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Destinatarios read(Destinatarios destinatario) {
		Destinatarios res = null;
		res = destinatarioDAO.add(destinatario);
		return res;
	}
	
	@DELETE//este metodo elimina un destino
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Destinatarios delete(Destinatarios destinatario) {
		Destinatarios res = null;
		res = destinatarioDAO.delete(destinatario);
		return res;
	}
	
	@PUT//este metodo modifica un destino
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Destinatarios update(Destinatarios destinatario) {
		Destinatarios res = null;
		res = destinatarioDAO.update(destinatario);
		return res;
	}
	
	@GET//este metodo es para notificar que un destinatario no existe o no se encuentra
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read2(@PathParam("id") Integer id) {
		Destinatarios d;
		Response r;
		d = destinatarioDAO.getById(id);
		if (d == null){
			r =  Response.status(Response.Status.NOT_FOUND).entity("El destinatario con id: "+id+" no existe").build();
		}
		else{
			r =  Response.status(Response.Status.OK).entity(d).build();
		}
		return r;
	}
}
