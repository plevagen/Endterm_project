package com.example.music_library.repository;

import com.example.music_library.model.Playlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaylistRepository {
    private JdbcTemplate jdbc;

    public PlaylistRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    private final RowMapper<Playlist> playlistMapper = ((rs, rowNum) ->
            new Playlist(rs.getInt("id"), rs.getString("name"),
                    rs.getString("description"), null));

    public List<Playlist> findAll(){
        return jdbc.query("SELECT id, name FROM playlists", playlistMapper);
    }

    public Playlist findById(int id) {
        return jdbc.queryForObject("SELECT id, name FROM playlists WHERE id = ?",
                new Object[]{id}, playlistMapper);
    }

    public int save(Playlist p) {
        return jdbc.update("INSERT INTO playlists (name, description) VALUES (?, ?)",
                p.getName(), p.getDescription());
    }

    public int update(Playlist p) {
        return jdbc.update("UPDATE playlists SET name = ?, description = ? WHERE id = ?",
                p.getName(), p.getDescription(), p.getId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM playlists WHERE id = ?", id);
    }
}
