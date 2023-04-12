package generics.connection;

import java.util.*;

public class ConnectionFinder {

    // Map<Connection source, Map<Connection endpoints, Whether they were used in findConnection or not>>
    private final Map<String, Map<String, Boolean>> connections = new HashMap<>();

    public void addAll(List<Connection> connections) {
        connections.forEach(this::add);
    }

    public void add(Connection connection) {
        addEndpoint(connection.getFrom(), connection.getTo());
        addEndpoint(connection.getTo(), connection.getFrom());
    }

    private void addEndpoint(String from, String to) {
        Map<String, Boolean> endpoints = this.connections.getOrDefault(from, new HashMap<>());
        endpoints.put(to, false);
        connections.put(from, endpoints);
    }


    public boolean hasConnection(String a, String b) {
        return !findConnection(a, b).isEmpty();
    }

    public List<String> findConnection(String a, String b) {
        var connection = connectionFinder(a, b);
        resetConnections();
        return connection;
    }

    private List<String> connectionFinder(String a, String b) {
        if (!connections.containsKey(a)) {
            return Collections.emptyList();
        }

        List<String> path = new ArrayList<>();
        path.add(a);

        for (Map.Entry<String, Boolean> directConnection : connections.get(a).entrySet()) {
            if (directConnection.getValue()) {
                continue;
            }

            directConnection.setValue(true);

            if (directConnection.getKey().equals(b)) {
                path.add(b);
                return path;
            }

            List<String> tempPath = connectionFinder(directConnection.getKey(), b);

            if (!tempPath.isEmpty() && tempPath.get(tempPath.size() - 1).equals(b)) {
                path.addAll(tempPath);
                return path;
            }
        }

        return Collections.emptyList();
    }

    private void resetConnections() {
        connections.keySet().forEach(source ->
                connections.get(source).keySet().forEach(endpoint ->
                        connections.get(source).put(endpoint, false)
                )
        );
    }
}
