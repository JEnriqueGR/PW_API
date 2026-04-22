package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Paquete;

public class PaqueteImp {

    public static Respuesta registrar(Paquete paquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filas = conexionBD.insert("paquete.registrar", paquete);
                conexionBD.commit();

                if (filas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Paquete registrado correctamente");
                    
                    // recalcula el costo del envío 
                    try {
                        Respuesta r = EnvioImp.recalcularCosto(paquete.getIdEnvio());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar el paquete");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static Respuesta editar(Paquete paquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filas = conexionBD.update("paquete.editar", paquete);
                conexionBD.commit();

                if (filas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Paquete actualizado correctamente");
                    
                    //recalcula el costo del envío 
                    try {
                        Respuesta r = EnvioImp.recalcularCosto(paquete.getIdEnvio());
                        if (r.isError()) {
                            respuesta.setMensaje(respuesta.getMensaje() + " (recalculo costo: " + r.getMensaje() + ")");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el paquete o no hubo cambios");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }

    public static List<Paquete> consultarPorEnvio(int idEnvio) {
        List<Paquete> paquetes = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.consultar-por-envio", idEnvio);
            } finally {
                conexionBD.close();
            }
        }
        return paquetes;
    }

    public static Respuesta eliminar(int idPaquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                //obtiene el paquete para saber el idEnvio antes de borrar
                Paquete paquete = conexionBD.selectOne("paquete.obtener-por-id", idPaquete);
                Integer idEnvio = (paquete != null) ? paquete.getIdEnvio() : null;
                
                int filas = conexionBD.delete("paquete.eliminar", idPaquete);
                conexionBD.commit();

                if (filas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Paquete eliminado correctamente");
                    
                    if (idEnvio != null) {
                        try {
                            Respuesta r = EnvioImp.recalcularCosto(idEnvio);
                            if (r.isError()) {
                                respuesta.setMensaje(respuesta.getMensaje() + " (recalculo costo: " + r.getMensaje() + ")");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el paquete");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        }
        return respuesta;
    }
    
    public static List<Paquete> obtenerTodos() {
        List<Paquete> paquetes = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.obtener-todos");
            } finally {
                conexionBD.close();
            }
        }
        return paquetes;
    }
}
