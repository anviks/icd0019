package collections.simulator;

import inheritance.constructor.Car;

import java.util.*;
import java.util.stream.Stream;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private final List<Card> cards = new ArrayList<>();
    private List<Integer> valuesList;
    private List<Integer> uniqueValuesList;
    private List<Integer> uniqueSuitsList;

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public int size() {
        return cards.size();
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public Hand copy() {
        Hand result = new Hand();
        result.cards.addAll(cards);
        return result;
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        valuesList = cards.stream().map(card -> card.getValue().ordinal()).sorted().toList();
        uniqueValuesList = cards.stream().map(card -> card.getValue().ordinal()).distinct().sorted().toList();
        uniqueSuitsList = cards.stream().map(card -> card.getSuit().ordinal()).distinct().toList();

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
        for (Integer value : uniqueValuesList) {
            if (Collections.frequency(valuesList, value) == number) {
                return true;
            }
        }

        return false;
    }

    private boolean isFullHouse() {
        return isNumberOfAKind(3) && isNumberOfAKind(2);
    }

    private boolean isFlush() {
        return uniqueSuitsList.size() == 1 && cards.size() == 5;
    }

    private boolean isStraight() {
        return uniqueValuesList.size() == 5
                && (uniqueValuesList.get(0) + 4 == uniqueValuesList.get(4) || uniqueValuesList.equals(List.of(0, 1, 2, 3, 12)));
    }

    private boolean isTwoPairs() {
        return !isNumberOfAKind(3) && uniqueValuesList.size() + 2 == cards.size();
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
        if (getHandType().ordinal() > other.getHandType().ordinal()) {
            return 1;
        } else if (getHandType().ordinal() < other.getHandType().ordinal()) {
            return -1;
        } else if (getHandType() == HandType.HIGH_CARD) {
            List<Integer> values = cards.stream().map(card -> card.getValue().ordinal()).sorted().toList();
            List<Integer> otherValues = other.cards.stream().map(card -> card.getValue().ordinal()).sorted().toList();
            int largest = values.get(values.size() - 1);
            int largestOther = otherValues.get(otherValues.size() - 1);

            return Integer.compare(largest, largestOther);
        } else {
            List<Integer> values = cards.stream().map(card -> card.getValue().ordinal()).sorted(Comparator.reverseOrder()).toList();
            List<Integer> otherValues = other.cards.stream().map(card -> card.getValue().ordinal()).sorted(Comparator.reverseOrder()).toList();

            for (int i = 0; i < (values.size() + otherValues.size()) / 2; i++) {
                if (values.get(i) > otherValues.get(i)) {
                    return 1;
                } else if (values.get(i) < otherValues.get(i)) {
                    return -1;
                }
            }

            return 0;
        }
    }
}
