package dominio;

import dto.Respuesta;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;

public class EnvioImp {

    private static String generarNumeroGuia() {
        return "GUIA-" + System.currentTimeMillis();
    }

    public static Respuesta registrar(Envio envio) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                envio.setNumeroGuia(generarNumeroGuia());
                envio.setIdEstatus(1); // recibido en sucursal
                envio.setCostoTotal(0);

                int filas = conexionBD.insert("envio.registrar", envio);
                conexionBD.commit();

                if (filas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Envío registrado correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar el envío");
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
            respuesta.setMensaje("No hay conexión con la BD");
        }
        return respuesta;
    }

    public static Respuesta editar(Envio envio) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filas = conexionBD.update("envio.editar", envio);
                conexionBD.commit();

                if (filas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Envío actualizado correctamente");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el envío o no hubo cambios");
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

    public static Envio consultarPorGuia(String numeroGuia) {
        Envio envio = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                envio = conexionBD.selectOne("envio.consultar-por-guia", numeroGuia);
            } finally {
                conexionBD.close();
            }
        }
        return envio;
    }

    public static Respuesta actualizarEstatus(int idEnvio, int idEstatus) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("idEnvio", idEnvio);
                params.put("idEstatus", idEstatus);

                int filas = conexionBD.update("envio.actualizar-estatus", params);
                conexionBD.commit();

                if (filas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Estatus del envío actualizado");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo actualizar el estatus");
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
    
    public static Respuesta recalcularCosto(int idEnvio) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD == null) {
            respuesta.setError(true);
            respuesta.setMensaje("No hay conexión con la BD por el momento");
            return respuesta;
        }

        try {
            //obtener envio
            Envio envio = conexionBD.selectOne("envio.obtener-por-id", idEnvio);
            if (envio == null) {
                respuesta.setError(true);
                respuesta.setMensaje("No se encontró el envío");
                return respuesta;
            }

            //obtener CP desde sucursal
            pojo.Sucursal suc = conexionBD.selectOne("sucursal.obtener-por-id", envio.getIdSucursalOrigen());
            if (suc == null || suc.getCodigoPostal() == null) {
                respuesta.setError(true);
                respuesta.setMensaje("No se pudo obtener el código postal de la sucursal origen");
                return respuesta;
            }
            String cpOrigen = suc.getCodigoPostal();
            String cpDestino = envio.getCodigoPostal();
            if (cpDestino == null || cpDestino.trim().isEmpty()) {
                respuesta.setError(true);
                respuesta.setMensaje("Código postal de destino no disponible en el envío");
                return respuesta;
            }

            //contar paquetes del envío
            int numPaquetes = conexionBD.selectOne("paquete.contar-por-envio", idEnvio);
            
            if (numPaquetes <= 0) {
                respuesta.setError(true);
                respuesta.setMensaje("No hay paquetes registrados para calcular el costo. Registra paquetes primero.");
                return respuesta;
            }

            //llamar al api de distancia externo
            double distanciaKM = -1;
            String urlStr = "http://sublimas.com.mx:8080/calculadora/api/envios/distancia/"
                    + cpOrigen + "," + cpDestino;
            java.net.HttpURLConnection conn = null;
            java.io.InputStream is = null;
            try {
                java.net.URL url = new java.net.URL(urlStr);
                conn = (java.net.HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);

                int codigo = conn.getResponseCode();
                is = (codigo == HttpURLConnection.HTTP_OK) ? conn.getInputStream() : conn.getErrorStream();
                StringBuilder sb = new StringBuilder();
                try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is, "UTF-8"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        sb.append(linea);
                    }
                }
                if (codigo != HttpURLConnection.HTTP_OK) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Error al obtener distancia: " + sb.toString());
                    return respuesta;
                }
                
                com.google.gson.JsonObject json = new com.google.gson.JsonParser().parse(sb.toString()).getAsJsonObject();
                if (json.has("distanciaKM")) {
                    distanciaKM = json.get("distanciaKM").getAsDouble();
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("Respuesta inválida del servicio de distancia");
                    return respuesta;
                }
            } catch (Exception ex) {
                respuesta.setError(true);
                respuesta.setMensaje("No se pudo obtener distancia: " + ex.getMessage());
                return respuesta;
            } finally {
                if (is != null) try {
                    is.close();
                } catch (Exception ignore) {
                }
                if (conn != null) {
                    conn.disconnect();
                }
            }

            //calcular costo por km
            double costoPorKm;
            if (distanciaKM >= 1 && distanciaKM <= 200) {
                costoPorKm = 4.0;
            } else if (distanciaKM <= 500) {
                costoPorKm = 3.0;
            } else if (distanciaKM <= 1000) {
                costoPorKm = 2.0;
            } else if (distanciaKM <= 2000) {
                costoPorKm = 1.0;
            } else {
                costoPorKm = 0.5;
            }

            //costo adicional por número de paquetes
            double extra;
            if (numPaquetes == 1) {
                extra = 0.0;
            } else if (numPaquetes == 2) {
                extra = 50.0;
            } else if (numPaquetes == 3) {
                extra = 80.0;
            } else if (numPaquetes == 4) {
                extra = 110.0;
            } else {
                extra = 150.0;
            }

            //calculo final
            double costo = distanciaKM * costoPorKm + extra;
            //redondear
            costo = Math.round(costo * 100.0) / 100.0;
            //actualizar la BD
            java.util.Map<String, Object> params = new java.util.HashMap<>();
            params.put("idEnvio", idEnvio);
            params.put("costoTotal", costo);

            int filas = conexionBD.update("envio.actualizar-costo", params);
            conexionBD.commit();

            if (filas > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Costo recalculado correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("No se pudo actualizar el costo del envío");
            }

        } catch (Exception e) {
            try {
                conexionBD.rollback();
            } catch (Exception ignored) {
            }
            respuesta.setError(true);
            respuesta.setMensaje(e.getMessage());
        } finally {
            conexionBD.close();
        }

        return respuesta;
    }
    
    public static HashMap<String, Object> obtenerTodas() {
        HashMap<String, Object> respuesta = new HashMap<>();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                List<Envio> lista = conexionBD.selectList("envio.obtener-todos");
                respuesta.put("error", false);
                respuesta.put("envios", lista);
            } catch (Exception e) {
                respuesta.put("error", true);
                respuesta.put("mensaje", e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.put("error", true);
            respuesta.put("mensaje", "No hay conexión con la BD");
        }

        return respuesta;
    }
    
    public static Respuesta actualizarMotivoEstatus(int idEnvio, String motivo) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("idEnvio", idEnvio);
                params.put("motivoEstatus", motivo);
                
                int filas = conexionBD.update("envio.actualizar-motivo-estatus", params);
                conexionBD.commit();
                if (filas > 0) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Motivo de estatus actualizado");
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo actualizar el motivo");
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
    
}
