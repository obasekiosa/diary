package com.example.diary.model;

import java.io.Serializable;
import java.util.Objects;

public class RelationshipId implements Serializable {

    private String username;
    private String friendName;

    public RelationshipId() {}


    public RelationshipId(String username, String friendName) {
        this.username = username;
        this.friendName = friendName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, friendName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RelationshipId relationship = (RelationshipId) obj;
        return username.equals(relationship.username) && friendName.equals(relationship.friendName);
    }
}
