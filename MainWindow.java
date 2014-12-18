package com.company;

import com.ozten.font.JFontChooser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.*;
import java.util.Timer;

/**
 * @author Frolov Daniil IVT-43BO.
 * The main window of the application.
 */
public class MainWindow extends JFrame
{
    /**
     * Timer to control the redrawing of the clock.
     */
    private Timer timerWatch = new Timer();
    /**
     * Timer to control the alarm checks.
     */
    private Timer timerAlarm = new Timer();
    /**
     * Array of existing alarms.
     */
    private AlarmArray alarms = new AlarmArray();

    /**
     * Initializes the class.
     * Set windows title = Watch 2.4.
     * Initializes components of window.
     * Sets the behavior when closing = EXIT_ON_CLOSE.
     */
    public MainWindow()
    {
        super( "Watch 2.4" );
        initComponents();
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        watchPanel.setGraphContext();
    }

    /**
     * Produces timer settings control redrawing and alarm clock.
     * Disables the ability to resize the window.
     * Enables the visibility of the window.
     */
    public void showWindow()
    {
        setTimers(25, 1000);

        setResizable( false );
        setVisible( true );
    }

    /**
     * Adjusts timer control redrawing hours and checking the alarm.
     * @param timeoutRedrawWatch redraw watch every N milliseconds
     * @param timeoutCheckAlarms check alarms every N milliseconds
     */
    private void setTimers(int timeoutRedrawWatch, int timeoutCheckAlarms)
    {
        timerWatch.scheduleAtFixedRate( new TimerTask()
        {
            @Override
            public void run()
            {
                watchPanel.redrawWatch( new Time( GregorianCalendar.getInstance().get( Calendar.HOUR_OF_DAY ),
                        GregorianCalendar.getInstance().get( Calendar.MINUTE ),
                        GregorianCalendar.getInstance().get( Calendar.SECOND ),
                        GregorianCalendar.getInstance().get( Calendar.MILLISECOND ) ) );
            }
        }, 0, timeoutRedrawWatch );

        timerAlarm.scheduleAtFixedRate( new TimerTask()
        {
            @Override
            public void run()
            {
                checkAlarmTime( new Time( GregorianCalendar.getInstance().get( Calendar.HOUR_OF_DAY ),
                        GregorianCalendar.getInstance().get( Calendar.MINUTE ),
                        GregorianCalendar.getInstance().get( Calendar.SECOND ), 0 ) );
            }
        }, 0, timeoutCheckAlarms );
    }

    /**
     * Handler menu.
     * Dialog for choose color of hour hand.
     * @param e
     */
    private void hourHandColorActionPerformed(ActionEvent e)
    {
        watchPanel.setHourHandColor(
                JColorChooser.showDialog( this, "Выбор цвета для часовой стрелки",
                        watchPanel.getHourHandColor() ) );
    }

    /**
     * Handler menu.
     * Dialog for choose color of minute hand.
     * @param e
     */
    private void minuteHandColorActionPerformed(ActionEvent e)
    {
        watchPanel.setMinuteHandColor(
                JColorChooser.showDialog( this, "Выбор цвета для минутной стрелки",
                        watchPanel.getMinuteHandColor() ) );
    }

    /**
     * Handler menu.
     * Dialog for choose color of second hand.
     * @param e
     */
    private void secondHandColorActionPerformed(ActionEvent e)
    {
        watchPanel.setSecondHandColor(
                JColorChooser.showDialog( this, "Выбор цвета для секундной стрелки",
                        watchPanel.getSecondHandColor() ) );
    }

    /**
     * Handler menu.
     * Dialog for choose text color.
     * @param e
     */
    private void colorTextMenuActionPerformed(ActionEvent e)
    {
        watchPanel.setTextColor(
                JColorChooser.showDialog( this, "Выбор цвета для текста",
                        watchPanel.getTextColor() ) );
    }

    /**
     * Handler menu.
     * Dialog for choose text font.
     * @param e
     */
    private void fontMenuActionPerformed(ActionEvent e)
    {
        watchPanel.setTextFont(
                JFontChooser.showDialog( this, "Выбор шрифта для цифр", "Предложение для проверки",
                        watchPanel.getTextFont() ) );
    }

