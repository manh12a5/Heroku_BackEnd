package com.example.demo.service.commentPlayList;

import com.example.demo.model.comment.CommentOfPlayList;
import com.example.demo.repository.comment.CommentPlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentPlayListService implements ICommentPlayListService{
    @Autowired
    private CommentPlayListRepository commentPlayListRepository;


    @Override
    public List<CommentOfPlayList> getAllByPlayListId(Long id) {
        return commentPlayListRepository.getAllByPlayListId(id);
    }

    @Override
    public List<CommentOfPlayList> findAll() {
        return (List<CommentOfPlayList>) commentPlayListRepository.findAll();
    }

    @Override
    public CommentOfPlayList findById(Long id) {
        return commentPlayListRepository.findById(id).get();
    }

    @Override
    public CommentOfPlayList save(CommentOfPlayList commentOfPlayList) {
        return commentPlayListRepository.save(commentOfPlayList);
    }

    @Override
    public void delete(Long id) {
        commentPlayListRepository.deleteById(id);
    }

}
