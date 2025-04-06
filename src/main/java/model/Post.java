package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(length = 50)
    private String status;

    // Nếu bạn không dùng user_id thì có thể bỏ đoạn này
    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;

    // Constructors
    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    public Post(String title, String body, String status) {
        this.title = title;
        this.body = body;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    // Getters và Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContent(String content) {
    }


    // Nếu có User
    /*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    */
}
