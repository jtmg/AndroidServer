import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by josétiago on 25/05/2016.
 */
public class Move {
    // variables given by the sensor, this variables are the rate that the mouse should move
    private int rateX,rateY;
    //current positions of the mouse
    private final int mouseX, mouseY;
    // initializing the constructor
   public Move(double rateX, double rateY)
    {
        this.rateX = (int) (rateX * 2);
        this.rateY = (int) (rateY * 2);
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        mouseX = x ;
        mouseY = y ;

    }

    synchronized public void move () throws AWTException {
        Robot robot = new Robot();
        //verifications for moving the mouse, if the sensor given values are negative or positive
//        if (rateX < 0 && rateY < 0)
//        {
//            robot.mouseMove(mouseX - rateX, mouseY - rateY);
//        }
//        if (rateX < 0 && rateY >= 0)
//        {
//            robot.mouseMove(mouseX - rateX,mouseY + rateY);
//        }
//        if (rateX >= 0 && rateY < 0)
//        {
//            robot.mouseMove(mouseX + rateX, mouseY - rateY);
//        }
//        if (rateX >= 0 && rateY >=0)
//        {
//            robot.mouseMove(mouseX + rateX , mouseY + rateY);
//        }
        robot.mouseMove(mouseX - rateX, mouseY - rateY);
    }
    synchronized public void click (String right, String left ) throws AWTException {
        Robot robot = new Robot();

        if (right.equals("true"))
        {
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
        }
        if (left.equals("true"))
        {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }


    }
}
