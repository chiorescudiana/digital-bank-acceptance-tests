package wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static void main(String[] args) {

    }
    private static Properties properties;


    //static initializer run the block only once for the whole project
    //instance initializers run the block once for every object  creation from the class
    static {
        //filePath -> the directory of yor properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //this is a class that enables you to read files
        //it throws a checked exception
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);

        } catch (IOException e) {
            System.out.println("File not found");
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //System.out.println(properties.get("my_name"));
        // System.out.println(properties.get("browser"));
        //System.out.println(properties.get("environment"));

    }

    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);

    }
}


