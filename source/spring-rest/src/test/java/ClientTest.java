/**
 * author: Martin Janousek
 */

import cz.janousek.springREST.client.ServiceApi;
import cz.janousek.springREST.model.pojo.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import retrofit.RestAdapter;


/**
 *
 * @author martin
 */
public class ClientTest {
    
    public ClientTest() {
    }
    
    private static final String SERVER = "http://localhost:8080/springREST/";

	
    private ServiceApi svcApi = new RestAdapter.Builder()
                    .setEndpoint(SERVER).build()
                    .create(ServiceApi.class);

    @Test
    public void testService() throws Exception {
            System.out.println("Zacinam");
            String str = svcApi.test();
            System.out.println("VYSLEDEK");
            assertEquals(str, "ahoj");
    }
            
    @Test
    public void testUser() throws Exception {
            User u = svcApi.testUser();
            System.out.println(u.getName());
            assertEquals(u.getName(), "Martin");
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

}
