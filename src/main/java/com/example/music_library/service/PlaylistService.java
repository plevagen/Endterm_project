package com.example.music_library.service;


import com.example.music_library.model.Playlist;
import com.example.music_library.model.Song;
import com.example.music_library.repository.PlaylistRepository;
import com.example.music_library.exception.ResourceNotFoundException;
import com.example.music_library.repository.SongRepository;
import com.example.music_library.service.SongService; // for loading songs
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository repo;
    private final SongService songService;

    public PlaylistService(PlaylistRepository repo, SongService songService){
        this.repo = repo;
        this.songService = songService;
    }

    public List<Playlist> getAll(){
        return repo.findAll();
    }

    public Playlist getById(int id){
        try{
            Playlist p = repo.findById(id);
            List<Song> songs = songService.getSongsForPlaylist(id);
            p.setSongs(songs);
            return p;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Playlist not found by id" + id);
        }
    }

    public void create(Playlist p){
        repo.save(p);
    }

    public void update(Playlist p, int id){
        try{
            Playlist existing = repo.findById(id);
            p.setId(id);
            repo.update(p);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Playlist not found by id" + id);
        }
    }

    public void delete(int id){
        repo.delete(id);
    }
}
