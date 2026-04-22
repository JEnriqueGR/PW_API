package dominio;

import dto.Respuesta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Unidad;

public class UnidadImp {

    private static String generarNII(int anio, String vin) {
        if (vin == null || vin.length() < 4) {
            throw new IllegalArgumentException("VIN inválido para generar NII");
        }
        return anio + vin.substring(0, 4).toUpperCase();
    }
    
    public static Respuesta registrar(Unidad unidad) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                unidad.setNumIdenInter(
                        generarNII(unidad.getAnio(), unidad.getVin())
                );

                int filasAfectadas = conexionBD.insert("unidad.registrar", unidad);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad registrada correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar la unidad");
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
    
    public static Respuesta editar(Unidad unidad) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                unidad.setNumIdenInter(
                        generarNII(unidad.getAnio(), unidad.getVin())
                );

                int filasAfectadas = conexionBD.update("unidad.editar", unidad);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad actualizada correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró la unidad o no hubo cambios");
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
    
    public static Respuesta darDeBaja(int idUnidad, String motivo) {
        Respuesta respuesta = new Respuesta();

        if (motivo == null || motivo.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("El motivo de baja es obligatorio");
            return respuesta;
        }

        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idUnidad", idUnidad);
                parametros.put("motivo", motivo);

                int filasAfectadas = conexionBD.update("unidad.dar-baja", parametros);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad dada de baja correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró la unidad o ya estaba dada de baja");
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
    
    public static List<Unidad> buscar(String criterio) {
        List<Unidad> unidades = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                unidades = conexionBD.selectList("unidad.buscar", criterio);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return unidades;
    }
    
    public static List<Unidad> obtenerTodas() {
        List<Unidad> unidades = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                unidades = conexionBD.selectList("unidad.obtener-todas");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return unidades;
    }
    
    public static Respuesta asignarUnidad(int idUnidad, int idConductor) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                //valida que el colaborador sea conductor
                int esConductor = conexionBD.selectOne(
                        "unidad.es-conductor", idConductor);
                if (esConductor == 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("El colaborador no tiene rol de conductor");
                    return respuesta;
                }
                //valida que el conductor no tenga una unidad
                int conductorTieneUnidad = conexionBD.selectOne(
                        "unidad.conductor-tiene-unidad", idConductor);
                if (conductorTieneUnidad > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("El conductor ya tiene una unidad asignada");
                    return respuesta;
                }
                //valida que la unidad no tenga conductor
                int unidadTieneConductor = conexionBD.selectOne(
                        "unidad.unidad-tiene-conductor", idUnidad);
                if (unidadTieneConductor > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("La unidad ya tiene un conductor asignado");
                    return respuesta;
                }
                //asigna la unidad
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idUnidad", idUnidad);
                parametros.put("idConductor", idConductor);
                int filasAfectadas = conexionBD.update(
                        "unidad.asignar-unidad", parametros);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad asignada correctamente al conductor");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo asignar la unidad");
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

    public static Respuesta liberarUnidad(int idUnidad) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("unidad.liberar-unidad", idUnidad);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad liberada correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo liberar la unidad");
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
}
