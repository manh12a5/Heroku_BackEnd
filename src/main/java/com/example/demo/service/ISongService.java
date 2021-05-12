package com.example.demo.service;

import com.example.demo.model.Song;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISongService {
    List<Song> getAll();
    Song save(Song song);
    Song findById(Long id);
    void deleteSong(Long id);

    //Nghe nhiều
    List<Song> findAllByCreationTimeOrderByCreationTime();
    //Top view
    List<Song> findAllByNumberOfViewOrderByNumberOfView();
    //Tìm kiếm theo tên bài hát
    List<Song> findAllByNameSong(String nameSong);

    List<Song> getAllByCreateById(Long id);
}
