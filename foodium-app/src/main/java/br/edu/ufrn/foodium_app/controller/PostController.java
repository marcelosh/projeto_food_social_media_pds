package br.edu.ufrn.foodium_app.controller;

import br.edu.ufrn.foodium_app.controller.dto.post.CreatePostDto;
import br.edu.ufrn.foodium_app.controller.dto.post.UpdatePostDto;
import br.edu.ufrn.foodium_app.domain.model.Post;
import br.edu.ufrn.foodium_app.domain.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping
    public Post createPost(@Valid @RequestBody CreatePostDto post) {
        return postService.savePost(post);
    }

    @PutMapping
    public Post updatePost(@Valid @RequestBody UpdatePostDto post) {
        return postService.updatePost(post);
    }

    @PostMapping("/addLike/{postId}/{userId}")
    public void addLikeIntoPost(@PathVariable Integer postId, @PathVariable Integer userId) {
        postService.addLikeIntoPost(postId, userId);
    }

    @PostMapping("/deleteLike/{postId}/{userId}")
    public void deleteLikeIntoPost(@PathVariable Integer postId, @PathVariable Integer userId) {
        postService.deleteLikeIntoPost(postId, userId);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.removePost(id);
    }
}
