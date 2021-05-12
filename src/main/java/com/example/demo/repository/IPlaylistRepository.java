package com.example.demo.repository;

import com.example.demo.model.PlayList;
import com.example.demo.model.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlaylistRepository extends PagingAndSortingRepository<PlayList, Long> {

    List<PlayList> findAllByAppUserUsername(String username);

    @Query(value = "select * from play_list where play_list.name like ?", nativeQuery = true)
    List<PlayList> findAllByName(String name);

    //Top 10 playlist moi nhat
    @Query(value = "select * from play_list order by time_create desc limit 10", nativeQuery = true)
    List<PlayList> findAllByCreatedTimeOrderByCreatedTime();
}

