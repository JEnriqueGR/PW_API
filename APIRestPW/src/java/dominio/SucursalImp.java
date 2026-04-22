package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Sucursal;

public class SucursalImp {
    
    public static Respuesta registrar(Sucursal sucursal) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("sucursal.registrar", sucursal);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Sucursal registrada correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar la sucursal");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la BD por el momento");
        }
        return respuesta;
    }
    
    public static Respuesta editar(Sucursal sucursal) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("sucursal.editar", sucursal);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Sucursal actualizada correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró la sucursal o no hubo cambios");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la BD por el momento");
        }
        return respuesta;
    }
    
    public static Respuesta darDeBaja(int idSucursal) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("sucursal.dar-baja", idSucursal);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Sucursal dada de baja correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró la sucursal o ya estaba inactiva");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la BD por el momento");
        }
        return respuesta;
    }
    
    public static List<Sucursal> obtenerTodas() {
        List<Sucursal> sucursales = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                sucursales = conexionBD.selectList("sucursal.obtener-todas");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return sucursales;
    }
}
