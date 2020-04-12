package com.zt.ebook.request.commentRequest;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UpdateCommentRequest {
    @Column(name = "comment_content")
    private String commentContent;



}
