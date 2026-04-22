package ws;

import com.google.gson.Gson;
import dto.Respuesta;
import dominio.EnvioImp;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Envio;

@Path("envio")
public class EnvioWS {
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json) {
        Gson gson = new Gson();
        try {
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.registrar(envio);
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
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.editar(envio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("consultar/{numeroGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envio consultarPorGuia(@PathParam("numeroGuia") String numeroGuia) {
        try {
            return EnvioImp.consultarPorGuia(numeroGuia);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("actualizar-estatus/{idEnvio}/{idEstatus}/{idColaborador}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatus(@PathParam("idEnvio") int idEnvio, @PathParam("idEstatus") int idEstatus) {
        try {
            return EnvioImp.actualizarEstatus(idEnvio, idEstatus);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("recalcular-costo/{idEnvio}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta recalcularCosto(@PathParam("idEnvio") int idEnvio) {
        try {
            return EnvioImp.recalcularCosto(idEnvio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerTodos() {
        try {
            HashMap<String, Object> resultado = EnvioImp.obtenerTodas();
            return (List<Envio>) resultado.get("envios");
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("actualizar-motivo/{idEnvio}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarMotivo(@PathParam("idEnvio") int idEnvio, String json) {
        try {
            Gson gson = new Gson();
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.actualizarMotivoEstatus(idEnvio, envio.getMotivoEstatus());
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
