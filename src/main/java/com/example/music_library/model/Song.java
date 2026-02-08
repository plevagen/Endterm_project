package com.example.music_library.model;

public class Song {
    private Integer id;
    private String title;
    private Integer duration;
    private Integer artist_id;

    public Song(){}

    public Song(Integer id, String title, Integer duration, Integer artist_id){
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.artist_id = artist_id;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Integer getArtistId() { return artist_id; }
    public void setArtistId(Integer artist_id) { this.artist_id = artist_id; }
}
