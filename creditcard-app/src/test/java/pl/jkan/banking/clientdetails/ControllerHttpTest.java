package pl.jkan.banking.clientdetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerHttpTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnClientList() throws Exception {
        String rsp = this.restTemplate.getForObject(
                "http://localhost:" + port + "/clients",
                String.class);

        Assert.assertTrue(rsp.contains("[]"));
    }

    @Test
    public void allowCreateClient() throws Exception {
        this.restTemplate.postForObject("http://localhost:" + port + "/clients", "{\"firstname\": \"jakub\"}", Object.class);

    }
}
