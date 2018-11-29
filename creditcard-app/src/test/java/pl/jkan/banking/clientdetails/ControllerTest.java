package pl.jkan.banking.clientdetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
    private static final String clientId = "jakkan007";

    @Autowired
    ClientController clientController;

    @Test
    public void listingClients() throws Exception {
        Assert.assertNotNull(clientController.list());
    }

    @Test
    public void createAndList() throws Exception {
        ClientDetails client = clientIdentifiedWith(clientId);
        clientController.create(client);

        List<ClientDetails> clients = clientController.list();

        Assert.assertTrue(clients.size() > 0);
    }

    @Test
    public void createAndGet() throws Exception {
        ClientDetails client = clientIdentifiedWith(clientId);
        clientController.create(client);

        ClientDetails details = clientController.show(clientId);
        Assert.assertTrue(details.getId().equals(clientId));
    }

    @Test(expected = ClientNotFoundException.class)
    public void getNotExisting() throws Exception {
        ClientDetails details = clientController.show("not-exists-for-sure");
    }

    @Test
    public void createAndDelete() throws Exception {
        ClientDetails client = clientIdentifiedWith(clientId);
        clientController.create(client);

        clientController.delete(clientId);
        List<ClientDetails> clients = clientController.list();

        Assert.assertTrue(clients.size() == 0);
    }

    @Test
    public void updateDetails() throws Exception {
        ClientDetails client = clientIdentifiedWith(clientId);
        clientController.create(client);

        ClientDetails toBeUpdated = clientIdentifiedWith(clientId);
        toBeUpdated.setFirstname("Jakub");
        clientController.update(clientId, toBeUpdated);

        ClientDetails show = clientController.show(clientId);
        Assert.assertEquals("Jakub", show.getFirstname());
    }

    private ClientDetails clientIdentifiedWith(String clientId) {
        return new ClientDetails(clientId, "random name");
    }
}
