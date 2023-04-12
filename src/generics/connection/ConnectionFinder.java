package generics.connection;

import java.util.*;

public class ConnectionFinder {

    // Map<Connection source, Map<Connection endpoints, Whether they were used in findConnection or not>>
    private final Map<String, Map<String, Boolean>> connections = new HashMap<>();

    public void addAll(List<Connection> connections) {
        connections.forEach(this::add);
    }

    public void add(Connection connection) {
        Map<String, Boolean> endpoints = this.connections.getOrDefault(connection.getFrom(), new HashMap<>());
        endpoints.put(connection.getTo(), false);
        connections.put(connection.getFrom(), endpoints);

        Map<String, Boolean> endpointsReversed = this.connections.getOrDefault(connection.getTo(), new HashMap<>());
        endpointsReversed.put(connection.getFrom(), false);
        connections.put(connection.getTo(), endpointsReversed);
    }

    public boolean hasConnection(String a, String b) {
        return !findConnection(a, b).isEmpty();
    }

    public List<String> findConnection(String a, String b) {
        var connection = findConnections(a, b);
        resetConnections();
        return connection;
    }

    private List<String> findConnections(String a, String b) {
        List<String> path = new ArrayList<>();

        if (!connections.containsKey(a)) {
            return Collections.emptyList();
        }

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

            List<String> tempPath = findConnections(directConnection.getKey(), b);

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
