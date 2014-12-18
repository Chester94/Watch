package com.company;

/**
 * @author Frolov Daniil IVT-43BO.
 * Stores time.
 */
public class Time
{
    private int hour;
    private int minute;
    private int second;
    private int millisecond;

    public Time( int hour, int minute, int second, int millisecond )
    {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getSecond()
    {
        return second;
    }

    public int getMillisecond()
    {
        return millisecond;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if( !(obj instanceof Time) )
            return false;

        Time tmp = (Time)obj;

        return hour == tmp.hour && minute == tmp.minute && second == tmp.second;
    }

    @Override
    public String toString()
    {
        return hour + " : " + minute + " : " + second;
    }
}