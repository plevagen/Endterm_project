package com.example.music_library.repository;

import com.example.music_library.model.Artist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ArtistRepository {
    private final JdbcTemplate jdbc;

    public ArtistRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    private final RowMapper<Artist> artistMapper = ((rs, rowNum) -> new Artist(rs.getInt("id"),
            rs.getString("name")));
    public List<Artist> findAll(){
        return jdbc.query("SELECT id, name FROM artists", artistMapper);
    }

    public Artist findById(int id){
        return jdbc.queryForObject("SELECT id, name FROM artists WHERE id = ?", new Object[]{id}, artistMapper);
    }

    public int save(Artist artist) {
        return jdbc.update("INSERT INTO artists (name) VALUES (?)", artist.getName());
    }
    public int update(Artist artist){
        return jdbc.update("UPDATE artists SET name = ?, WHERE id = ?", artist.getName(), artist.getId());
    }
    public int delete(int id){
        return jdbc.update("DELETE FROM artists WHERE id = ?", id);
    }
}
