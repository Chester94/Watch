package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Frolov Daniil IVT-43BO.
 * Stores the data necessary for the alarm.
 */
public class Alarm
{
    /**
     * Music value
     */
    Clip clip = null;
    /**
     * Alarm time
     */
    Time callTime = null;
    /**
     * Path to music file
     */
    String path = null;

    public Alarm( Time callTime, String path )
    {
        this.callTime = callTime;
        this.path = path;
    }

    /**
     * Start music play.
     */
    public void play()
    {
        if(clip != null) return;

        try
        {
            AudioInputStream ais;

            if(path == null || path.isEmpty() || path.equals( "[default music]" ))
                ais = AudioSystem.getAudioInputStream( Main.class.getResource( "/resources/tmp.wav" ) );
            else
            {
                File soundFile = new File(path);
                ais = AudioSystem.getAudioInputStream(soundFile);
            }

            clip = AudioSystem.getClip();

            clip.open( ais );

            clip.setFramePosition(0);
            clip.start();
        }
        catch(IOException | UnsupportedAudioFileException | LineUnavailableException exc)
        {
            exc.printStackTrace();
        }
    }

    /**
     * Stop music play.
     */
    public void stop()
    {
        if(clip != null && clip.isActive())
        {
            clip.stop();
            clip = null;
        }
    }

    /**
     * Getter for path to music file.
     * @return path to music path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Getter for call tile.
     * @return call time
     */
    public Time callTime()
    {
        return callTime;
    }

    @Override
    public String toString()
    {
        return callTime.toString() + " " + path;
    }
}

