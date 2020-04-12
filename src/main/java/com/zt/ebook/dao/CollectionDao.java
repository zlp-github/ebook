package com.zt.ebook.dao;


import com.zt.ebook.mapper.TkCollectionMapper;
import com.zt.ebook.pojo.TkCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class CollectionDao {
    @Autowired
    private TkCollectionMapper collectionMapper;
    public Integer addCollection(TkCollection tkCollection){
        return collectionMapper.insert(tkCollection);
    }
    public Integer deleteCollection(Integer id){
        return collectionMapper.deleteByPrimaryKey(id);
    }
    public Integer deleteCollectionList(List<Integer> ids){
        Example example=new Example(TkCollection.class);
        example.createCriteria().andIn("id",ids);
        return  collectionMapper.deleteByExample(example);
    }
    public List<TkCollection> findCollectionByUserId(Integer uId){
        Example example=new Example(TkCollection.class);
        example.createCriteria().andEqualTo("uId",uId);
        return collectionMapper.selectByExample(example);
    }
}
