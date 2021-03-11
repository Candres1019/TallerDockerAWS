package co.edu.escuelaing.arep.loadbalancer.APIConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Clase LogServiceAPI, encargada de realizar la petición a los contenedores
 */
public class LogServiceAPI {

    public static final LogServiceAPI _instance = new LogServiceAPI();
    private static final String path = "http://172.17.0.1:";
    private static final String solicitud = "/insertar";
    private static Integer numDocker = -1;
    private final String[] ports = {"35001", "35002", "35003"};

    /**
     * Constructor de la clase LogServiceAPI
     */
    public LogServiceAPI() {
        //Construcctor vacio
    }

    /**
     * Metodo para obtener la isntancia de la clase LogServiceAPI
     *
     * @return - Objeto de tipo LogServiceAPI
     */
    public static LogServiceAPI getInstance() {
        return _instance;
    }

    /**
     * Metodo encargado de realizar la peticion a el contenedor y devolver la respuesta del mismo
     *
     * @param jsonBody - Mensaje para insertar.
     * @return - Mensaje respuesta del contenedor
     */
    public String consultarMensajes(String jsonBody) {
        try {
            String port = roundRobinGetPort();
            URL url = new URL(path + port + solicitud);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Mandar El Post
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);

            //Leer la respuesta
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            System.out.println("Error en consultarMensajes method");
            e.printStackTrace();
            return ("Error al consultar Pa");
        }
    }

    /**
     * Metodo que controla el acceso a cada uno de los contenedores
     *
     * @return - Puerto al cual acceder.
     */
    private String roundRobinGetPort() {
        if (numDocker == 2) {
            numDocker = 0;
        } else {
            numDocker += 1;
        }
        return ports[numDocker];
    }

}
