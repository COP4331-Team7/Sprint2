package com.team7.ConfigurableControls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;
/*
 * a class instance to be created when user specifies controls to change
 * uses Java Properties API to read/write controls to a file in root directory 'configPlayerTwo.properties'
*/
public class ConfigReader {

  private final String playerOneFile = "src/com/team7/ConfigurableControls/configPlayerOne.properties";
  private final String playerTwoFile = "src/com/team7/ConfigurableControls/configPlayerTwo.properties";

  private String currentFile;

  private final String defaultFileName = "src/com/team7/ConfigurableControls/defaultConfig.properties"; //location of prop file with all default values

 // public static void main(String[] args) {
 //   ConfigReader reader = new ConfigReader();

    //example usages:
  //  reader.printAllProperties();
  //  reader.getValueByKey("PlayerOneNorth");
  //  reader.changeValueByKey("PlayerOneNorth", "test");
  //  reader.resetToDefault();

  //}

  public ConfigReader() {

  }

  //prints value of key input
  private void getValueByKey(String player, String key){
    currentFile = (player.contains("One")) ? playerOneFile : playerTwoFile;

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(currentFile);
      prop.load(input);   // load a properties file
      System.out.println(key + ": " + prop.getProperty(key));   // get the property value and print it out

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  //changes value of key according to input
  public void changeValueByKey(String player, String key, String value){
    System.out.println("changing controls of " + player + " key: " + key + " value: " + value);

    currentFile = (player.contains("One")) ? playerOneFile : playerTwoFile;

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(currentFile);
      prop.load(input);   // load a properties file
      input.close();      //close input stream

      FileOutputStream out = new FileOutputStream(currentFile);  //create output to same file location
      prop.setProperty(key, value); //change property values
      prop.store(out, null);  //store output stream to 'overwrite' old file
      out.close();  //close

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  public HashMap<String, String> getAllControlsByPlayer(String player) {
    currentFile = (player.contains("One")) ? playerOneFile : playerTwoFile;
    HashMap<String, String> controls = new HashMap<>();

    Properties prop = new Properties();
    InputStream input = null;

    try {
      input = new FileInputStream(currentFile);
      prop.load(input);

      Enumeration<?> e = prop.propertyNames();
      while (e.hasMoreElements()) {
        String key = (String) e.nextElement();
        String value = prop.getProperty(key);
        controls.put(key, value);

      }
    }catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return controls;
  }

  //resets properties file to default values by overwriting with a different file
  public void resetToDefault(String player){
    System.out.println("resetting controls of " + player);

    currentFile = (player.contains("One")) ? playerOneFile : playerTwoFile;

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(defaultFileName); //load input with the DEFAULT values
      prop.load(input);
      input.close();

      FileOutputStream out = new FileOutputStream(currentFile);  //write to standard output with the default values
      prop.store(out, null);
      out.close();

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /*  private void printAllProperties() {

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(currentFile);
      prop.load(input);

      Enumeration<?> e = prop.propertyNames();  //get all properties
      while (e.hasMoreElements()) {             //iterate through list of properties key,value
        String key = (String) e.nextElement();
        String value = prop.getProperty(key);
        System.out.println("Key : " + key + ", Value : " + value);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }*/

}
