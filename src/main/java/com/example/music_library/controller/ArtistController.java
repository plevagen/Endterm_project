package com.example.music_library.controller;

import com.example.music_library.model.Artist;
import com.example.music_library.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService service;

    public ArtistController(ArtistService service){
        this.service = service;
    }

    @GetMapping
    public List<Artist> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Artist getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Artist artist){
        service.create(artist);
        return ResponseEntity.ok("Artist created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Artist artist){
        service.update(id, artist);
        return ResponseEntity.ok("Artist updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.ok("Artist deleted");
    }
}
