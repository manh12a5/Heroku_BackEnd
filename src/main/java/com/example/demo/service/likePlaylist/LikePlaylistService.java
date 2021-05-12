package com.example.demo.service.likePlaylist;

import com.example.demo.model.LikePlaylist;
import com.example.demo.repository.LikePlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikePlaylistService implements ILikePlaylistService{
    @Autowired
    private LikePlaylistRepository likePlaylistRepository;

    @Override
    public LikePlaylist save(LikePlaylist likePlaylist) {
        return likePlaylistRepository.save(likePlaylist);
    }

    @Override
    public void delete(Long id) {
        likePlaylistRepository.deleteById(id);
    }

    @Override
    public LikePlaylist findById(Long id) {
        return likePlaylistRepository.findLikePlaylistById(id);
    }

    @Override
    public int getTotalLikeOfPlaylist(Long pId) {
        return likePlaylistRepository.showTotalLikeOfPlaylist(pId);
    }

    public void deleteLikePlaylist(Long pId){
        likePlaylistRepository.deleteLikePlaylist(pId);
    }
}
