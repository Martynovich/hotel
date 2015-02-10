package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Client;
import com.martynovich.hotel.util.ConnectionPoint;
import static org.junit.Assert.*;

import org.junit.*;

/**
 * @author
 */
public class ClientDaoIntegrationTest {
    private ConnectionPoint connectionPoint;
    private ClientDao clientDao;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @BeforeClass
    public static void beforeClass() {

    }

    @AfterClass
    public static void afterClass() {

    }

    @Test
    public void testValidInsert()  {
        connectionPoint = ConnectionPoint.getInstance();
        clientDao = new ClientDao();
        Client client = new Client();
//        todo: check other fields
        String expectedLogin = "testLogin";
        client.setLogin(expectedLogin);
        String expectedPass = "testPass";
        client.setPassword(expectedPass);

        clientDao.insert(client);
        Client actualClient = clientDao.read(expectedLogin);

        assertNotNull("Extracted client shouldn't be null", actualClient);
        assertEquals("Logins are not equals", expectedLogin, actualClient.getLogin());
        assertEquals("Passwords are not equals", expectedPass, actualClient.getPassword());

    }
}
