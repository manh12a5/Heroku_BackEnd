package com.example.demo.repository;

import com.example.demo.model.LikePlaylist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface LikePlaylistRepository extends JpaRepository<LikePlaylist,Long> {
    LikePlaylist findLikePlaylistById(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from like_playlist where play_list_id = ?1 ; ", nativeQuery = true)
    void deleteLikePlaylist (Long pId);

    @Query(value = "select count(user_id) as 'total_like' from like_playlist where play_list_id= ?1 group by play_list_id ;", nativeQuery = true)
    int showTotalLikeOfPlaylist(Long pId);
}
