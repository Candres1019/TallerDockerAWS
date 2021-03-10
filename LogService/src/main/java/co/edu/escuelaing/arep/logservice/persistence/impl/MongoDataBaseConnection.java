package co.edu.escuelaing.arep.logservice.persistence.impl;

import co.edu.escuelaing.arep.logservice.persistence.DataBaseConnection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de la conexión a la base de datos MONGODB
 */
public class MongoDataBaseConnection implements DataBaseConnection {

    private MongoCollection<Document> coleccion;

    /**
     * Constructor de la clase MongoDataBaseConnection.
     */
    public MongoDataBaseConnection() {
        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://andres:12345asd@lab-arep-docker.rjg7m.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
            MongoClient mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("LabArepDocker");
            coleccion = database.getCollection("RegistroMensajes");
        } catch (Exception e) {
            System.out.println("No se pudo crear la conexión");
        }
    }

    /**
     * Metodo para insertar mensaje a la base de datos.
     *
     * @param mensaje - Mensaje a insertar.
     */
    @Override
    public void insertMensaje(String mensaje) {
        try {
            Map<String, Object> document = new HashMap<String, Object>();
            document.put("Mensaje", mensaje);
            document.put("Fecha", new Date().toString());
            coleccion.insertOne(new Document(document));
        } catch (Exception e) {
            System.out.println("No se pudo insertar los datos");
        }
    }

    /**
     * Metodo para consultar mensajes.
     *
     * @return - Cadena JSON de los datos del mensaje.
     */
    @Override
    public String consultarMensajes() {
        StringBuilder jsonData = new StringBuilder("{ \"Mensajes\": [");
        int cont = 0;
        FindIterable<Document> resultados = coleccion.find().sort(Sorts.descending("Fecha"));
        for (Document document : resultados) {
            String jsonMini = "";
            if (cont < 10) {
                jsonMini = "{\"Mensaje\": \"" + document.get("Mensaje") + "\", \"Fecha\": \"" + document.get("Fecha") + "\"}";
                jsonData.append(jsonMini);
            }
            cont = cont + 1;
        }
        jsonData.append("]}");
        return jsonData.toString();
    }

}