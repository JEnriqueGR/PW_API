package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;

public class ClienteImp {
    
    public static Respuesta registrar(Cliente cliente) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("cliente.registrar", cliente);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Cliente registrado correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar el cliente");
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
    
    public static Respuesta editar(Cliente cliente) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("cliente.editar", cliente);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Cliente actualizado correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el cliente o no hubo cambios");
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
    
    public static Respuesta eliminar(int idCliente) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.delete("cliente.eliminar", idCliente);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Cliente eliminado correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el cliente");
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
    
    public static List<Cliente> obtenerTodos() {
        List<Cliente> clientes = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                clientes = conexionBD.selectList("cliente.obtener-todos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return clientes;
    }
    
    public static List<Cliente> buscar(String valorBusqueda) {
        List<Cliente> clientes = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                clientes = conexionBD.selectList("cliente.buscar", valorBusqueda);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return clientes;
    }
}
