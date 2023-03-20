package generics.recursion;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recursion {

    public List<String> getParts(Path path) {
        List<String> parts = new ArrayList<>();

        parts.add(path.getFileName().toString());
        while (path.getParent() != null) {
            parts.add(path.getParent().getFileName().toString());
            path = path.getParent();
        }

        Collections.reverse(parts);

        return parts;
    }

    public List<String> getParts2(Path path) {
        if (path != null) {
            System.out.println(path.getFileName());
            getParts2(path.getParent());
        }

        return null;
    }

    public List<String> getParts3(Path path, List<String> parts) {
        if (path != null) {
            parts.add(path.getFileName().toString());
            getParts3(path.getParent(), parts);
        }

        return parts;
    }

    public List<String> getParts4(Path path) {
        if (path != null) {
            List<String> parts = getParts4(path.getParent());
            parts.add(path.getFileName().toString());
            return parts;
        }

        return new ArrayList<>();
    }
}