    /**
     * Handler menu.
     * Call the dialog creation / modification / deletion of alarms.
     * Use field alarms.
     * @param e
     */
    private void setAlarmClockMenuActionPerformed(ActionEvent e)
    {
        AlarmTimeControlDialog a = new AlarmTimeControlDialog( this );
        a.showDialog( alarms,
                new Time( GregorianCalendar.getInstance().get( Calendar.HOUR_OF_DAY ),
                        GregorianCalendar.getInstance().get( Calendar.MINUTE ),
                        GregorianCalendar.getInstance().get( Calendar.SECOND ), 0 ) );
    }

    /**
     * Run all alarms installed at this time.
     * Function for timer control.
     * @param curTime current system time
     */
    private void checkAlarmTime(Time curTime)
    {
        alarms.playAppropriateAlarm( curTime );
    }

    /**
     * Stop all alarms.
     * Click on the clock.
     * @param e
     */
    private void watchPanelMouseClicked(MouseEvent e)
    {
        alarms.stopAllAlarms();
    }

    private void initComponents()
    {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        mainMenu = new JMenu();
        handColorMenu = new JMenu();
        hourHandColor = new JMenuItem();
        minuteHandColor = new JMenuItem();
        secondHandColor = new JMenuItem();
        digitMenu = new JMenu();
        colorTextMenu = new JMenuItem();
        fontMenu = new JMenuItem();
        alarmClockMenu = new JMenu();
        setAlarmClockMenu = new JMenuItem();
        watchPanel = new WatchPanel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar ========
        {

            //======== mainMenu ========
            {
                mainMenu.setText("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438");

                //======== handColorMenu ========
                {
                    handColorMenu.setText("\u0426\u0432\u0435\u0442 \u0441\u0442\u0440\u0435\u043b\u043e\u043a");

                    //---- hourHandColor ----
                    hourHandColor.setText("\u0427\u0430\u0441\u043e\u0432\u0430\u044f");
                    hourHandColor.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            hourHandColorActionPerformed(e);
                        }
                    });
                    handColorMenu.add(hourHandColor);

                    //---- minuteHandColor ----
                    minuteHandColor.setText("\u041c\u0438\u043d\u0443\u0442\u043d\u0430\u044f");
                    minuteHandColor.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            minuteHandColorActionPerformed(e);
                        }
                    });
                    handColorMenu.add(minuteHandColor);

                    //---- secondHandColor ----
                    secondHandColor.setText("\u0421\u0435\u043a\u0443\u043d\u0434\u043d\u0430\u044f");
                    secondHandColor.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            secondHandColorActionPerformed(e);
                        }
                    });
                    handColorMenu.add(secondHandColor);
                }
                mainMenu.add(handColorMenu);

                //======== digitMenu ========
                {
                    digitMenu.setText("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u0446\u0438\u0444\u0440");

                    //---- colorTextMenu ----
                    colorTextMenu.setText("\u0426\u0432\u0435\u0442");
                    colorTextMenu.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            colorTextMenuActionPerformed(e);
                        }
                    });
                    digitMenu.add(colorTextMenu);

                    //---- fontMenu ----
                    fontMenu.setText("\u0428\u0440\u0438\u0444\u0442");
                    fontMenu.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fontMenuActionPerformed(e);
                        }
                    });
                    digitMenu.add(fontMenu);
                }
                mainMenu.add(digitMenu);
            }
            menuBar.add(mainMenu);

            //======== alarmClockMenu ========
            {
                alarmClockMenu.setText("\u0411\u0443\u0434\u0438\u043b\u044c\u043d\u0438\u043a");

                //---- setAlarmClockMenu ----
                setAlarmClockMenu.setText("\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u0431\u0443\u0434\u0438\u043b\u044c\u043d\u0438\u043a");
                setAlarmClockMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setAlarmClockMenuActionPerformed( e );
                    }
                });
                alarmClockMenu.add(setAlarmClockMenu);
            }
            menuBar.add(alarmClockMenu);
        }
        setJMenuBar(menuBar);

        //---- watchPanel ----
        watchPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                watchPanelMouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(watchPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(watchPanel, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenu handColorMenu;
    private JMenuItem hourHandColor;
    private JMenuItem minuteHandColor;
    private JMenuItem secondHandColor;
    private JMenu digitMenu;
    private JMenuItem colorTextMenu;
    private JMenuItem fontMenu;
    private JMenu alarmClockMenu;
    private JMenuItem setAlarmClockMenu;
    private WatchPanel watchPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
