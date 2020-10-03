package com.toan.ecommercedemo.entities;

import com.toan.ecommercedemo.enums.CommentStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comment_image")
public class CommentImage extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name = "from_tiki")
    private Boolean fromTiki;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Boolean getFromTiki() {
        return fromTiki;
    }

    public void setFromTiki(Boolean fromTiki) {
        this.fromTiki = fromTiki;
    }
}
