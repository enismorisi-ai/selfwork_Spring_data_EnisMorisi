package it.aulab.selfwork_spring_data.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
// @JsonIgnoreProperties({"author"})
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100)
    private String title;

    @Column(nullable=false, length=100)
    private String body;
    
    // camelCase e snakeCase coincidono per la JPA, ma se non sei sicuro puoi sempre specificare il nome
    @Column(nullable=true, length=8)
    private String publishDate;

    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonIgnoreProperties({"posts"})
    private Author author;

    @OneToMany(mappedBy="post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"posts"})
    private List<Comment> comments = new ArrayList<Comment>();


    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
}