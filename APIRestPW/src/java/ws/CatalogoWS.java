package ws;

import dominio.CatalogoImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Colaborador;
import pojo.Envio;
import pojo.EstatusEnvio;
import pojo.Rol;
import pojo.Sucursal;
import pojo.TipoUnidad;

@Path("catalogos")
public class CatalogoWS {
    
    @Path("roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRoles() {
        return CatalogoImp.obtenerRoles();
    }
    
    @Path("obtener-sucursales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerSucursales() {
        return CatalogoImp.obtenerSucursales();
    }
    
    @Path("obtener-sucursales-activas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerSucursalesActivas() {
        return CatalogoImp.obtenerSucursalesActivas();
    }
    
    @Path("obtener-tipo-unidad")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUnidad> obtenerTiposUnidad() {
        return CatalogoImp.obtenerTiposUnidad();
    }
    
    @Path("obtener-estatus-envio")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstatusEnvio> obtenerEstatusEnvio() {
        return CatalogoImp.obtenerEstatusEnvio();
    }
    
    @Path("obtener-por-guia")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerPorGuia() {
        return CatalogoImp.obtenerPorGuia();
    }
    
    @Path("obtener-conductores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerConductores() {
        return CatalogoImp.obtenerConductores();
    }
}
