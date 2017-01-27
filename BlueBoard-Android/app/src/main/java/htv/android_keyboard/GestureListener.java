package htv.android_keyboard;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by jr on 1/7/2017.
 */

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    static float xCoordinate, yCoordinate;

    @Override
    public boolean onScroll(MotionEvent ev1, MotionEvent ev2, float x, float y) {
        // Register the current coordinates of the touchpad
        // ev2.getX() and getY() is the position of current touch
        xCoordinate = ev2.getX();
        yCoordinate = ev2.getY();

        return true;
    }

    @Override
    public boolean onDown(MotionEvent ev){
        // Should set new previous coordinates every time screen is pressed again
        PreviousCursorLocation.x = ev.getX();
        PreviousCursorLocation.y = ev.getY();

        return true;
    }
}
