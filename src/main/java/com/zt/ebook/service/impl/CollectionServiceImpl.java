package com.zt.ebook.service.impl;


import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.dao.BookDao;
import com.zt.ebook.dao.CollectionDao;
import com.zt.ebook.pojo.Book;
import com.zt.ebook.pojo.TkCollection;
import com.zt.ebook.pojo.User;
import com.zt.ebook.request.collectionRequest.AddCollectionRequest;
import com.zt.ebook.response.collectionResponse.FindCollectionResponse;
import com.zt.ebook.service.CollectionService;
import com.zt.ebook.utils.SessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;

    @Autowired
    BookDao bookDao;

    @Override
    public SzpJsonResult addCollection(AddCollectionRequest request) {
        TkCollection tkCollection = new TkCollection();
        tkCollection.setbId(request.getBId());
        tkCollection.setbImg(request.getBImg());
        tkCollection.setbName(request.getBName());
        tkCollection.setuId(request.getUId());
        Integer integer = collectionDao.addCollection(tkCollection);
        if (integer==1){
            return SzpJsonResult.ok();
        }
        return SzpJsonResult.errorException("添加失败");
    }

    @Override
    public SzpJsonResult deleteCollection(Integer id) {
        Integer integer = collectionDao.deleteCollection(id);
        if (integer==1){
            return SzpJsonResult.ok();
        }
        return SzpJsonResult.errorException("删除失败");

    }

    @Override
    public SzpJsonResult deleteCollectionList(List<Integer> ids) {
        return SzpJsonResult.ok(collectionDao.deleteCollectionList(ids));
    }

    @Override
    public List<FindCollectionResponse> findCollectionByUserId(Integer uId) {
        List<TkCollection> list = collectionDao.findCollectionByUserId(uId);
        return showBookAll(list);
    }
    private List<FindCollectionResponse> showBookAll(List<TkCollection> List) {
        List<FindCollectionResponse> list = new ArrayList<>();
        for (TkCollection tkCollection : List) {
            FindCollectionResponse findCollectionResponse = new FindCollectionResponse();
            Integer bId = tkCollection.getbId();
            Book book = bookDao.findBookById(bId);
            BeanUtils.copyProperties(tkCollection, findCollectionResponse);
            findCollectionResponse.setAuthor(book.getAuthor());
            list.add(findCollectionResponse);
        }
        return list;
    }
} 