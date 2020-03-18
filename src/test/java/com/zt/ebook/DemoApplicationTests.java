package com.zt.ebook;

import com.zt.ebook.common.GetDataByUrl;
import com.zt.ebook.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void getData(){
        GetDataByUrl getDataByUrl = new GetDataByUrl();
        String url = "http://www.shenzepengzuishuai.cn/sringmvcphoto-1.0-SNAPSHOT/upload/5cdffb8bce6547e58f128f3f4676a3e7.txt";
        String fileNameDirs = getDataByUrl.getGetDateByUrl(url);
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        bookServiceImpl.readBook(fileNameDirs);

    }

}
