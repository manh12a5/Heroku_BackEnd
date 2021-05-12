package com.example.demo.service.commentPlayList;

import com.example.demo.model.comment.CommentOfPlayList;
import com.example.demo.service.IService;

import java.util.List;

public interface ICommentPlayListService extends IService<CommentOfPlayList> {
    List<CommentOfPlayList> getAllByPlayListId (Long id);
}
