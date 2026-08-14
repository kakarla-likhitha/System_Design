package org.example;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Song {
    private int id;
    private String name;
    private String artist;

    public Song(String album, String artist, int id, String name) {
        this.album = album;
        this.artist = artist;
        this.id = id;
        this.name = name;
    }

    private String album;
}


class MusicLibrary {
    private Map<Integer,Song> songs= new HashMap<>();
    public void addSong(Song song){
        songs.put(song.getId(),song);
    }
    public Song getSong(int id){
        return songs.get(id);
    }
    public void removeSong(int id){
        songs.remove(id);
    }
}

