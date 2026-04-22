package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Envio;
import pojo.EstatusEnvio;
import pojo.Rol;
import pojo.Sucursal;
import pojo.TipoUnidad;

public class CatalogoImp {

    public static List<Rol> obtenerRoles() {
        List<Rol> roles = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                roles = conexionBD.selectList("catalogo.roles");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return roles;
    }

    public static List<Sucursal> obtenerSucursales() {
        List<Sucursal> sucursales = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                sucursales = conexionBD.selectList("catalogo.obtener-sucursales");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursales;
    }

    public static List<Sucursal> obtenerSucursalesActivas() {
        List<Sucursal> sucursales = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                sucursales = conexionBD.selectList("catalogo.obtener-sucursales-activas");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursales;
    }
    
    public static List<TipoUnidad> obtenerTiposUnidad() {
        List<TipoUnidad> tipos = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                tipos = conexionBD.selectList("catalogo.obtener-tipo-unidad");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return tipos;
    }
    
    public static List<EstatusEnvio> obtenerEstatusEnvio() {
        List<EstatusEnvio> estatus = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                estatus = conexionBD.selectList("catalogo.obtener-estatus-envio");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return estatus;
    }
    
    public static List<Envio> obtenerPorGuia() {
        List<Envio> estatus = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                estatus = conexionBD.selectList("catalogo.obtener-por-guia");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return estatus;
    }
    
    public static List<Colaborador> obtenerConductores() {
        List<Colaborador> conductores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                conductores = conexionBD.selectList("catalogo.obtener-conductores");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return conductores;
    }
}
