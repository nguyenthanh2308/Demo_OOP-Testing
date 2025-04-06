package controller;

import dao.PostDAO;
import model.Post;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class PostController {
    private PostDAO postDAO = new PostDAO(); // DAO đã dùng Hibernate
    private List<Post> posts;

    private String title;
    private String content;

    @PostConstruct
    public void init() {
        posts = postDAO.getAllPosts();
    }

    public String addPost() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        postDAO.addPost(post);
        return "welcome.xhtml?faces-redirect=true";
    }

    // Getter/Setter
    public List<Post> getPosts() {
        return posts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
