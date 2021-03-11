package co.edu.escuelaing.arep.loadbalancer.SparkWebApp;

import co.edu.escuelaing.arep.loadbalancer.APIConnection.LogServiceAPI;
import com.google.gson.Gson;

import static spark.Spark.*;

public class SparkWebApp {

    /**
     * Metodo principal encargado realziar la inicicion y el llamado html, para enviar
     * y recibir datos.
     *
     * @param args - "" utilizaremos
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/static");
        init();
        post("/insertarMensajes", (request, response) -> {
            response.status(200);
            response.type("application/json");
            String respuesta = LogServiceAPI.getInstance().consultarMensajes(request.body());
            return new Gson().toJson(respuesta);
        });
    }

    /**
     * Retorna el puerto por el que deberia correr el servidor, creado para evitar errores en un ambiente de
     * despliegue no local
     *
     * @return - puerto.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
