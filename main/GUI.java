package main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.BorderLayout;
import java.awt.*;

/**
 * GUI.
 * 
 * @author Skylar
 * @version
 */

public class GUI extends JFrame {
    
    private Main main; // interface to database
    private int choice; // users choice 1-5

    /**
     * Constructor for objects of type GUI. Constructs the GUI and adds a
     * keyboard listener to capture the user's choices from the menu.
     */
    public GUI(Main main) {
        setSize(400, 400); // fix window size
        setVisible(true); // make window visible
        addKeyListener(new KeyBoardInput()); // listen to keyboard input
        this.main = main;
    }

    /**
     * Displays the people records passed in.
     * 
     * @param database
     * @param index 
     */
    public void display(String[] database, int pos) {
        String msg = "";
        String foundName = database[pos];
        String name = foundName.replaceAll("[^a-zA-Z]", "");
        String phone = foundName.replaceAll("[^0-9]", "");
        msg += name + "    ";
        msg += phone + "\n";
        JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void displayAll(String[] database) {
        String msg = "";
        for(int i = 0; i < database.length; i++) {
          String foundName = database[i];
          String name = foundName.replaceAll("[^a-zA-Z]", "");
          String phone = foundName.replaceAll("[^0-9]", "");
          msg += name + "    ";
          msg += phone + "\n";
      }
      JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Reads a Person's name using a dialog box.
     * 
     * @return String - name read in
     */
    public String readName() {

        final String name = JOptionPane.showInputDialog("Enter the persons name");

        return (name);

    }

    /**
     * Invokes the appropriate method on the addressBook. When the user makes
     * their selection the Keyboard listener stores the selection value in data
     * member "choice" and then calls this method.
     */
    private void evaluateChoice() {
        switch (choice) {
        case 1:
            main.addPerson();
            break;
        case 2:
            main.deletePerson();
            break;
        case 3:
            main.findPerson();
            break;
        case 4:
            main.displayAll();
            break;
        case 5:
            System.exit(0);
            break;

        default:
            
        }
    }

    /**
     * Clears and draws the main menu on the window.
     * 
     * @param g
     *            Graphics - device context to allow drawing on this window
     */
    private void displayMenu(Graphics g) {
        Color c = this.getBackground();// colour to clear screen with
        g.setColor(c);// use that colour
        // colour in a rectangle the size of the window with that colour
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);// set colour to draw with now to black
        g.drawString("1) Add", 100, 100);
        g.drawString("2) Delete", 100, 120);
        g.drawString("3) Search", 100, 140);
        g.drawString("4) Display All", 100, 160);
        g.drawString("5) Exit", 100, 180);
    }

    /**
     * Displays the menu when window requires repainting.
     * 
     * @param g
     *            Graphics - device context for the window to draw on
     */
    public void paint(Graphics g) {
        displayMenu(g);
    }

    /**
     * Displays a message on the title bar of the window.
     * 
     * @param msg
     *            String - non-error message to display
     */
    public void displayMsg(String msg) {
        setTitle(msg);
    }

    /**
     * Displays an error message in a dialog box.
     * 
     * @param msg
     *            String - error msg to display
     */
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /*
     * KeyBoardInput.
     *
     * A private (no one else needs access to this class) inner class (this
     * class needs access to the GUI to handle user selections) that listens for
     * keys pressed.
     *
     */
    private class KeyBoardInput extends KeyAdapter {

        /**
         * Responds when a key is pressed on the keyboard.
         * 
         * @param e
         *            KeyEvent - key pressed and other information
         */
        public void keyTyped(KeyEvent e) {
            // set the "choice" data member of the outer class GUI
            // to get the integer value, get the character value of the key
            // pressed, make it a string and ask the Integer class to parse it
            try {
                choice = Integer.parseInt("" + e.getKeyChar());
                // if it wasn't an integer key pressed then make an invalid
                // choice
            } catch (Exception except) {
                choice = -1;// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call Main to perform task
        }
    }
}
