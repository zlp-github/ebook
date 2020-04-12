package com.zt.ebook.request.secondCommentRequest;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UpdateSecondCommentRequest {

    @Column(name = "comment_content")
    private String commentContent;

}
