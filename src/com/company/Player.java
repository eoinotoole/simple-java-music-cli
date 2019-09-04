package com.company;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Status {STOPPED, PLAYING};

public class Player {
    private Boolean exitApp = false;
    private Status status = Status.STOPPED;
    private Library library;
    private Controller controller = new Controller();
    private Song currentSong;

    public void run() {
        setLibrary();
        System.out.print("==============================\nSUPER SIMPLE JAVA MUSIC PLAYER\n==============================\n");
        while (!exitApp) {
            Scanner input = new Scanner(System.in);
            System.out.print("CONTROLS (play, stop, list, select, next, prev, exit) => ");
            try {
                controller.carryOutAction(input.next());
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Library getLibrary() {
        return library;
    }

    private void setLibrary() {
        try {
            library = new Library(Main.MUSIC_LIB_PATH);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    public Song getCurrentSong() {
        return this.currentSong;
    }

    public void setCurrentSong(Song song) {
        this.currentSong = song;
    }

    public void displayCurrentSong() {
        System.out.println("Current song: "+this.getCurrentSong());
    }
}
