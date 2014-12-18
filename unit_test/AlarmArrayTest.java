
import com.company.Alarm;
import com.company.AlarmArray;
import com.company.Time;
import org.junit.Test;

public class AlarmArrayTest
{
    AlarmArray t = new AlarmArray();
    Time time = new Time(0, 0, 0, 0);

    @Test
    public void stopAllAlarm_NoAlarmsInArray()
    {
        t.stopAllAlarms();
    }

    @Test
    public void addNewAlarmStartAlarmStopAllAlarms()
    {
        t.add( new Alarm( time, "" ) );
        t.playAppropriateAlarm( time );
        t.stopAllAlarms();
    }

    @Test
    public void addNewAlarmStartAlarmChangeAlarmStopAllAlarm()
    {
        t.add( new Alarm( time, "" ) );
        t.playAppropriateAlarm( time );
        t.change( 0, new Alarm( time, "" ) );
        t.playAppropriateAlarm( time );
        t.stopAllAlarms();
    }

    @Test
    public void addNewAlarmStartAlarmDeleteAlarm()
    {
        t.add( new Alarm( time, "" ) );
        t.playAppropriateAlarm( time );
        t.delete( 0 );
    }

    @Test
    public void addNewAlarmChangeAlarmStopAllAlarm()
    {
        t.add( new Alarm( time, "" ) );
        t.change( 0, new Alarm( time, "" ) );
    }

    @Test
    public void addNewAlarmDeleteAlarm()
    {
        t.add( new Alarm( time, "" ) );
        t.delete( 0 );
    }
}
