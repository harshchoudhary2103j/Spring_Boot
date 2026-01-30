package com.Module5.Spring_Security_App.controllers;


import com.Module5.Spring_Security_App.dto.PostDTO;
import com.Module5.Spring_Security_App.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured("ROLE_USER")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN') OR hasAuthority('POST_VIEW')")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

    @PutMapping("{postId}")
    public PostDTO updatePost(@RequestBody PostDTO inputPost,@PathVariable Long postId ) {
        return postService.updatePost(inputPost, postId);
    }


}
