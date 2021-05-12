package com.example.demo.repository.comment;

import com.example.demo.model.comment.CommentOfPlayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentPlayListRepository extends CrudRepository<CommentOfPlayList, Long> {
    @Query(value = "select * from comment_of_play_list where play_list_id = ?;", nativeQuery = true)
    List<CommentOfPlayList> getAllByPlayListId(Long id);
}
