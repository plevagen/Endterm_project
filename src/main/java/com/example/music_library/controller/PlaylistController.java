package com.example.music_library.controller;

import com.example.music_library.model.Playlist;
import com.example.music_library.repository.PlaylistRepository;
import com.example.music_library.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService service;

    public PlaylistController(PlaylistService service){
        this.service = service;
    }

    @GetMapping
    public List<Playlist> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Playlist getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Playlist playlist){
        service.create(playlist);
        return ResponseEntity.ok("Playlist created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Playlist playlist){
        service.update(playlist, id);
        return ResponseEntity.ok("Playlist updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.ok("Playlist deleted");
    }
}
