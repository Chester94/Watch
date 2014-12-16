package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AlarmTimeControlDialog extends JDialog
{
    private AlarmArray alarms;

    public AlarmTimeControlDialog(Frame owner)
    {
        super(owner);
        initComponents();
        setResizable( false );
    }

    public AlarmTimeControlDialog(Dialog owner)
    {
        super(owner);
        initComponents();
        setResizable( false );
    }

    public void setAlarms( AlarmArray alarms )
    {
        this.alarms = alarms;
    }

    public void showDialog( AlarmArray alarms )
    {
        setAlarms( alarms );

        String[] hour = new String[24];
        for( int i = 0; i < 24; i++ )
            hour[i] = String.valueOf( i );

        hourComboBox.setModel( new DefaultComboBoxModel( hour ) );
        hourComboBox.setSelectedIndex( 9 );

        String[] minute = new String[60];
        for( int i = 0; i < 60; i++ )
            minute[i] = String.valueOf( i );

        minuteComboBox.setModel( new DefaultComboBoxModel( minute ) );
        minuteComboBox.setSelectedIndex( 0 );

        String[] second = new String[60];
        for( int i = 0; i < 60; i++ )
            second[i] = String.valueOf( i );

        secondComboBox.setModel( new DefaultComboBoxModel( second ) );
        secondComboBox.setSelectedIndex( 0 );

        pathTextField.setText( "" );

        setAllAlarmsComboBox();

        setVisible( true );
    }

    private void setAllAlarmsComboBox()
    {
        allAlarmsComboBox.setModel( new DefaultComboBoxModel( alarms.getAllAlarmTime() ) );
        if(alarms.length() > 0)
            allAlarmsComboBox.setSelectedIndex( allAlarmsComboBox.getItemCount() - 1 );
    }

    private Alarm getNewAlarm()
    {
        return new Alarm( new Time( hourComboBox.getSelectedIndex(),
                minuteComboBox.getSelectedIndex(),
                secondComboBox.getSelectedIndex(), 0 ),
                pathTextField.getText());
    }

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

    private void addButtonActionPerformed(ActionEvent e)
    {
        alarms.add( getNewAlarm() );
        setAllAlarmsComboBox();
    }

    private void changeButtonActionPerformed(ActionEvent e)
    {
        alarms.change( allAlarmsComboBox.getSelectedIndex(), getNewAlarm() );
        setAllAlarmsComboBox();
    }

    private void deleteButtonActionPerformed(ActionEvent e)
    {
        alarms.delete( allAlarmsComboBox.getSelectedIndex() );
        setAllAlarmsComboBox();
    }

    private void chooseFileButtonActionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV files", "wav");
        chooser.setFileFilter(filter);

        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            pathTextField.setText(chooser.getSelectedFile().getPath());
        else
            pathTextField.setText( "" );
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
