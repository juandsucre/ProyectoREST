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

import dao.EnviosDAO;
import entities.Envios;

@Path("envios")
public class EnviosREST {
	EnviosDAO envioDAO = new EnviosDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Envios> getAll(){
		return envioDAO.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Envios read(@PathParam("id") Integer id) {
		return envioDAO.getById(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Envios read(Envios envio) {
		Envios res;
		res = envioDAO.add(envio);
		return res;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Envios delete(Envios env) {
		Envios res;
		res = envioDAO.delete(env);
		return env;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Envios update(Envios env){
		Envios res;
		res = envioDAO.update(env);
		return env;
	}
	
	@GET//este metodo es para notificar que un envio no existe o no se encuentra
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read2(@PathParam("id") Integer id) {
		Envios en;
		Response r;
		en = envioDAO.getById(id);
		if(en == null) {
			r = Response.status(Response.Status.NOT_FOUND).entity("El envio con id: "+id+" no existe").build();
		}else {
			r =  Response.status(Response.Status.OK).entity(en).build();
		}
		return r;
	}
}
