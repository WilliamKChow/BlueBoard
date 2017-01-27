/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.keyboard;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author seanv
 */
public class keyBot {

    public static final int PRESS_DEYLAY = 20;
    public static Robot bot;
    public static final float SENSITIVITY_SCALE = (float) 10;

    public static void moveMouse(double x, double y) throws AWTException {
        //Create new bot
        bot = new Robot();
        // Get cursor locatiosn
        int z = MouseInfo.getPointerInfo().getLocation().x;
        int w = MouseInfo.getPointerInfo().getLocation().y;
        // Calculate new location
        int move_x = (int) Math.ceil(z + SENSITIVITY_SCALE * (x));
        int move_y = (int) Math.ceil(w + SENSITIVITY_SCALE * (y));
        // move the mouse
        bot.mouseMove(move_x, move_y);
    }

    public static void scrollMouse(int c) throws AWTException {
        //Create a new bot
        bot = new Robot();
        // Scale c
        int scroll = c / 10;
        //Scroll Mouse
        bot.mouseWheel(scroll);
    }

    public static void mouseClick(String input) throws AWTException {
        //Create a new bot
        bot = new Robot();
        // use switch case to determine action
        switch (input) {
            case "LClick0":
                bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(10);
                break;
            case "LClick1":
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                bot.delay(10);
                break;
            case "2LClick":
                bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(10);
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                bot.delay(10);
                bot.mousePress(InputEvent.BUTTON1_MASK);
                bot.delay(10);
                bot.mouseRelease(InputEvent.BUTTON1_MASK);
                bot.delay(10);
                break;
            case "RClick":
                bot.mousePress(InputEvent.BUTTON3_MASK);
                bot.delay(10);
                bot.mouseRelease(InputEvent.BUTTON3_MASK);
                break;

        }
    }

