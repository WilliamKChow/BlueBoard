package htv.android_keyboard;

/**
 * Created by jr on 1/8/2017.
 */

public class Command {
    // Special strings for mouse
    final static String L_CLICK1 = "LClick0";
    final static String L_CLICK2 = "LClick1";
    final static String R_CLICK = "RClick";

    // Special strings for keyboard
    final static String ENTER = "<Enter>";
    final static String BACKSPACE = "<Backspace>";
    final static String SPACE = "<Space>";


    public static String key(String s){
        // Returns the command to input a certain string c.
        return "key " + s;
    }

    public static String mouseMove(float x, float y){
        // Returns the command to move the mouse.
        return "mouse Move " + Float.toString(x) + " " + Float.toString(y);
    }

    public static String mouseClick(String s){
        // Returns the command to click the mouse, left or right click.
        return "mouse " + s;
    }

    public static String mouseScroll(float y){
        // Returns the command to scroll y.
        return "mouse Scroll " + Integer.toString((int)(-y));
    }

    public static String end(){
        // Returns the end command.
        return "end";
    }

    public static String kill(){
        // Returns the kill command.
        return "kill";
    }

}