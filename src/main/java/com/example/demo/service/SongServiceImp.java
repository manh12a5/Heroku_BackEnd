package com.example.demo.service;

import com.example.demo.model.Song;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongServiceImp implements ISongService {

    @Autowired
    private SongRepository songRepository;
    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).get();
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public List<Song> findAllByCreationTimeOrderByCreationTime() {
        return songRepository.findAllByCreatedTimeOrderByCreatedTime();
    }

    @Override
    public List<Song> findAllByNumberOfViewOrderByNumberOfView() {
        return songRepository.findAllByNumberOfViewOrderByNumberOfView();
    }

    @Override
    public List<Song> findAllByNameSong(String nameSong) {
        return songRepository.findAllByNameSong(nameSong);
    }

    @Override
    public List<Song> getAllByCreateById(Long id) {
        return songRepository.getAllByCreateById(id);
    }
}
