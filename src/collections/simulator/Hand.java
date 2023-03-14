package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        if (isStraightFlush()) {
            return HandType.STRAIGHT_FLUSH;
        } else if (isNumberOfAKind(4)) {
            return HandType.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return HandType.FULL_HOUSE;
        } else if (isFlush()) {
            return HandType.FLUSH;
        } else if (isStraight()) {
            return HandType.STRAIGHT;
        } else if (isNumberOfAKind(3)) {
            return HandType.TRIPS;
        } else if (isTwoPairs()) {
            return HandType.TWO_PAIRS;
        } else if (isNumberOfAKind(2)) {
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isNumberOfAKind(int number) {
        List<Integer> values = cards.stream().map(card -> card.getValue().ordinal()).toList();
        for (Integer value : values.stream().distinct().toList()) {
            if (Collections.frequency(values, value) == number) {
                return true;
            }
        }

        return false;
    }

    private boolean isFullHouse() {
        return isNumberOfAKind(3) && isNumberOfAKind(2);
    }

    private boolean isFlush() {
        List<Integer> uniqueSuits = cards.stream().map(card -> card.getSuit().ordinal()).distinct().toList();
        return uniqueSuits.size() == 1 && cards.size() == 5;
    }

    private boolean isStraight() {
        List<Integer> uniqueValues = cards.stream().map(card -> card.getValue().ordinal()).distinct().sorted().toList();
        return uniqueValues.size() == 5 && uniqueValues.get(0) + 4 == uniqueValues.get(4);
    }

    private boolean isTwoPairs() {
        List<Integer> uniqueValues = cards.stream().map(card -> card.getValue().ordinal()).distinct().sorted().toList();
        return !isNumberOfAKind(3) && uniqueValues.size() + 2 == cards.size();
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}
