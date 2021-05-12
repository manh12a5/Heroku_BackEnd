package com.example.demo.service.commentSong;
import com.example.demo.model.comment.CommentOfSong;
import com.example.demo.service.IService;

import java.util.List;

public interface ICommentSongService extends IService<CommentOfSong> {
    List<CommentOfSong> getAllBySongId (Long id);
}
