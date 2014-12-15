package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Alarm
{
    Clip clip = null;
    Time callTime = null;
    String path = null;

    public Alarm( Time callTime, String path )
    {
        this.callTime = callTime;
        this.path = path;
    }

    public void play()
    {
        if(clip != null) return;

        try
        {
            AudioInputStream ais;

            System.out.println( path );
            if(path == null || path.equals( "" ))
                ais = AudioSystem.getAudioInputStream( Main.class.getResource( "/resources/tmp.wav" ) );
            else
            {
                System.out.println( "****************************" );
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

    public void stop()
    {
        if(clip.isActive())
        {
            clip.stop();
            clip = null;
        }
    }

    public String getPath()
    {
        return path;
    }

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

