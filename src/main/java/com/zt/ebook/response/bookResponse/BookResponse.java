package com.zt.ebook.response.bookResponse;


import com.zt.ebook.pojo.Book;
import lombok.Data;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-02-26 13:53
 * Description:张立朋，写点注释吧!!
 */
@Data
public class BookResponse {

    List<Book> bookList;

    Long total;
}
