package com.if23b212.mtcg.model.card;

import java.util.List;

public class Package {
    private List<Card> cards;

    public Package(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("A package must contain exactly 5 cards.");
        }
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
