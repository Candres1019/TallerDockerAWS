package co.edu.escuelaing.arep.logservice.persistence;

/**
 * Interfaz para el manejo de la conexión con base de datos
 */
public interface DataBaseConnection {

    /**
     * Metodo para insertar mensaje a la base de datos.
     *
     * @param mensaje - Mensaje a insertar.
     */
    void insertMensaje(String mensaje);

    /**
     * Metodo para consultar mensajes.
     *
     * @return - Cadena JSON de los datos del mensaje.
     */
    String consultarMensajes();

}
