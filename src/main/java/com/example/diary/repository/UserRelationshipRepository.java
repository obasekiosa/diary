package com.example.diary.repository;

import com.example.diary.model.RelationshipId;
import com.example.diary.model.UserRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRelationshipRepository extends JpaRepository<UserRelationship, RelationshipId> {
}
