package com.example.music_library.service;


import com.example.music_library.model.Song;
import com.example.music_library.repository.SongRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.music_library.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class SongService {
    private final SongRepository repo;

    public SongService(SongRepository repo){
        this.repo = repo;
    }

    public List<Song> getAll(){
        return repo.findAll();
    }

    public Song getById(int id){
        try{
            return repo.findById(id);
        } catch (Exception e){
            throw new ResourceNotFoundException("Song not found by id" + id);
        }
    }

    public void create(Song song){
        repo.save(song);
    }

    public void update(Song song, int id){
        try{
            Song exist = repo.findById(id);
            song.setId(id);
            repo.update(song);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Song not found by id" + id);
        }
    }

    public void delete(int id){
        repo.delete(id);
    }

    public List<Song> getSongsForPlaylist(int playlistId) {
        return repo.findByPlaylistId(playlistId);
    }

    public void addSongToPlaylist(int playlistId, int songId) {
        repo.addSongToPlaylist(playlistId, songId);
    }

    public void removeSongFromPlaylist(int playlistId, int songId) {
        repo.removeSongFromPlaylist(playlistId, songId);
    }
}
