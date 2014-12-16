/*
 * Created by JFormDesigner on Thu Dec 11 17:52:14 MSK 2014
 */

package com.company;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class WatchPanel extends JPanel
{
    private int width;
    private int height;

    private Color textColor = Color.WHITE;
    private Color hourHandColor = Color.WHITE;
    private Color minuteHandColor = Color.WHITE;
    private Color secondHandColor = Color.WHITE;
    private Color borderHandColor = Color.BLACK;

    private Font textFont = new Font( "Starcraft", Font.ITALIC, 36 );

    private Graphics graphContext;

    private final int maxClockLength = 180;
    private final int gradeInSegmentSecond = 6;
    private final int gradeInSegmentMinute = 6;
    private final int gradeInSegmentHour = 30;
    private final double gradeInSegmentMilliSecond = 6./1000;

    private final double pi = Math.PI;

    public WatchPanel()
    {
        initComponents();
    }

    public void setHourHandColor( Color hourHandColor )
    {
        if(hourHandColor == null) return;
        this.hourHandColor = hourHandColor;
    }

    public void setMinuteHandColor( Color minuteHandColor )
    {
        if(minuteHandColor == null) return;
        this.minuteHandColor = minuteHandColor;
    }

    public void setSecondHandColor( Color secondHandColor )
    {
        if(secondHandColor == null) return;
        this.secondHandColor = secondHandColor;
    }

    public void setTextColor( Color textColor )
    {
        if(textColor == null) return;
        this.textColor = textColor;
    }

    public Font getTextFont()
    {
        return textFont;
    }

    public void setTextFont( Font textFont )
    {
        if(textFont == null) return;
        redrawDial( getBackground() );
        this.textFont = textFont;
        redrawDial( textColor );
    }

    public Color getHourHandColor()
    {
        return hourHandColor;
    }

    public Color getMinuteHandColor()
    {
        return minuteHandColor;
    }

    public Color getSecondHandColor()
    {
        return secondHandColor;
    }

    public Color getTextColor()
    {
        return textColor;
    }

    public void countScale()
    {
        width = this.getSize().width;
        height = this.getSize().height;
    }

    public void setGraphContext()
    {
        graphContext = this.getGraphics();
        graphContext.translate( width/2, height/2 );
    }

    public void redrawWatch(Time curTime)
    {
        redrawHands( curTime );
        redrawDial( textColor );
    }

    private void redrawHands(Time curTime)
    {
        graphContext.setColor( getBackground() );
        graphContext.fillOval( -maxClockLength - 5, -maxClockLength - 5,
                maxClockLength*2 + 10, maxClockLength*2 + 10);

        drawHand( curTime.getHour() * gradeInSegmentHour + curTime.getMinute() * gradeInSegmentHour / 60.,
                50, 140, 25, hourHandColor, borderHandColor );
        drawHand( curTime.getMinute() * gradeInSegmentMinute + curTime.getSecond() * gradeInSegmentMinute / 60.,
                40, 160, 25, minuteHandColor, borderHandColor );
        drawHand( curTime.getSecond() * gradeInSegmentSecond + curTime.getMillisecond() * gradeInSegmentMilliSecond,
                30, 180, 25, secondHandColor, borderHandColor );
    }

    private void drawHand(double grade, int bottomHand, int heightUpHand, int heightDownHand,
                          Color inside, Color border)
    {
        grade -= 90;

        double radianPoint1 = grade * pi/180;
        double radianPoint2 = (grade+90) * pi/180;
        double radianPoint3 = (grade + 180) * pi/180;
        double radianPoint4 = (grade-90) * pi/180;

        int x1 = (int)(Math.cos( radianPoint1 )*heightUpHand);
        int y1 = (int)(Math.sin( radianPoint1 )*heightUpHand);

        int x2 = (int)(Math.cos( radianPoint2 )*bottomHand/2);
        int y2 = (int)(Math.sin( radianPoint2 )*bottomHand/2);

        int x3 = (int)(Math.cos( radianPoint3 )*heightDownHand);
        int y3 = (int)(Math.sin( radianPoint3 )*heightDownHand);

        int x4 = (int)(Math.cos( radianPoint4 )*bottomHand/2);
        int y4 = (int)(Math.sin( radianPoint4 )*bottomHand/2);

        graphContext.setColor( inside );
        graphContext.fillPolygon( new int[]{x1, x2, x3, x4}, new int[]{y1, y2, y3, y4}, 4 );

        graphContext.setColor( border );
        graphContext.drawPolygon( new int[]{x1, x2, x3, x4}, new int[]{y1, y2, y3, y4}, 4 );
    }

    private void redrawDial(Color color)
    {
        double radian;
        int x, y;

        graphContext.setColor( color );
        graphContext.setFont( textFont );

        for( int i = 1; i <= 12; i++ )
        {
            radian = ( -90 + gradeInSegmentHour * i ) * pi/180;
            x = (int)(Math.cos( radian ) * 230) - 15;
            y = (int)(Math.sin( radian ) * 230) + 10;

            graphContext.drawString( Integer.toString( i ), x, y);
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        setBorder(LineBorder.createBlackLineBorder());
        setBackground(new Color(255, 102, 255));
        setLayout(null);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
