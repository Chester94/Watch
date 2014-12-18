package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Frolov Daniil IVT-43BO.
 * Dialog creation / modification / deletion of alarms.
 * Holds a reference to an array of alarm clocks, work that carries.
 */
public class AlarmTimeControlDialog extends JDialog
{
    /**
     * Array of alarms.
     */
    private AlarmArray alarms;

    /**
     * Initializes the class.
     * @param owner
     */
    public AlarmTimeControlDialog(Frame owner)
    {
        super(owner);
        initComponents();
    }

    /**
     * Initializes the class.
     * @param owner
     */
    public AlarmTimeControlDialog(Dialog owner)
    {
        super(owner);
        initComponents();
    }

    /**
     * Setter for alarms array.
     * @param alarms
     */
    private void setAlarms( AlarmArray alarms )
    {
        this.alarms = alarms;
    }

    /**
     * Sets an array of alarms.
     * Initializes the combo box.
     * Initializes field select a music file.
     * Disables the ability to resize the window.
     * Enables the visibility of the window.
     * @param alarms
     * @param curTime
     */
    public void showDialog( AlarmArray alarms, Time curTime )
    {
        setResizable( false );

        setAlarms( alarms );

        setComboBox( hourComboBox, 24, curTime.getHour() );
        setComboBox( minuteComboBox, 60, curTime.getMinute() );
        setComboBox( secondComboBox, 60, curTime.getSecond() );

        pathTextField.setText( "[default music]" );

        setAllAlarmsComboBox();

        setVisible( true );
    }

    /**
     * Installation of valid values for combo box.
     * @param comboBox hour / minute / second combo box.
     * @param value value ~ hour(24) / minute(60) / second(60)
     * @param curPosition starting position for combo box
     */
    private void setComboBox(JComboBox comboBox, int value, int curPosition)
    {
        String[] model = new String[value];
        for( int i = 0; i < value; i++ )
            model[i] = String.valueOf( i );

        comboBox.setModel( new DefaultComboBoxModel( model ) );
        comboBox.setSelectedIndex( curPosition );
    }

    /**
     * Set combo box for to select alarm.
     */
    private void setAllAlarmsComboBox()
    {
        allAlarmsComboBox.setModel( new DefaultComboBoxModel( alarms.getAllAlarmTime() ) );
        if(alarms.length() > 0)
            allAlarmsComboBox.setSelectedIndex( allAlarmsComboBox.getItemCount() - 1 );
    }

    /**
     * Get alarm formed by the values of a dialog.
     * @return alarm
     */
    private Alarm getNewAlarm()
    {
        return new Alarm( new Time( hourComboBox.getSelectedIndex(),
                minuteComboBox.getSelectedIndex(),
                secondComboBox.getSelectedIndex(), 0 ),
                pathTextField.getText());
    }

    /**
     * State change event combo box to select alarm.
     * @param e
     */
    private void allAlarmsComboBoxActionPerformed(ActionEvent e)
    {
        Alarm tmp = alarms.getAlarm(((JComboBox) e.getSource()).getSelectedIndex());

        if(tmp == null) return;

        Time t = tmp.callTime();

        hourComboBox.setSelectedIndex( t.getHour() );
        minuteComboBox.setSelectedIndex( t.getMinute() );
        secondComboBox.setSelectedIndex( t.getSecond() );

        pathTextField.setText( tmp.getPath() );
    }

    /**
     * Button event. Add new alarm.
     * @param e
     */
    private void addButtonActionPerformed(ActionEvent e)
    {
        alarms.add( getNewAlarm() );
        setAllAlarmsComboBox();
    }

    /**
     * Button event. Change alarm.
     * @param e
     */
    private void changeButtonActionPerformed(ActionEvent e)
    {
        alarms.change( allAlarmsComboBox.getSelectedIndex(), getNewAlarm() );
        setAllAlarmsComboBox();
    }

    /**
     * Button event. Delete alarm.
     * @param e
     */
    private void deleteButtonActionPerformed(ActionEvent e)
    {
        alarms.delete( allAlarmsComboBox.getSelectedIndex() );
        setAllAlarmsComboBox();
    }

    /**
     * Button event. Choose music file.
     * @param e
     */
    private void chooseFileButtonActionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV files", "wav");
        chooser.setFileFilter(filter);

        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            pathTextField.setText(chooser.getSelectedFile().getPath());
        else
            pathTextField.setText( "[default music]" );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        hourComboBox = new JComboBox();
        minuteComboBox = new JComboBox();
        secondComboBox = new JComboBox();
        allAlarmsComboBox = new JComboBox();
        chooseFileButton = new JButton();
        pathTextField = new JTextField();
        addButton = new JButton();
        changeButton = new JButton();
        deleteButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- allAlarmsComboBox ----
        allAlarmsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allAlarmsComboBoxActionPerformed(e);
            }
        });

        //---- chooseFileButton ----
        chooseFileButton.setText("\u0412\u044b\u0431\u043e\u0440 \u0444\u0430\u0439\u043b\u0430");
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFileButtonActionPerformed(e);
            }
        });

        //---- pathTextField ----
        pathTextField.setEditable(false);

        //---- addButton ----
        addButton.setText("\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });

        //---- changeButton ----
        changeButton.setText("\u0418\u0437\u043c\u0435\u043d\u0438\u0442\u044c");
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonActionPerformed(e);
            }
        });

        //---- deleteButton ----
        deleteButton.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(hourComboBox, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addComponent(minuteComboBox, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(secondComboBox, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(allAlarmsComboBox, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(changeButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                .addComponent(pathTextField))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(chooseFileButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(hourComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(minuteComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(secondComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(allAlarmsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseFileButton)
                        .addComponent(pathTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(changeButton)
                        .addComponent(deleteButton))
                    .addContainerGap(11, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JComboBox hourComboBox;
    private JComboBox minuteComboBox;
    private JComboBox secondComboBox;
    private JComboBox allAlarmsComboBox;
    private JButton chooseFileButton;
    private JTextField pathTextField;
    private JButton addButton;
    private JButton changeButton;
    private JButton deleteButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
