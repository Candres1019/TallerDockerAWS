package co.edu.escuelaing.arep.logservice.persistence.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MongoDataBaseConnectionTest {

    private static MongoDataBaseConnection mongoDataBaseConnection;

    @Before
    public void antesDe() {
        mongoDataBaseConnection = new MongoDataBaseConnection();
    }

    @Test
    public void insertMensaje() {
        try {
            mongoDataBaseConnection.insertMensaje("Mensaje De Prueba");
            Assert.assertTrue(true);
        }catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void consultarMensajes() {
        try {
            mongoDataBaseConnection.consultarMensajes();
            Assert.assertTrue(true);
        }catch (Exception e){
            Assert.fail();
        }
    }

}