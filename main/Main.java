package main;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {
    
  private String[] database;
  Scanner input;
  GUI ui;
  
  public Main() {
    database = new String[0];
    input = new Scanner(System.in);
  }
  public void add(final String name){
    String[] temp = new String[database.length +1];
    System.arraycopy(database, 0, temp, 0, database.length);
    temp[database.length] = name;
    database = temp;
  }
  public int search(final String name){
    String name2;
    int space=0;

    for (int pos=0; pos<database.length; pos++){
      Scanner extract = new Scanner(database[pos]);
      name2= extract.next();

      if (name.compareToIgnoreCase(name2)==0){
        return pos;
      }
    }
    return -1;
  }
  public void display(int pos){
    String name, phone;
    Scanner extract = new Scanner(database[pos]);
    name = extract.next();
    phone = extract.next();
    System.out.printf("%-20s%-15s\n", name, phone);
  }
  
  public void displayHeading(){
    String heading1 = "Name";
    String heading2 = "Phone";
    System.out.printf("%-20s%-15s\n", heading1, heading2);
  }
  
  public void displayAll(){
      ui.displayAll(database);
  }
  
  public boolean remove(final String name){
    int pos = search(name);
    if (pos >= 0){
      String[] temp = new String[database.length-1];
      System.arraycopy(database,0,temp, 0, pos);
      System.arraycopy(database, pos+1, temp, pos, database.length-pos-1);
      database = temp;
      return true;
    }
    return false;
  }

  public int getChoice(){
    int choice = 4;//default
    boolean done = false;
    while(!done){
      System.out.print("choice? ");
      try{
        choice = input.nextInt();
      }catch(Exception e){}
      if (choice >0 && choice <= 5)
        done = true;
      else
        System.out.println("\nYour choice is incorrect, please try again");
    }
    return choice;
  }

  public void addPerson(){
      final String name;
      final String phone;
      name = ui.readName();
      phone = JOptionPane.showInputDialog("Enter the persons phone number");
      add(name + " " + phone);
  }

  public void deletePerson(){
      final String name;
      name = ui.readName();
      if (!remove(name))
          ui.displayErrorMsg("Could not delete "+name);
      else
          ui.displayMsg(name + " was deleted successfully");
  }
  
  public void findPerson(){
      final String name;
      name = ui.readName();
      int pos;
      pos = search(name);
      if (pos >=0){
          ui.display(database, pos);
      } else {
          ui.displayErrorMsg("No such person");
      }
  }

  public void run(){
      ui = new GUI(this);
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.run();
  }
  
}
