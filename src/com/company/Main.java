package com.company;

import javax.sound.sampled.*;
import java.io.File;

public class Main {
    static final String MUSIC_LIB_PATH = "C:\\Users\\eoinj\\IdeaProjects\\MusicPlayer\\music";
    static Player player = new Player();

    public static void main(String[] args) {
        player.run();
    }
}
