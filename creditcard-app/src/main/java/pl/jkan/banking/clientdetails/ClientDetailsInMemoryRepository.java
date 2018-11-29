package pl.jkan.banking.clientdetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class ClientDetailsInMemoryRepository {
    private Map<String, ClientDetails> clients = new ConcurrentHashMap<>();

    public void save(ClientDetails clientDetails) {
        clients.put(clientDetails.getId(), clientDetails);
    }

    public Optional<ClientDetails> findById(String id) {
        return Optional.ofNullable(clients.get(id));
    }

    public List<ClientDetails> findAll() {
        return clients.values().stream()
                .collect(Collectors.toList());
    }

    public void deleteById(String clientId) {
        clients.remove(clientId);
    }
}
