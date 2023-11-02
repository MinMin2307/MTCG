package com.if23b212.mtcg.model.user;

import com.if23b212.mtcg.model.card.Card;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users") // Optional: specify a table name if you don't want the default name "User"
public class User {


    public User() {}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(unique = true) // Assuming usernames should be unique
    private String username;

    private String password;

    private int coins;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id") // This assumes there's a user_id column in the Card table
    private List<Card> stack;

    public User(String username, String password, int coins, List<Card> stack) {
        this.username = username;
        this.password = password;
        this.coins = coins;
        this.stack = stack;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public List getStack() {
        return stack;
    }
    public void setStack(List stack) {
        this.stack = stack;
    }
}
