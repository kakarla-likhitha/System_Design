package org.example;

public class Main {
    public static void main(String[] args) {

        // 1. Create songs
        Song s1 = new Song("Album1", "Artist1", 1, "Song A");
        Song s2 = new Song("Album1", "Artist1", 2, "Song B");
        Song s3 = new Song("Album2", "Artist2", 3, "Song C");
        Song s4 = new Song("Album2", "Artist2", 4, "Song D");

        // 2. Add songs to library
        MusicLibrary library = new MusicLibrary();

        library.addSong(s1);
        library.addSong(s2);
        library.addSong(s3);
        library.addSong(s4);

        // 3. Create playlist
        PlayList playlist = new PlayList("Workout");

        playlist.addSong(s1);
        playlist.addSong(s2);
        playlist.addSong(s3);
        playlist.addSong(s4);

        // 4. Create player
        Player player = new Player();

        // 5. Add songs to queue
        player.addToQueue(s1);
        player.addToQueue(s2);

        // 6. Play
        System.out.println("Playing: " + player.play().getName());

        // 7. Pause
        player.pause();
        System.out.println("Paused");

        // 8. Resume
        System.out.println("Resumed: " + player.play().getName());

        // 9. Skip
        System.out.println("Skipped to: " + player.skip().getName());

        // 10. Previous
        System.out.println("Previous: " + player.prev().getName());

        // 11. Play playlist
        System.out.println(
                "Playlist started: " +
                        player.playPlaylist(playlist).getName()
        );

        // 12. Enable loop
        player.setLoop(true);

        // 13. Enable shuffle
        player.setShuffle(true);

        System.out.println("Shuffle enabled");
    }
}