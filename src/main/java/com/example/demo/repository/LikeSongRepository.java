package com.example.demo.repository;

import com.example.demo.model.LikeSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface LikeSongRepository extends JpaRepository<LikeSong,Long> {
    LikeSong findLikeSongById(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from like_song where song_id = ?1 ; ", nativeQuery = true)
    void deleteLikeSong (Long sId);

    @Query(value = "select count(app_user_id) as 'total_like' from like_song where song_id= ?1 group by song_id ;",nativeQuery = true)
    int showTotalLikeOfSong(Long sId);



}
