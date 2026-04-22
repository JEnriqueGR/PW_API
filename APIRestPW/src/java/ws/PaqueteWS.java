package ws;

import com.google.gson.Gson;
import dto.Respuesta;
import dominio.PaqueteImp;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Paquete;

@Path("paquete")
public class PaqueteWS {
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json) {
        Gson gson = new Gson();
        try {
            Paquete paquete = gson.fromJson(json, Paquete.class);
            return PaqueteImp.registrar(paquete);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar(String json) {
        Gson gson = new Gson();
        try {
            Paquete paquete = gson.fromJson(json, Paquete.class);
            return PaqueteImp.editar(paquete);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("consultar-por-envio/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> consultarPorEnvio(@PathParam("idEnvio") int idEnvio) {
        try {
            return PaqueteImp.consultarPorEnvio(idEnvio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("eliminar/{idPaquete}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@PathParam("idPaquete") int idPaquete) {
        try {
            return PaqueteImp.eliminar(idPaquete);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerTodos() {
        try {
            return PaqueteImp.obtenerTodos();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
