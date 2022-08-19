package com.example.Twitter.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tweet")
public class Tweet implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long tweet_id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "content")
    private String content;

    protected Tweet() {
    }

    public Tweet(long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, content='%s']", userId, content);
    }

    public String getContent() {
        return content;
    }
}
