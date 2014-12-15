package com.company;

import java.util.ArrayList;

public class AlarmArray
{
    private ArrayList<Alarm> alarms = new ArrayList<>();

    public void add(Alarm newAlarm)
    {
        alarms.add( newAlarm );
    }

    public void change(int index, Alarm newAlarm)
    {
        alarms.remove( index );
        alarms.add( index, newAlarm );
    }

    public void delete(int index)
    {
        alarms.get( index ).stop();
        alarms.remove( index );
    }

    public Alarm getAlarm(int index)
    {
        if(index > alarms.size() ) return null;
        return alarms.get( index );
    }

    public void playAppropriateAlarm(Time curTime)
    {
        for( Alarm i : alarms )
            if(i.callTime().equals( curTime ))
                i.play();
    }

    public void stopAllAlarms()
    {
        for( Alarm i : alarms )
            i.stop();
    }

    public String[] getAllAlarmTime()
    {
        String[] buf = new String[alarms.size()];

        for( int i = 0; i < alarms.size(); i++ )
            buf[i] = alarms.get( i ).callTime().toString() + " #" + i;

        return buf;
    }

    public int length()
    {
        return alarms.size();
    }
}
