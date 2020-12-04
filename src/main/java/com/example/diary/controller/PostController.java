package com.example.diary.controller;

import com.example.diary.exceptions.UserNotFoundException;
import com.example.diary.model.Post;
import com.example.diary.model.UserRelationship;
import com.example.diary.repository.PostsRepository;
import com.example.diary.repository.UserRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {
    private final PostsRepository postsRepository;

//    @Autowired
    private final UserRelationshipRepository relationshipRepository;

    @Autowired
    PostController(PostsRepository postsRepository, UserRelationshipRepository relationshipRepository) {
        this.postsRepository = postsRepository;
        this.relationshipRepository = relationshipRepository;
    }

    // Aggregate root

    @GetMapping("/posts")
    List<Post> all() {
        return postsRepository.findAll();
    }

    @PostMapping("/posts")
    Post newPost(@RequestBody Post newPost) {
        newPost.setCreatedAt(new Date());
        return postsRepository.save(newPost);
    }

    // Single item

    @GetMapping("/posts/{id}")
    Post one(@PathVariable Long id) {

        return postsRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/posts/{username}")
    List<Post> usersPost(@PathVariable String username) {
        Post post = new Post();
        post.setUsername(username);
        return postsRepository.findAll(Example.of(post));
//                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @GetMapping("/posts/friends/{username}")
    List<Post> friendsPost(@PathVariable String username) {

        // create model relationship to use as example
        UserRelationship relationships = new UserRelationship();
        relationships.setUsername(username);

        // get all similar relationships
        List<UserRelationship> results = relationshipRepository.findAll(Example.of(relationships));

        List<Post> posts = new ArrayList<>();
        for(UserRelationship user : results) {
            Post post = new Post();
            post.setUsername(user.getFriendName());

            // get all posts that have username as friend name
            posts.addAll(postsRepository.findAll(Example.of(post)));
        }

        return posts;
    }

//    @PutMapping("/employees/{id}")
//    User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//    }

    @DeleteMapping("/posts/{id}")
    void deleteUser(@PathVariable Long id) {
        postsRepository.deleteById(id);
    }
}
