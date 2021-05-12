package com.example.demo.service.likePlaylist;

import com.example.demo.model.LikePlaylist;


public interface ILikePlaylistService {
    LikePlaylist save (LikePlaylist likePlaylist);
    void delete(Long id);

    LikePlaylist findById(Long id);

    int getTotalLikeOfPlaylist(Long pId);
}
