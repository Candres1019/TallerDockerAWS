package co.edu.escuelaing.arep.logservice.sparkweb;

import co.edu.escuelaing.arep.logservice.persistence.impl.MongoDataBaseConnection;
import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONObject;

import static spark.Spark.*;

public class SparkWebApp {

    private static final MongoDataBaseConnection mongoDataBaseConnection = new MongoDataBaseConnection();

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
        post("/insertar", (request, response) -> {
            response.status(200);
            response.type("application/json");
            JSONObject jsonObject = new JSONObject(request.body());
            mongoDataBaseConnection.insertMensaje(jsonObject.get("mensaje").toString());
            return new Gson().toJson(mongoDataBaseConnection.consultarMensajes());
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
