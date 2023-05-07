package bonus.dna;

import java.util.Set;

public class GenerationResult {

        private final String first;
        private final String second;
        private final Set<String> overLaps;

        public GenerationResult(String first, String second, Set<String> overLaps) {
            this.first = first;
            this.second = second;
            this.overLaps = overLaps;
        }

        public String getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }

        public Set<String> getSubsequences() {
            return overLaps;
        }
    }
