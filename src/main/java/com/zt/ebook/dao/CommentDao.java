package com.zt.ebook.dao;

import com.zt.ebook.mapper.CommentMapper;
import com.zt.ebook.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class CommentDao {
    @Autowired
    private CommentMapper commentMapper;
    //添加评论
    public Integer addComment(Comment comment){
        return commentMapper.insert(comment);
    }
    //删除评论
    public Integer deleteCommentById(Integer id){
        return commentMapper.deleteByPrimaryKey(id);
    }
    //修改评论
    public Integer updateComment(Comment comment){
        return  commentMapper.updateByPrimaryKey(comment);
    }
    //查看品论
    public List<Comment> findComment(Integer bookId){
        Example example=new Example(Comment.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return  commentMapper.selectByExample(example);
    }
    public Comment findOneComment(Integer id){
        Example example=new Example(Comment.class);
        example.createCriteria().andEqualTo("id",id);
        return commentMapper.selectOneByExample(example);
    }

    public List<Comment> findCommentByIds(Integer commentId,Integer bookId,Integer userId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("id",commentId).andEqualTo("bookId",bookId).andEqualTo("userId",userId);
        return commentMapper.selectByExample(example);
    }
}
