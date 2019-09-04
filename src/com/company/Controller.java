package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

enum Action {PLAY, STOP, LIST, SELECT, PREV, NEXT, EXIT};

public class Controller {

    public void carryOutAction(String action) {
        Action matchedEnum;
        try {
            matchedEnum = Action.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Control argument is invalid, please try again");
        }
        switch (matchedEnum) {
            case PLAY:
                if (Main.player.getCurrentSong() == null) {
                    throw new IllegalArgumentException("No song has been chosen to play");
                }
                if (Main.player.getStatus() == Status.PLAYING) {
                    throw new IllegalArgumentException("Player is already playing");
                }
                try {
                    Main.player.getCurrentSong().play();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                return;
            case STOP:
                if(Main.player.getStatus() == Status.PLAYING) {
                    Main.player.getCurrentSong().stop();
                }
                return;
            case LIST:
                Main.player.getLibrary().displaySongs();
                Main.player.displayCurrentSong();
                return;
            case SELECT:
                Scanner input = new Scanner(System.in);
                Main.player.getLibrary().displaySongs();
                Main.player.displayCurrentSong();
                System.out.print("Choose song from list: ");
                String selection = input.next();
                try {
                    Main.player.getLibrary().chooseSong(selection);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
                System.out.println("Song selected:" + Main.player.getCurrentSong().getTitle());
                return;

            case PREV:
                if(Main.player.getCurrentSong() != null) {
                    Main.player.getCurrentSong().stop();
                    Main.player.getLibrary().prevSong();
                }
                return;
            case NEXT:
                if(Main.player.getCurrentSong() != null) {
                    Main.player.getCurrentSong().stop();
                    Main.player.getLibrary().nextSong();
                }
                return;
            case EXIT:
                if(Main.player.getStatus() == Status.PLAYING) {
                    Main.player.getCurrentSong().stop();
                    Main.player.getCurrentSong().getAudioClip().close();
                }
                System.exit(1);
            default:
                throw new IllegalArgumentException("Control argument is invalid, please try again");
        }
    }



}
