package com.example.demo.repository.comment;

import com.example.demo.model.comment.CommentOfSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentSongRepository extends JpaRepository<CommentOfSong,Long> {
    @Query(value = "select * from comment_of_song where song_id = ?", nativeQuery = true)
    List<CommentOfSong> findAllBySongId(Long id);
}
