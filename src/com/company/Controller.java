package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

enum Action { PLAY, STOP, PREV, NEXT, EXIT };

public class Controller {
    private Clip audioClip;


    public void carryOutAction(String action)  {
        Action matchedEnum;
        try {
            matchedEnum = Action.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Control argument is invalid, please try again");
        }
        switch (matchedEnum) {
            case PLAY:
                String filePath = "C:\\Users\\eoinj\\IdeaProjects\\MusicPlayer\\music\\cantina.wav";
                play(filePath);
                return;
            case STOP:
                stop();
                return;
            case PREV:
                prev();
                return;
            case NEXT:
                next();
                return;
            case EXIT:
                stop();
                audioClip.close();
                System.exit(1);
            default:
                throw new IllegalArgumentException("Control argument is invalid, please try again");
        }
    }

    public void play(String filePath) {
        File fileIn = new File(filePath);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
            AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(fileIn);
            AudioFormat audioFormat = audioFileFormat.getFormat();
            System.out.print("...\n");
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioInputStream);
            audioClip.start();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }

    }

    public void stop() {
        audioClip.stop();
    }

    public void prev() {}

    public void next() {}

}
