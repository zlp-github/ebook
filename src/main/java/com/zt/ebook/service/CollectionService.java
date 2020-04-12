package com.zt.ebook.service;


import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.request.collectionRequest.AddCollectionRequest;
import com.zt.ebook.response.collectionResponse.FindCollectionResponse;

import java.util.List;

public interface CollectionService {
    SzpJsonResult addCollection(AddCollectionRequest request);
    SzpJsonResult deleteCollection(Integer id);
    SzpJsonResult deleteCollectionList(List<Integer> ids);
    List<FindCollectionResponse> findCollectionByUserId(Integer uId);
}
