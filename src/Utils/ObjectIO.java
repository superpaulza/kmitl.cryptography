package Utils;

import java.io.*;

public class ObjectIO {
    public void WriteObjectToFile(Object serObj, String path) {
        try {

            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object ReadObjectFromFile(String filepath) {
        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void writeBytesToFile(File file, byte[] bytes) {
        // Try block to check for exceptions
        try {
            // Initialize a pointer in file
            // using OutputStream
            OutputStream os = new FileOutputStream(file);
            // Starting writing the bytes in it
            os.write(bytes);
            // Display message onconsole for successful
            // execution
            System.out.println("Successfully"
                    + " byte inserted");
            // Close the file connections
            os.close();
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            // Display exception on console
            System.out.println("Exception: " + e);
        }
    }
}
