package ws;

import com.google.gson.Gson;
import dto.Respuesta;
import dominio.UnidadImp;
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
import pojo.Unidad;

@Path("unidad")
public class UnidadWS {
    @Path("buscar/{criterio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscar(@PathParam("criterio") String criterio) {
        try {
            return UnidadImp.buscar(criterio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json) {
        Gson gson = new Gson();
        try {
            Unidad unidad = gson.fromJson(json, Unidad.class);
            return UnidadImp.registrar(unidad);
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
            Unidad unidad = gson.fromJson(json, Unidad.class);
            return UnidadImp.editar(unidad);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("dar-baja/{idUnidad}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta darDeBaja(
            @PathParam("idUnidad") int idUnidad,
            String motivo) {

        try {
            return UnidadImp.darDeBaja(idUnidad, motivo);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("obtener-todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerTodas() {
        try {
            return UnidadImp.obtenerTodas();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("asignar/{idUnidad}/{idConductor}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta asignarUnidad(
            @PathParam("idUnidad") int idUnidad,
            @PathParam("idConductor") int idConductor) {
        try {
            return UnidadImp.asignarUnidad(idUnidad, idConductor);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("liberar/{idUnidad}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta liberarUnidad(@PathParam("idUnidad") int idUnidad) {
        try {
            return UnidadImp.liberarUnidad(idUnidad);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
