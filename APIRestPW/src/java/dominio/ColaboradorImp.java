package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;

public class ColaboradorImp {

    public static List<Colaborador> obtenerTodos() {
        List<Colaborador> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaborador.obtener-todos");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }

    public static Respuesta registrar(Colaborador colaborador) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("colaborador.registrar", colaborador);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Registro del colaborador " + colaborador.getNombre() + ", guardado correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar el colaborador");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la BD por el momento :c");
        }
        return respuesta;
    }

    public static Respuesta editar(Colaborador colaborador) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("colaborador.editar", colaborador);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("El colaborador "
                            + colaborador.getNombre() + ", ha sido actualizado");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el colaborador o no hubo cambios");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la BD por el momento :c");
        }
        return respuesta;
    }

    public static Respuesta eliminar(int idColaborador) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("colaborador.eliminar", idColaborador);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("El colaborador con el id: " + idColaborador + ", ha sido eliminado");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el colaborador");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la BD por el momento :c");
        }
        return respuesta;
    }

    public static Respuesta guardarFoto(int idColaborador, byte[] foto) {
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(idColaborador);
                colaborador.setFoto(foto);
                int filasAfectadas = conexionBD.update("colaborador.guardar-foto", colaborador);
                conexionBD.commit();
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Foto subida correctamente");
                } else {
                    respuesta.setMensaje("No se pudo subir la foto");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        } else {
            respuesta.setMensaje("No hay conexión con la BD");
        }
        return respuesta;
    }

    public static Colaborador obtenerFoto(int idColaborador) {
        Colaborador colaborador = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                colaborador = conexionBD.selectOne("colaborador.obtener-foto", idColaborador);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaborador;
    }
}
