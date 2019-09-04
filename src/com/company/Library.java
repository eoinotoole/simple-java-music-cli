package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Song> songs = new ArrayList<>();

    public Library(String libraryFolderPath) throws IOException {
        try {
            Files.list(new File(libraryFolderPath).toPath()).forEach(path -> {
                songs.add(new Song(path.getFileName().toString(), path.toString()));
            });
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void displaySongs() {
        songs.forEach(song -> {
            System.out.println(song.getTitle());
        });
    }

    public void chooseSong(String title) {
        for(int i = 0; i < this.songs.size(); i++) {
            if(this.songs.get(i).getTitle().equals(title)) {
                Main.player.setCurrentSong(songs.get(i));
                return;
            }
        }

        throw new IllegalArgumentException("That song does not exist in your library");
    }

    public void prevSong() {
        int songIndex = this.songs.indexOf(Main.player.getCurrentSong());
        if (songIndex > 0) {
            Main.player.setCurrentSong(this.songs.get(songIndex - 1));
        } else {
            Main.player.setCurrentSong(this.songs.get(songs.size() - 1));
        }
        try {
            Main.player.getCurrentSong().play();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void nextSong() {
        int songIndex = this.songs.indexOf(Main.player.getCurrentSong());
        if (songIndex < songs.size() - 1) {
            Main.player.setCurrentSong(this.songs.get(songIndex + 1));
        } else {
            Main.player.setCurrentSong(this.songs.get(0));
        }
        try {
            Main.player.getCurrentSong().play();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
