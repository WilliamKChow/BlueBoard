package pc.keyboard;

import java.awt.AWTException;
import java.util.Scanner;
import java.io.*;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class CommandInterpreter {

    public static RemoteDevice dev;
    public static BufferedReader bReader;

    public static void main(String[] args) throws IOException, AWTException {
        serverSetup();
        // keyBot object initialized\
        keyBot kb = new keyBot();

        //loops through every line in file
        while (true) {

            String line = bReader.readLine();
            if (line.equals("end")) {
                serverSetup();
            } else if (line.equals("kill")) {
                break;
            }
            //scans through line
            Scanner scan = new Scanner(line);

            String identifier = scan.next();

            // If the current word is key
            if (identifier.equals("key")) {

                // Declares a variable for the next word
                String next = scan.next();

                // Prints the next word in the file
                kb.typeKey(next);
            } else if (identifier.equals("mouse")) {

                String op = scan.next();

                if (op.equals("Move")) {
                    kb.moveMouse(scan.nextDouble(), scan.nextDouble());
                } else if (op.equals("Scroll")) {
                    kb.scrollMouse(scan.nextInt());
                } else if (op.equals("LClick0")) {
                    kb.mouseClick("LClick0");
                } else if (op.equals("LClick1")) {
                    kb.mouseClick("LClick1");
                } else if (op.equals("2LClick")) {
                    kb.mouseClick("2LClick");
                } else if (op.equals("RClick")) {
                    kb.mouseClick("RClick");
                } else {
                    System.out.println("Mouse Error");
                }
            } // In case of invalid input
            else {
                System.out.println("Error");
            }

            scan.close();

        }
    }

    public static void serverSetup() throws IOException {
        //Create a UUID for SPP
        UUID uuid = new UUID("1101", true);
        //Create the servicve url
        String connectionString = "btspp://localhost:" + uuid + ";name=Sample SPP Server";

        //open server url
        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier) Connector.open(connectionString);

        //Wait for client connection
        System.out.println("\nServer Started. Waiting for clients to connect...");
        StreamConnection connection = streamConnNotifier.acceptAndOpen();

        dev = RemoteDevice.getRemoteDevice(connection);
        // Create input stream
        InputStream inStream = connection.openInputStream();
        bReader = new BufferedReader(new InputStreamReader(inStream));

        streamConnNotifier.close();
    }
}
