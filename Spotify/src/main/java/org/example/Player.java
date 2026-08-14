package org.example;

import java.util.*;

enum State{
    PAUSE,
    PLAY
}
public class Player{
    Song currentSong;
    State state;
    Deque<Song> songs;
    Deque<Song> history;
    boolean loopMode;
    boolean shuffle;
    PlayList playList;

    public Player() {
        this.currentSong = currentSong;
        this.history = new ArrayDeque<Song>();
        this.loopMode = false;
        this.shuffle = false;
        this.songs = new ArrayDeque<Song>();
        this.state = State.PAUSE;
    }
    public Song play(){
        if(currentSong!=null && state==State.PAUSE){
            state=State.PLAY;
            return currentSong;
        }
        if(songs.isEmpty()){
            return null;
        }
        currentSong=songs.pollFirst();
        state=State.PLAY;
        return currentSong;
    }
    public boolean pause(){

            if (currentSong == null || state != State.PLAY) {
                return false;
            }

            state = State.PAUSE;
            return true;

    }
    public Song skip(){
        if (currentSong == null) {
            return null;
        }
        if (songs.isEmpty()) {

            if (loopMode && playList != null) {
                for (Song song : playList.getSongList()) {
                    songs.offerLast(song);
                }
            } else {
                return null;
            }
        }
        history.offerLast(currentSong);
        currentSong=songs.pollFirst();
        state= State.PLAY;
        return currentSong;
    }
    public Song prev(){
        if(history.isEmpty()){
            return null;
        }
        if(currentSong!=null){
            songs.offerFirst(currentSong);
        }

        currentSong=history.pollLast();
        state=State.PLAY;
        return currentSong;

    }
    public boolean addToQueue(Song song){
        return songs.offerLast(song);
    }
    public Song playPlaylist(PlayList playlist) {
        this.playList=playlist;
        songs.clear();
        history.clear();

        for (Song song : playlist.getSongList()) {
            songs.offerLast(song);
        }

        return play();
    }

    public void setLoop(boolean loopMode){
        this.loopMode=loopMode;

    }
    public void setShuffle(boolean shuffle){
        this.shuffle=shuffle;
        if(shuffle){
            List<Song> temp =new ArrayList<>(songs);
            Collections.shuffle(temp);
            songs.clear();
            songs.addAll(temp);

        }

    }

}
