import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
/*
 * Author Or Oz:
 * a class instance to be created when user specifies controls to change
 * uses Java Properties API to read/write controls to a file in root directory 'config.properties'
*/
public class ConfigReader {

  private final String fileName = "config.properties";
  private final String defaultFileName = "defaultConfig.properties"; //location of prop file with all default values

  public static void main(String[] args) {
    ConfigReader reader = new ConfigReader();

    //example usages:
  //  reader.printAllProperties();
  //  reader.getValueByKey("PlayerOneNorth");
  //  reader.changeValueByKey("PlayerOneNorth", "test");
  //  reader.resetToDefault();

  }

  //prints value of key input
  private void getValueByKey(String key){

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(fileName);
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
  private void changeValueByKey(String key, String value){

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(fileName);
      prop.load(input);   // load a properties file
      input.close();      //close input stream

      FileOutputStream out = new FileOutputStream(fileName);  //create output to same file location
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

  private void printAllProperties() {

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(fileName);
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
  }

  //resets properties file to default values by overwriting with a different file
  private void resetToDefault(){

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(defaultFileName); //load input with the DEFAULT values
      prop.load(input);
      input.close();

      FileOutputStream out = new FileOutputStream(fileName);  //write to standard output with the default values
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


}
