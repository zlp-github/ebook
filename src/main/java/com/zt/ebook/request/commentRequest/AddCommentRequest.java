package com.zt.ebook.request.commentRequest;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AddCommentRequest {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "comment_content")
    private String commentContent;


}
