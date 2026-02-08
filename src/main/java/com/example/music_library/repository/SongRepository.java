package com.example.music_library.repository;

import com.example.music_library.model.Song;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {
    private final JdbcTemplate jdbc;

    public SongRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    private final RowMapper<Song> songMapper = ((rs, rowNum) -> new Song(rs.getInt("id"),
            rs.getString("title"),
            rs.getObject("duration") == null ? null : rs.getInt("duration"),
            rs.getObject("artist_id") == null ? null : rs.getInt("artist_id")));

    public List<Song> findAll(){
        return jdbc.query("SELECT id, title, duration, artist_id FROM songs", songMapper);
    }

    public Song findById(int id){
        return jdbc.queryForObject("SELECT id, title, duration, artist_id FROM songs WHERE id = ?",
                new Object[]{id}, songMapper);
    }

    public int save(Song s){
        return jdbc.update("INSERT INTO songs(title, duration, artist_id) VALUES (?, ?, ?)", s.getTitle(),
                s.getDuration(), s.getArtistId());
    }

    public int update(Song s){
        return jdbc.update("UPDATE songs SET title = ?, duration = ?, artist_id = ? WHERE id = ?",
                s.getTitle(), s.getDuration(), s.getArtistId(), s.getId());
    }

    public int delete(int id){
        return jdbc.update("DELETE FROM songs WHERE id = ?", id);
    }

    public List<Song> findByPlaylistId(int playlistId){
        String sql = "SELECT s.id, s.title, s.duration, s.artist_id " +
                "FROM songs s JOIN playlist_songs ps ON s.id = ps.song_id " +
                "WHERE ps.playlist_id = ?";
        return jdbc.query(sql, new Object[]{playlistId}, songMapper);
    }

    public int addSongToPlaylist(int playlistId, int songId){
        return jdbc.update("INSERT INTO playlists_song(playlist_id, song_id) VALUES (?, ?)", playlistId, songId);
    }

    public int removeSongFromPlaylist(int playlistId, int songId){
        return jdbc.update("DELETE FROM playlists_songs WHERE playlist_id = ? AND song_id = ?", playlistId, songId);
    }
}
