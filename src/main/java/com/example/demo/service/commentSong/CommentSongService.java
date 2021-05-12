package com.example.demo.service.commentSong;

import com.example.demo.model.comment.CommentOfSong;
import com.example.demo.repository.comment.CommentSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSongService implements ICommentSongService{
    @Autowired
    CommentSongRepository commentSongRepository;

    @Override
    public List<CommentOfSong> findAll() {
        return commentSongRepository.findAll();
    }

    @Override
    public CommentOfSong findById(Long id) {
        return commentSongRepository.findById(id).get();
    }

    @Override
    public CommentOfSong save(CommentOfSong commentOfSong) {
        return commentSongRepository.save(commentOfSong);
    }

    @Override
    public void delete(Long id) {
        commentSongRepository.deleteById(id);
    }

    @Override
    public List<CommentOfSong> getAllBySongId(Long id) {
        return commentSongRepository.findAllBySongId(id);
    }
}