    // this method is intentionally long to save time
    public static void typeKey(String keywords) throws AWTException {
        //Create a new bot
        bot = new Robot();
        int code = 0;
        switch (keywords) {
            case "<Enter>":
                bot.keyPress(KeyEvent.VK_ENTER);
                bot.delay(10);
                bot.keyRelease(KeyEvent.VK_ENTER);
                break;
            case "<Space>":
                bot.keyPress(KeyEvent.VK_SPACE);
                bot.delay(10);
                bot.keyRelease(KeyEvent.VK_SPACE);
                break;
            case "<Backspace>":
                bot.keyPress(KeyEvent.VK_BACK_SPACE);
                bot.delay(10);
                bot.keyRelease(KeyEvent.VK_BACK_SPACE);
                break;
            case "0":
                bot.keyPress(48);
                bot.delay(10);
                bot.keyRelease(48);
                break;
            case "1":
                bot.keyPress(49);
                bot.delay(10);
                bot.keyRelease(49);
                break;
            case "2":
                bot.keyPress(50);
                bot.delay(10);
                bot.keyRelease(50);
                break;
            case "3":
                bot.keyPress(51);
                bot.delay(10);
                bot.keyRelease(51);
                break;
            case "4":
                bot.keyPress(52);
                bot.delay(10);
                bot.keyRelease(52);
                break;
            case "5":
                bot.keyPress(53);
                bot.delay(10);
                bot.keyRelease(53);
                break;
            case "6":
                bot.keyPress(54);
                bot.delay(10);
                bot.keyRelease(54);
                break;
            case "7":
                bot.keyPress(55);
                bot.delay(10);
                bot.keyRelease(55);
                break;
            case "8":
                bot.keyPress(56);
                bot.delay(10);
                bot.keyRelease(56);
                break;
            case "9":
                bot.keyPress(57);
                bot.delay(10);
                bot.keyRelease(57);
                break;
            case "a":
                bot.keyPress(65);
                bot.delay(10);
                bot.keyRelease(65);
                break;
            case "b":
                bot.keyPress(66);
                bot.delay(10);
                bot.keyRelease(66);
                break;
            case "c":
                bot.keyPress(67);
                bot.delay(10);
                bot.keyRelease(67);
                break;
            case "d":
                bot.keyPress(68);
                bot.delay(10);
                bot.keyRelease(61);
                break;
            case "e":
                bot.keyPress(69);
                bot.delay(10);
                bot.keyRelease(69);
                break;
            case "f":
                bot.keyPress(70);
                bot.delay(10);
                bot.keyRelease(70);
                break;
            case "g":
                bot.keyPress(71);
                bot.delay(10);
                bot.keyRelease(71);
                break;
            case "h":
                bot.keyPress(72);
                bot.delay(10);
                bot.keyRelease(72);
                break;
            case "i":
                bot.keyPress(73);
                bot.delay(10);
                bot.keyRelease(73);
                break;
            case "j":
                bot.keyPress(74);
                bot.delay(10);
                bot.keyRelease(74);
                break;
            case "k":
                bot.keyPress(75);
                bot.delay(10);
                bot.keyRelease(75);
                break;
            case "l":
                bot.keyPress(76);
                bot.delay(10);
                bot.keyRelease(76);
                break;
            case "m":
                bot.keyPress(77);
                bot.delay(10);
                bot.keyRelease(77);
                break;
            case "n":
                bot.keyPress(78);
                bot.delay(10);
                bot.keyRelease(78);
                break;
            case "o":
                bot.keyPress(79);
                bot.delay(10);
                bot.keyRelease(79);
                break;
            case "p":
                bot.keyPress(80);
                bot.delay(10);
                bot.keyRelease(80);
                break;
            case "q":
                bot.keyPress(81);
                bot.delay(10);
                bot.keyRelease(81);
                break;
            case "r":
                bot.keyPress(82);
                bot.delay(10);
                bot.keyRelease(82);
                break;
            case "s":
                bot.keyPress(83);
                bot.delay(10);
                bot.keyRelease(83);
                break;
            case "t":
                bot.keyPress(84);
                bot.delay(10);
                bot.keyRelease(84);
                break;
            case "u":
                bot.keyPress(85);
                bot.delay(10);
                bot.keyRelease(85);
                break;
            case "v":
                bot.keyPress(86);
                bot.delay(10);
                bot.keyRelease(86);
                break;
            case "w":
                bot.keyPress(87);
                bot.delay(10);
                bot.keyRelease(87);
                break;
            case "x":
                bot.keyPress(88);
                bot.delay(10);
                bot.keyRelease(89);
                break;
            case "y":
                bot.keyPress(90);
                bot.delay(10);
                bot.keyRelease(90);
                break;
            case "z":
                bot.keyPress(91);
                bot.delay(10);
                bot.keyRelease(91);
                break;
            case "`":
                bot.keyPress(192);
                bot.delay(10);
                bot.keyRelease(192);

                break;
            case "-":
                bot.keyPress(45);
                bot.delay(10);
                bot.keyRelease(45);
                break;
            case "=":
                bot.keyPress(61);
                bot.delay(10);
                bot.keyRelease(61);
                break;
            case "[":
                bot.keyPress(91);
                bot.delay(10);
                bot.keyRelease(91);
                break;
            case "]":
                bot.keyPress(93);
                bot.delay(10);
                bot.keyRelease(93);
                break;
            case "\\":
                bot.keyPress(92);
                bot.delay(10);
                bot.keyRelease(92);
                break;
            case ";":
                bot.keyPress(59);
                bot.delay(10);
                bot.keyRelease(59);
                break;
            case "\'":
                bot.keyPress(222);
                bot.delay(10);
                bot.keyRelease(222);
                break;
            case ",":
                bot.keyPress(44);
                bot.delay(10);
                bot.keyRelease(44);
                break;
            case ".":
                bot.keyPress(46);
                bot.delay(10);
                bot.keyRelease(46);
                break;
            case "/":
                bot.keyPress(191);
                bot.delay(10);
                bot.keyRelease(191);
                break;
            case ")":
                bot.keyPress(16);
                typeKey("0");
                bot.keyRelease(16);
                break;
            case "!":
                bot.keyPress(16);
                typeKey("1");
                bot.keyRelease(16);
                break;
            case "@":
                bot.keyPress(16);
                typeKey("2");
                bot.keyRelease(16);
                break;
            case "#":
                bot.keyPress(16);
                typeKey("3");
                bot.keyRelease(16);
                break;
            case "$":
                bot.keyPress(16);
                typeKey("4");
                bot.keyRelease(16);
                break;
            case "%":
                bot.keyPress(16);
                typeKey("5");
                bot.keyRelease(16);
                break;
            case "^":
                bot.keyPress(16);
                typeKey("6");
                bot.keyRelease(16);
                break;
            case "&":
                bot.keyPress(16);
                typeKey("7");
                bot.keyRelease(16);
                break;
            case "*":
                bot.keyPress(16);
                typeKey("8");
                bot.keyRelease(16);
                break;
            case "(":
                bot.keyPress(16);
                typeKey("9");
                bot.keyRelease(16);
                break;
            case "A":
                bot.keyPress(16);
                typeKey("a");
                bot.keyRelease(16);
                break;
            case "B":
                bot.keyPress(16);
                typeKey("b");
                bot.keyRelease(16);
                break;
            case "C":
                bot.keyPress(16);
                typeKey("c");
                bot.keyRelease(16);
                break;
            case "D":
                bot.keyPress(16);
                typeKey("d");
                bot.keyRelease(16);
                break;
            case "E":
                bot.keyPress(16);
                typeKey("e");
                bot.keyRelease(16);
                break;
            case "F":
                bot.keyPress(16);
                typeKey("f");
                bot.keyRelease(16);
                break;
            case "G":
                bot.keyPress(16);
                typeKey("g");
                bot.keyRelease(16);
                break;
            case "H":
                bot.keyPress(16);
                typeKey("h");
                bot.keyRelease(16);
                break;
            case "I":
                bot.keyPress(16);
                typeKey("i");
                bot.keyRelease(16);
                break;
            case "J":
                bot.keyPress(16);
                typeKey("j");
                bot.keyRelease(16);
                break;
            case "K":
                bot.keyPress(16);
                typeKey("k");
                bot.keyRelease(16);
                break;
            case "L":
                bot.keyPress(16);
                typeKey("l");
                bot.keyRelease(16);
                break;
            case "M":
                bot.keyPress(16);
                typeKey("m");
                bot.keyRelease(16);
                break;
            case "N":
                bot.keyPress(16);
                typeKey("n");
                bot.keyRelease(16);
                break;
            case "O":
                bot.keyPress(16);
                typeKey("o");
                bot.keyRelease(16);
                break;
            case "P":
                bot.keyPress(16);
                typeKey("p");
                bot.keyRelease(16);
                break;
            case "Q":
                bot.keyPress(16);
                typeKey("q");
                bot.keyRelease(16);
                break;
            case "R":
                bot.keyPress(16);
                typeKey("r");
                bot.keyRelease(16);
                break;
            case "S":
                bot.keyPress(16);
                typeKey("s");
                bot.keyRelease(16);
                break;
            case "T":
                bot.keyPress(16);
                typeKey("t");
                bot.keyRelease(16);
                break;
            case "U":
                bot.keyPress(16);
                typeKey("u");
                bot.keyRelease(16);
                break;
            case "V":
                bot.keyPress(16);
                typeKey("v");
                bot.keyRelease(16);
                break;
            case "W":
                bot.keyPress(16);
                typeKey("w");
                bot.keyRelease(16);
                break;
            case "X":
                bot.keyPress(16);
                typeKey("x");
                bot.keyRelease(16);
                break;
            case "Y":
                bot.keyPress(16);
                typeKey("y");
                bot.keyRelease(16);
                break;
            case "Z":
                bot.keyPress(16);
                typeKey("z");
                bot.keyRelease(16);
                break;
            case "~":
                bot.keyPress(16);
                typeKey("`");
                bot.keyRelease(16);
                break;
            case "_":
                bot.keyPress(16);
                typeKey("-");
                bot.keyRelease(16);
                break;
            case "+":
                bot.keyPress(16);
                typeKey("=");
                bot.keyRelease(16);
                break;
            case "{":
                bot.keyPress(16);
                typeKey("[");
                bot.keyRelease(16);
                break;
            case "}":
                bot.keyPress(16);
                typeKey("]");
                bot.keyRelease(16);
                break;
            case "|":
                bot.keyPress(16);
                typeKey("\\");
                bot.keyRelease(16);
                break;
            case ":":
                bot.keyPress(16);
                typeKey(";");
                bot.keyRelease(16);
                break;
            case "\"":
                bot.keyPress(16);
                typeKey("'");
                bot.keyRelease(16);
                break;
            case "<":
                bot.keyPress(16);
                typeKey(",");
                bot.keyRelease(16);
                break;
            case ">":
                bot.keyPress(16);
                typeKey(".");
                bot.keyRelease(16);
                break;
            case "?":
                bot.keyPress(16);
                typeKey("//");
                bot.keyRelease(16);
                break;
            case "//":
                bot.keyPress(47);
                bot.delay(10);
                bot.keyRelease(47);

        }
    }
}
