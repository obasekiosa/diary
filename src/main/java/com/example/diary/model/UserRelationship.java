package com.example.diary.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(RelationshipId.class)
public class UserRelationship implements Serializable {

    @Id
    private String username;

    @Id
    private String friendName;


    public UserRelationship() {}

    public UserRelationship(String username, String friendName) {
        this.username = username;
        this.friendName = friendName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
