package com.example.demo.service.likeSong;

import com.example.demo.model.LikeSong;
import com.example.demo.repository.LikeSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeSongService implements ILikeSongService {
    @Autowired
    private LikeSongRepository likeSongRepository;
    @Override
    public LikeSong save(LikeSong likeSong) {
        return likeSongRepository.save(likeSong);
    }

    @Override
    public void delete(Long id) {
        likeSongRepository.deleteById(id);

    }

    @Override
    public LikeSong findById(Long id) {
        return likeSongRepository.findLikeSongById(id);
    }

    @Override
    public int getTotalLikeOfSong(Long sId) {
        return likeSongRepository.showTotalLikeOfSong(sId);
    }

    //
    public void deleteLikeSong(Long sId){
        likeSongRepository.deleteLikeSong(sId);
    }

}
