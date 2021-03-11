package co.edu.escuelaing.arep.loadbalancer.APIConnection;

import org.junit.Assert;
import org.junit.Test;

public class LogServiceAPITest {

    @Test
    public void getInstance() {
        try {
            LogServiceAPI logServiceAPI = LogServiceAPI.getInstance();
            Assert.assertTrue(true);
        }catch (Exception e) {
            Assert.fail();
        }
    }
}