package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Song {
    private String title;
    private String filePath;
    private Clip audioClip;


    public Song(String fileName, String filePath) {
        this.title = fileName;
        this.filePath = filePath;
    }

    public String getTitle() {
        return this.title;
    }

    public void play() throws LineUnavailableException, IOException {
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
            System.out.println(audioClip.getMicrosecondLength() / 1000000);
            Main.player.setStatus(Status.PLAYING);

        } catch (UnsupportedAudioFileException ex) {
            throw new UnsupportedOperationException("The specified audio file is not supported.");
        } catch (LineUnavailableException ex) {
            throw new LineUnavailableException("Audio line for playing back is unavailable.");
        } catch (IOException ex) {
            throw new IOException("Error playing the audio file.");
        }

    }

    public void stop() {
        if (Main.player.getStatus() == Status.PLAYING) {
            audioClip.stop();
            Main.player.setStatus(Status.STOPPED);
        }
    }

    public Clip getAudioClip() {
        return audioClip;
    }
}
