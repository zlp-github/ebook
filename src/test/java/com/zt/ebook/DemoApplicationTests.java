package com.zt.ebook;

import com.zt.ebook.service.RedisService;
import com.zt.ebook.service.impl.BookServiceImpl;
import com.zt.ebook.utils.GetDataByUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
    }

    @Test
    public void getData(){
        GetDataByUrl getDataByUrl = new GetDataByUrl();
        String url = "http://www.shenzepengzuishuai.cn:8080/sringmvcphoto-1.0-SNAPSHOT/upload/95ba89a9b6fa4a6583771a49e54447b4.txt";
        String fileNameDirs = getDataByUrl.getGetDateByUrl(url);
//        String fileNameDirs = "src/main/java/com/zt/ebook/utils/bookFile/demo.txt";
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        bookServiceImpl.readBook(fileNameDirs,1,5);

    }

}
