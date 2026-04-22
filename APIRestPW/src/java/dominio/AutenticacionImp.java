package dominio;

import dto.RSAutenticacionColaborador;
import java.util.HashMap;
import java.util.LinkedHashMap;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;

public class AutenticacionImp {
    public static RSAutenticacionColaborador autenticarColaborador(String usuario, String password){
        RSAutenticacionColaborador respuesta = new RSAutenticacionColaborador();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null){
            try{
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("usuario", usuario);
                parametros.put("password", password);
                Colaborador colab = conexionBD.selectOne("autenticacion.colaborador", parametros);
                if(colab != null){
                    //Flujo normal credeneciales son correctas
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del colaborador: " + colab.getNombre());
                    respuesta.setColaborador(colab);
                }else{
                    //Flujo alterno 2 credeneciales son incorrectas
                    respuesta.setError(true);
                    respuesta.setMensaje("Usuario o contraseña incorrectos.");
                }

                conexionBD.close();
            }catch(Exception e){
                //Flujo alterno 3 error al consultar algo de la consulta
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la base de datos.");
        }

        return respuesta;
    }
    
    public static RSAutenticacionColaborador autenticarConductor(String usuario, String password) {
        RSAutenticacionColaborador respuesta = new RSAutenticacionColaborador();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("usuario", usuario);
                parametros.put("password", password);
                Colaborador colab = conexionBD.selectOne( "autenticacion.colaboradorConductor", parametros);
                if (colab != null) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del conductor: " + colab.getNombre());
                    respuesta.setColaborador(colab);
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("Credenciales incorrectas o el usuario no es conductor.");
                }
                
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la base de datos.");
        }
        
        return respuesta;
    }
}
