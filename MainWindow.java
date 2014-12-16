/*
 * Created by JFormDesigner on Thu Dec 11 17:43:14 MSK 2014
 */

package com.company;

import com.ozten.font.JFontChooser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.*;
import java.util.Timer;

public class MainWindow extends JFrame
{
    private Timer timerWatch = new Timer();
    private Timer timerAlarm = new Timer();
    private AlarmArray alarms = new AlarmArray();

    public MainWindow()
    {
        super( "Часы" );
        initComponents();
        watchPanel.countScale();
        watchPanel.setGraphContext();

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        setResizable( false );
        setVisible( true );

        setTimers();
    }

    private void setTimers()
    {
        timerWatch.scheduleAtFixedRate( new TimerTask()
        {
            @Override
            public void run()
            {
                watchPanel.redrawWatch( new Time(GregorianCalendar.getInstance().get( Calendar.HOUR_OF_DAY ),
                        GregorianCalendar.getInstance().get( Calendar.MINUTE ),
                        GregorianCalendar.getInstance().get( Calendar.SECOND ),
                        GregorianCalendar.getInstance().get( Calendar.MILLISECOND )) );
            }
        }, 0, 25 );

        timerAlarm.scheduleAtFixedRate( new TimerTask()
        {
            @Override
            public void run()
            {
                checkAlarmTime( new Time(GregorianCalendar.getInstance().get( Calendar.HOUR_OF_DAY ),
                        GregorianCalendar.getInstance().get( Calendar.MINUTE ),
                        GregorianCalendar.getInstance().get( Calendar.SECOND ), 0) );
            }
        }, 0, 1000 );
    }

    private void hourHandColorActionPerformed(ActionEvent e)
    {
        watchPanel.setHourHandColor(
                JColorChooser.showDialog( this, "Выбор цвета для часовой стрелки",
                        watchPanel.getHourHandColor() ) );
    }

    private void minuteHandColorActionPerformed(ActionEvent e)
    {
        watchPanel.setMinuteHandColor(
                JColorChooser.showDialog( this, "Выбор цвета для минутной стрелки",
                        watchPanel.getMinuteHandColor() ) );
    }

    private void secondHandColorActionPerformed(ActionEvent e)
    {
        watchPanel.setSecondHandColor(
                JColorChooser.showDialog( this, "Выбор цвета для секундной стрелки",
                        watchPanel.getSecondHandColor() ) );
    }

    private void colorTextMenuActionPerformed(ActionEvent e)
    {
        watchPanel.setTextColor(
                JColorChooser.showDialog( this, "Выбор цвета для текста",
                        watchPanel.getTextColor()) );
    }

    private void fontMenuActionPerformed(ActionEvent e)
    {
        watchPanel.setTextFont(
                JFontChooser.showDialog( this, "Выбор шрифта для цифр", "Предложение для проверки",
                        watchPanel.getTextFont() ) );
    }

    private void setAlarmClockMenuActionPerformed(ActionEvent e)
    {
        AlarmTimeControlDialog a = new AlarmTimeControlDialog( this );
        a.showDialog( alarms );
    }

    private void checkAlarmTime(Time curTime)
    {
        alarms.playAppropriateAlarm( curTime );
    }

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
                        setAlarmClockMenuActionPerformed(e);
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
