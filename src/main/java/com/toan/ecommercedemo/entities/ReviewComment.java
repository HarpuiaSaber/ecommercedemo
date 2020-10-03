package com.toan.ecommercedemo.entities;

import com.toan.ecommercedemo.enums.CommentStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review_comment")
public class ReviewComment extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name = "is_helpful")
    private Boolean isHelpful;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Boolean getHelpful() {
        return isHelpful;
    }

    public void setHelpful(Boolean helpful) {
        isHelpful = helpful;
    }
}
