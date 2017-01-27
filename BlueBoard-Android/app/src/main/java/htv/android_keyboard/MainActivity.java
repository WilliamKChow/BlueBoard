package htv.android_keyboard;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


public class MainActivity extends AppCompatActivity {
    private TextView out;
    private GestureDetector mGestureDetector;

    // Variables for keyboard
    private EditText keyboard;
    private Button enter;

    // Variables for touchpad
    private ImageView touchpad;
    private float touchpadTopLeft[];
    private float touchpadBottomRight[];

    // Variables for Bluetooth operation
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;

    // Well known SPP UUID
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // Server's MAC Address (hard-coded for now)
    private static String address = "58:FB:84:57:19:07";
    //private static String address = "E0:06:E6:BA:CD:74";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up gestures
        mGestureDetector = new GestureDetector(this, new GestureListener());

        // Set up touchpad
        touchpad = (ImageView)(findViewById(R.id.touchpad));

        // Set up enter button
        enter = (Button)(findViewById(R.id.enter));

        // Watch enter key
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // Perform enter
                sendCommand(Command.key(Command.ENTER));
            }
        });

        // Watch left click and right click
        final Button lClick = (Button)(findViewById(R.id.lClick)),
                rClick = (Button)(findViewById(R.id.rClick));

        lClick.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform a left click
                sendCommand(Command.mouseClick(Command.L_CLICK1));
                sendCommand(Command.mouseClick(Command.L_CLICK2));
            }
        });

        rClick.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform a right click
                sendCommand(Command.mouseClick(Command.R_CLICK));
            }
        });

        // Setting up the keyboard
        keyboard = (EditText)(findViewById(R.id.input));
        keyboard.addTextChangedListener(filterTextWatcher);
        // Puts space so an empty string would signify backspace
        keyboard.setText(" ");

        // Setting up bluetooth
        out = (TextView)(findViewById(R.id.mGestureText));
        // Get adapter
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        CheckBTState();

        final Button kill = (Button) findViewById(R.id.kill);

        kill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Kills the program
                sendCommand(Command.kill());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        out.append("\n...In onStart()...");
    }

    @Override
    public void onResume() {
        super.onResume();

        System.out.println("Attempting client connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.
        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            AlertBox("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        try {
            btSocket.connect();
            out.append("\n...Connection established and data link opened...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                AlertBox("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }
    }

    private void sendCommand(String command){
        // Sends a string command to the server.
        // Create a data stream so we can talk to server.
        out.append("\n...Sending message to server...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            AlertBox("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }

        String message = command + "\n";
        byte[] msgBuffer = message.getBytes();
        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
            if (address.equals("58:FB:84:57:19:07"))
                msg = msg + ".\n\nUpdate your server address from 58:FB:84:57:19:07 to the correct address on line 37 in the java code";
            msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

            AlertBox("Fatal Error", msg);
        }
    }



    @Override
    public void onPause() {
        super.onPause();

        System.out.println("end");
        sendCommand(Command.end());

        if (outStream != null) {
            try {
                outStream.flush();
            } catch (IOException e) {
                AlertBox("Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
            }

        }

        try {
            btSocket.close();
        } catch (IOException e2) {
            AlertBox("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }



    @Override
    public void onStop() {
        super.onStop();
        out.append("\n...In onStop()...");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        out.append("\n...In onDestroy()...");
    }


    private void CheckBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on

        // Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null) {
            AlertBox("Fatal Error", "Bluetooth Not supported. Aborting.");
        } else {
            if (btAdapter.isEnabled()) {
                out.append("\n...Bluetooth is enabled...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    public void AlertBox( String title, String message ){
        new AlertDialog.Builder(this)
                .setTitle( title )
                .setMessage( message + " Press OK to exit." )
                .setPositiveButton("OK", new OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).show();
    }


    private TextWatcher filterTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String keyboardString = keyboard.getText().toString();

            // Special case: backspace is <Backspace>
            if (keyboardString.isEmpty()){
                // Sets a delay
                sendCommand(Command.key(Command.BACKSPACE));
            }

            else if (keyboardString.length() > 1){
                String keyboardChar = Character.toString(keyboardString.charAt(1));
                // Special case: literal space is <Space>
                if (keyboardChar.equals(" ")){
                    sendCommand(Command.key(Command.SPACE));
                }
                // Char is exactly the same
                else {
                    sendCommand(Command.key(keyboardChar));
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {
            keyboard.removeTextChangedListener(filterTextWatcher);
            keyboard.setText(" ");
            keyboard.addTextChangedListener(filterTextWatcher);
            // Push selection to the end of the text edit
            keyboard.setSelection(keyboard.getText().length());
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        // Allows a buffer of time to set the touchpad coordinates after onCreate();
        if (hasFocus){
            setTouchpadCoordinates();
        }
    }

    private void setTouchpadCoordinates(){
        // Magic numbers for grabbing x, y coordinates
        final int X = 0, Y = 1;

        // Initialize top-left, top-right coordinates
        touchpadTopLeft = new float[2];
        touchpadBottomRight  = new float[2];

        // Set top-left coordinates
        touchpadTopLeft[X] = touchpad.getLeft();
        touchpadTopLeft[Y] = touchpad.getBottom() - touchpad.getHeight()/2;

        // Set bottom-right coordinates
        touchpadBottomRight[X] = touchpad.getRight();
        touchpadBottomRight[Y] = touchpad.getBottom() + touchpad.getHeight()/2;
    }

    private boolean inTouchpad(float x, float y){
        // Checks if the coordinates are within the touchpad boundaries
        return (x >= touchpadTopLeft[0] &&
                x <= touchpadBottomRight[0] &&
                y >= touchpadTopLeft[1] &&
                y <= touchpadBottomRight[1]);
    }

    private float[] changeInCursor(float x, float y){
        // Register the coordinates of the touch
        float movementX, movementY;

        // Find difference in x and y coordinates
        movementX = x - PreviousCursorLocation.x;
        movementY = y - PreviousCursorLocation.y;

        // Set new previous cursor location
        PreviousCursorLocation.x = x;
        PreviousCursorLocation.y = y;

        // Returns an array containing the changes in movement
        float[] deltas = new float[2];
        deltas[0] = movementX;
        deltas[1] = movementY;
        return deltas;
    }

    // onTouch() method gets called each time you perform any touch event with screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean eventConsumed = mGestureDetector.onTouchEvent(event);
        boolean touched = false;

        // Magic numbers for x and y elements
        final int X = 0, Y = 1;

        // Check if a gesture has occurred
        if (eventConsumed) {
            final float xCoordinate = event.getX(), yCoordinate = event.getY();

            // Check if the press coordinates are within the touchpad boundary
            if (inTouchpad(xCoordinate, yCoordinate)){
                float deltas[] = changeInCursor(xCoordinate, yCoordinate);

                // If there are two fingers on the screen, scroll
                if (event.getPointerCount() == 2){
                    sendCommand(Command.mouseScroll(deltas[Y]));
                }

                // Else, just move the cursor
                else {
                    sendCommand(Command.mouseMove(deltas[X], deltas[Y]));
                }
            }

            touched = true;
        }

        return touched;
    }
}