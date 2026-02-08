package com.example.music_library.model;
import java.util.List;

public class Playlist {
    private Integer id;
    private String name;
    private String description;
    private List<Song> songs;

    public Playlist(){}

    public Playlist(Integer id, String name, String description, List<Song> songs){
        this.id = id;
        this.name = name;
        this.description = description;
        this.songs = songs;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Song> getSongs() { return songs; }
    public void setSongs(List<Song> songs) { this.songs = songs; }
}
