package com.example.demo.service.playlist;

import com.example.demo.model.PlayList;
import com.example.demo.model.Song;
import com.example.demo.repository.IPlaylistRepository;
import com.example.demo.repository.SongRepository;
import com.example.demo.service.SongServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    IPlaylistRepository playlistRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public List<PlayList> findAll() {
        return (List<PlayList>) playlistRepository.findAll();
    }

    @Override
    public PlayList findById(Long id) {
        return playlistRepository.findById(id).get();
    }

    @Override
    public PlayList save(PlayList playList) {
        return playlistRepository.save(playList);
    }

    @Override
    public void delete(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public List<PlayList> findAllByAppUserUsername(String username) {
        return playlistRepository.findAllByAppUserUsername(username);
    }

    @Override
    public PlayList addSongToPlaylist(Long idSong, Long idPlaylist) {
        Song song = songRepository.findById(idSong).get();
        PlayList playlist = playlistRepository.findById(idPlaylist).get();
        List<Song> songs = playlist.getSongs();
        if (songs.contains(song)) {
            return null;
        }
        songs.add(song);
        playlist.setSongs(songs);
        playlistRepository.save(playlist);
        return playlist;
    }

    @Override
    public List<PlayList> findAllByName(String name) {
        return playlistRepository.findAllByName(name);
    }

    @Override
    public List<PlayList> findAllByCreationTimeOrderByCreationTime() {
        return playlistRepository.findAllByCreatedTimeOrderByCreatedTime();
    }
}
