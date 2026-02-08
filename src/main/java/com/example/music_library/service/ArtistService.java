package com.example.music_library.service;

import com.example.music_library.model.Artist;
import com.example.music_library.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import com.example.music_library.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository repo;

    public ArtistService(ArtistRepository repo){
        this.repo = repo;
    }

    public List<Artist> getAll(){
        return repo.findAll();
    }

    public Artist getById(int id){
        try{
            return repo.findById(id);
        } catch(Exception e){
            throw new ResourceNotFoundException("Artist not found by id" + id);
        }
    }

    public void create(Artist artist){
        repo.save(artist);
    }

    public void update(int id, Artist artist){
        try{
            Artist existing = repo.findById(id);
            artist.setId(id);
            repo.update(artist);
        } catch(Exception e){
            throw new ResourceNotFoundException("Artist not found by id" + id);
        }
    }

    public void delete(int id){
        repo.delete(id);
    }
}
