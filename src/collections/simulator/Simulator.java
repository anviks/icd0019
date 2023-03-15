package collections.simulator;

import java.util.*;
import java.util.List;

public class Simulator {

    private final double iterations;
    private final Hand deck = Helpers.getSuitedHand("2s3s4s5s6s7s8s9sTsJsQsKsAs2h3h4h5h6h7h8h9hThJhQhKhAh2d3d4d5d6d7d8d9dTdJdQdKdAd2c3c4c5c6c7c8c9cTcJcQcKcAc");

    public Simulator(double iterations) {
        this.iterations = iterations;
    }

    public Map<HandType, Double> calculateProbabilities() {
        Map<HandType, Double> probabilities = new HashMap<>();
        Hand[] hands = new Hand[Integer.parseInt(Math.round(iterations) + "")];
        Random random = new Random();

        for (int i = 0; i < iterations; i++) {
            Hand tempDeck = deck.copy();
            Hand hand = new Hand();

            for (int j = 0; j < 5; j++) {
                int index = random.nextInt(tempDeck.size());
                hand.addCard(tempDeck.get(index));
                tempDeck.removeCard(tempDeck.get(index));
            }

            hands[i] = hand;
        }

        List<Integer> handTypes = Arrays.stream(hands).map(hand -> hand.getHandType().ordinal()).toList();

        for (int type : handTypes.stream().distinct().toList()) {
            if (List.of(HandType.HIGH_CARD, HandType.ONE_PAIR, HandType.TWO_PAIRS, HandType.TRIPS).contains(type)) {
                probabilities.put(HandType.values()[type], Double.valueOf(Collections.frequency(handTypes, type)) / handTypes.size() * 100);
            }
        }

        return probabilities;
    }

    public double getWinningOdds(Hand player1hand, Hand player2hand) {
        int wins = 0;
        int lossesOrDraws = 0;
        Hand deck = this.deck.copy();
        Random random = new Random();
        List<Card> removedCards = new ArrayList<>();

        for (int i = 0; i < player1hand.size(); i++) {
            deck.removeCard(player1hand.get(i));
        }

        for (int i = 0; i < player2hand.size(); i++) {
            deck.removeCard(player2hand.get(i));
        }

        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < 5; j++) {
                Card card = deck.get(random.nextInt(deck.size()));
                removedCards.add(card);
                deck.removeCard(card);
                player1hand.addCard(card);
                player2hand.addCard(card);
            }

            if (player1hand.compareTo(player2hand) > 0) {
                wins++;
            } else {
                lossesOrDraws++;
            }

            for (Card card : removedCards) {
                deck.addCard(card);
                player1hand.removeCard(card);
                player2hand.removeCard(card);
            }

            removedCards.clear();
        }

        return (Double.valueOf(wins)) / (wins + lossesOrDraws) * 100;
    }

}
