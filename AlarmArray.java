package com.company;

import java.util.ArrayList;

/**
 * @author Frolov Daniil IVT-43BO.
 * Manages an array of alarms.
 */
public class AlarmArray
{
    /**
     * Array of alarms.
     */
    private ArrayList<Alarm> alarms = new ArrayList<>();

    /**
     * Add new alarms.
     * @param newAlarm added alarm
     */
    public void add(Alarm newAlarm)
    {
        alarms.add( newAlarm );
    }

    /**
     * Alarm changes with the specified number.
     * @param index index of alarm for change
     * @param newAlarm new data for change
     */
    public void change(int index, Alarm newAlarm)
    {
        alarms.get( index ).stop();
        alarms.set(index, newAlarm);
    }

    /**
     * Alarm delete with the specified number.
     * @param index index of alarm for delete
     */
    public void delete(int index)
    {
        alarms.get( index ).stop();
        alarms.remove( index );
    }

    /**
     * Get alarm by index.
     * @param index alarm index for return
     * @return alarm
     */
    public Alarm getAlarm(int index)
    {
        if(index > alarms.size() ) return null;
        return alarms.get( index );
    }

    /**
     * Run all alarms installed at this time.
     * @param curTime current system time
     */
    public void playAppropriateAlarm(Time curTime)
    {
        for( Alarm i : alarms )
            if(i.callTime().equals( curTime ))
                i.play();
    }

    /**
     * Stop all alarms.
     */
    public void stopAllAlarms()
    {
        for( Alarm i : alarms )
            i.stop();
    }

    /**
     * Getting all the alarms in the form of an array of strings.
     * @return Array of string
     */
    public String[] getAllAlarmTime()
    {
        String[] buf = new String[alarms.size()];

        for( int i = 0; i < alarms.size(); i++ )
            buf[i] = alarms.get( i ).callTime().toString() + " #" + i;

        return buf;
    }

    /**
     * Get array of alarms size.
     * @return size
     */
    public int length()
    {
        return alarms.size();
    }
}
