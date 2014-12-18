/*
 * Created by JFormDesigner on Thu Dec 11 17:52:14 MSK 2014
 */

package com.company;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Frolov Daniil IVT-43BO.
 * Display area directly watch.
 */
public class WatchPanel extends JPanel
{
    /**
     * Text color
     */
    private Color textColor = Color.WHITE;
    /**
     * Hour hand color
     */
    private Color hourHandColor = Color.WHITE;
    /**
     * Minute hand color
     */
    private Color minuteHandColor = Color.WHITE;
    /**
     * Second hand color
     */
    private Color secondHandColor = Color.WHITE;
    /**
     * Border of hand color
     */
    private Color borderHandColor = Color.BLACK;

    /**
     * Text font
     */
    private Font textFont = new Font( "Starcraft", Font.ITALIC, 36 );

    private Graphics graphContext;

    private final int maxClockLength = 180;
    private final int gradeInSegmentSecond = 6;
    private final int gradeInSegmentMinute = 6;
    private final int gradeInSegmentHour = 30;
    private final double gradeInSegmentMilliSecond = 6./1000;

    /**
     * Initializes the class.
     */
    public WatchPanel()
    {
        initComponents();
    }

    /**
     * Set hour hand color.
     * @param hourHandColor Color for hour hand. if == null => no changes
     */
    public void setHourHandColor( Color hourHandColor )
    {
        if(hourHandColor == null) return;
        this.hourHandColor = hourHandColor;
    }

    /**
     * Set minute hand color.
     * @param minuteHandColor Color for minute hand. if == null => no changes
     */
    public void setMinuteHandColor( Color minuteHandColor )
    {
        if(minuteHandColor == null) return;
        this.minuteHandColor = minuteHandColor;
    }

    /**
     * Set second hand color.
     * @param secondHandColor Color for second hand. if == null => no changes
     */
    public void setSecondHandColor( Color secondHandColor )
    {
        if(secondHandColor == null) return;
        this.secondHandColor = secondHandColor;
    }

    /**
     * Set text color.
     * @param textColor Color for text. if == null => no changes
     */
    public void setTextColor( Color textColor )
    {
        if(textColor == null) return;
        this.textColor = textColor;
    }

    /**
     * Getter for text font.
     * @return current text font
     */
    public Font getTextFont()
    {
        return textFont;
    }

    /**
     * Set text font.
     * Redraw numbers.
     * @param textFont font for text. if == null => no changes
     */
    public void setTextFont( Font textFont )
    {
        if(textFont == null) return;
        redrawDial( getBackground() );
        this.textFont = textFont;
        redrawDial( textColor );
    }

    /**
     * Getter for hour hand color.
     * @return current hour hand color
     */
    public Color getHourHandColor()
    {
        return hourHandColor;
    }

    /**
     * Getter for minute hand color.
     * @return current minute hand color
     */
    public Color getMinuteHandColor()
    {
        return minuteHandColor;
    }

    /**
     * Getter for second hand color.
     * @return current second hand color
     */
    public Color getSecondHandColor()
    {
        return secondHandColor;
    }

    /**
     * Getter for text color.
     * @return current text color
     */
    public Color getTextColor()
    {
        return textColor;
    }

    /**
     * Translate coordinates.
     */
    public void setGraphContext()
    {
        graphContext = this.getGraphics();
        graphContext.translate( this.getSize().width / 2, this.getSize().height / 2 );
    }

    /**
     * Redraw watch (hand and dial).
     * @param curTime current system time
     */
    public void redrawWatch(Time curTime)
    {
        redrawHands( curTime );
        redrawDial( textColor );
    }

    /**
     * Redraw hands.
     * @param curTime current system time
     */
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

    /**
     * Draw one hand.
     * @param grade degree of deviation from the vertical
     * @param bottomHand smaller diagonal of the quadrilateral
     * @param heightUpHand length from the center to the vertex distance
     * @param heightDownHand length from near the center to the vertices
     * @param inside inside color
     * @param border border color
     */
    private void drawHand(double grade, int bottomHand, int heightUpHand, int heightDownHand,
                          Color inside, Color border)
    {
        grade -= 90;

        double radianPoint1 = grade * Math.PI/180;
        double radianPoint2 = (grade+90) * Math.PI/180;
        double radianPoint3 = (grade + 180) * Math.PI/180;
        double radianPoint4 = (grade-90) * Math.PI/180;

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

    /**
     * Redraw dial.
     * @param color number color
     */
    private void redrawDial(Color color)
    {
        double radian;
        double x, y;

        final int OFFSET_X = -15;
        final int OFFSET_Y = 10;

        graphContext.setColor( color );
        graphContext.setFont( textFont );

        for( int i = 1; i <= 60 ; i++ )
        {
            radian = ( -90 + gradeInSegmentMinute * i ) * Math.PI/180;

            x = Math.cos( radian );
            y = Math.sin( radian );

            if( i % 5 == 0 )
                graphContext.drawString( Integer.toString( i / 5 ),
                        (int)(x*230 + OFFSET_X), (int)(y*230 + OFFSET_Y) );

            graphContext.fillOval( (int)(x*200 - 2), (int)(y*200 - 2), 4, 4 );
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
