package com.zt.ebook.service;


import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.request.secondCommentRequest.AddSecondCommentRequest;
import com.zt.ebook.request.secondCommentRequest.UpdateSecondCommentRequest;
import com.zt.ebook.response.secondCommentResponse.FindSecondCommentResponse;

import java.util.List;

public interface SecondCommentService {
    SzpJsonResult addSecondComment(AddSecondCommentRequest request);
    SzpJsonResult deleteSecondCommentById(Integer id);
    SzpJsonResult  updateSecondComment(UpdateSecondCommentRequest request);
    List<FindSecondCommentResponse> findSecondComment(Integer commentId);
}
