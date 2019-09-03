package com.company;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.Scanner;

enum Status {STOPPED, PLAYING};

public class Player {
    private Boolean exitApp = false;
    private Status status = Status.STOPPED;
    private String currentSong;

    public void run() {
        Controller controller = new Controller();
        System.out.print("==============================\nSUPER SIMPLE JAVA MUSIC PLAYER\n==============================\n");
        while (!exitApp) {
            Scanner input =  new Scanner(System.in);
            System.out.print("CONTROLS (play, stop, next, prev, exit) => ");
            try {
                controller.carryOutAction(input.next());
            } catch(IllegalArgumentException exc) {
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

    public String getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }
}
