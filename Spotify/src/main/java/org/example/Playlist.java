package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PlayList {
    private String name;
    private List<Song> songList;
    private Map<Integer, Song> songMap;

    public PlayList(String name) {
        this.name = name;
        this.songList = new ArrayList<>();
        this.songMap = new HashMap<>();
    }

    public void addSong(Song song){
        songList.add(song);
        songMap.put(song.getId(),song);
    }
    public Song getSong(int id){
        return songMap.get(id);
    }
    public boolean removeSong(int id){
        Song song=songMap.remove(id);
        if(song==null){
            return false;
        }
        songList.remove(song);
        return true;
    }
    public List<Song> getSongList() {
        return new ArrayList<>(songList);
    }
}