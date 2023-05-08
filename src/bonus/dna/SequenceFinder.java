package bonus.dna;

import java.util.*;

public class SequenceFinder {

    private final int minSequenceLength;
    private final int maxCapLength;

    public SequenceFinder(int minSequenceLength, int maxCapLength) {
        this.minSequenceLength = minSequenceLength;
        this.maxCapLength = maxCapLength;
    }

    public Set<String> findMatchingSubsequences(String firstSequence,
                                                String secondSequence) {
        Set<String> matches = new HashSet<>();

        System.out.println(firstSequence);
        System.out.println(secondSequence);

        char[] seq1 = firstSequence.toCharArray();
        char[] seq2 = secondSequence.toCharArray();

        for (int i = 0; i < seq1.length; i++) {
            for (int j = 0; j < seq2.length; j++) {
                if (seq1[i] == seq2[j]) {
                    StringBuilder result = compare(seq1, seq2, i, j);
                    if (result.length() >= minSequenceLength) {
                        matches.add(result.toString());
                    }
                }
            }
        }

        removeDuplicates(matches);

        return matches;
    }


    /**
     * Upon finding two matching characters from a sequence
     *
     * @param seq1
     * @param seq2
     * @param pos1
     * @param pos2
     * @return
     */
    private StringBuilder compare(char[] seq1, char[] seq2, int pos1, int pos2) {
        int errors = 0;
        StringBuilder common = new StringBuilder();
        int matchingLength = 0;

        while (pos1 < seq1.length && pos2 < seq2.length) {
            if (seq1[pos1] != seq2[pos2]) {
                errors++;

                if (matchingLength < minSequenceLength) {
                    errors += matchingLength;
                }

                if (errors > maxCapLength) {
                    break;
                }

                matchingLength = 0;
            } else {
                matchingLength++;
            }

            common.append(seq1[pos1]);
            pos1++;
            pos2++;

        }

        pos1--;
        pos2--;

        if (matchingLength < minSequenceLength) {
            while (matchingLength > 0) {
                common.deleteCharAt(common.length() - 1);
                matchingLength--;
                pos1--;
                pos2--;
            }
        }

        try {
            while (seq1[pos1] != seq2[pos2]) {
                common.deleteCharAt(common.length() - 1);
                pos1--;
                pos2--;
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            return new StringBuilder("hi");
        }

        return common;
    }

    /**
     * Remove a sequence from a set, if that sequence is fully contained by another sequence.
     *
     * @param sequences set of sequences to alter
     */
    private void removeDuplicates(Set<String> sequences) {
        Set<String> sequencesToRemove = new HashSet<>();

        for (String seq1 : sequences) {
            for (String seq2 : sequences) {
                if (!seq1.equals(seq2) && seq1.contains(seq2)) {
                    sequencesToRemove.add(seq2);
                }
            }
        }

        sequences.removeAll(sequencesToRemove);
    }
}