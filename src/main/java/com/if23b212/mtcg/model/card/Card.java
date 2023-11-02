package com.if23b212.mtcg.model.card;

import com.if23b212.mtcg.model.user.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    private String name;

    private double damage;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private CardType cardType;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private ElementType elementType;

    // Assuming you have a ManyToOne relationship with User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Card() {
        // Default constructor for JPA
    }

    public Card(UUID id, String name, double damage, CardType cardType, ElementType elementType) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.cardType = cardType;
        this.elementType = elementType;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CardType getCardType() {
        return cardType;
    }
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
    public ElementType getElementType() {
        return elementType;
    }
    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }
    public double getDamage() {
        return damage;
    }
}
