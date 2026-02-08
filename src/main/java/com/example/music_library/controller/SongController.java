package com.example.music_library.controller;

import com.example.music_library.model.Song;
import com.example.music_library.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private final SongService service;

    public SongController(SongService service) { this.service = service; }

    @GetMapping
    public List<Song> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Song getById(@PathVariable int id) { return service.getById(id); }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Song song) {
        service.create(song);
        return ResponseEntity.ok("Song created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Song song) {
        service.update(song, id);
        return ResponseEntity.ok("Song updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Song deleted");
    }

    @PostMapping("/{songId}/playlists/{playlistId}")
    public ResponseEntity<String> addToPlaylist(@PathVariable int songId, @PathVariable int playlistId) {
        service.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.ok("Song added to playlist");
    }

    @DeleteMapping("/{songId}/playlists/{playlistId}")
    public ResponseEntity<String> removeFromPlaylist(@PathVariable int songId, @PathVariable int playlistId) {
        service.removeSongFromPlaylist(playlistId, songId);
        return ResponseEntity.ok("Song removed from playlist");
    }
}
