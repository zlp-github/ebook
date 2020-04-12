package com.zt.ebook.response.bookResponse;

import lombok.Data;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-03-20 21:19
 * Description:张立朋，写点注释吧!!
 */
@Data
public class BookChapterResponse {
    List<ContentResponse> bookChapterList;
    Long total;
}
