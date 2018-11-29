package pl.jkan.banking.clientdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
class ClientController {
    @Autowired
    ClientDetailsInMemoryRepository repository;

    @GetMapping
    public List<ClientDetails> list() {
        return repository.findAll();
    }

    @PostMapping(value = "/clients", headers = "Accept=application/json")
    public void create(@RequestBody ClientDetails clientDetails) {
        repository.save(clientDetails);
    }

    @DeleteMapping("/{clientId}")
    public void delete(@PathVariable String clientId) {
        repository.deleteById(clientId);
    }

    @GetMapping("/{clientId}")
    public ClientDetails show(@PathVariable String clientId) throws ClientNotFoundException {
        return repository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException());
    }

    @PutMapping(value = "/{clientId}", headers = "Accept=application/json")
    public void update(@PathVariable String clientId, @RequestBody ClientDetails clientDetails) throws ClientNotFoundException {
        ClientDetails loaded = repository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException());

        loaded.setFirstname(clientDetails.getFirstname());

        repository.save(loaded);
    }
}
