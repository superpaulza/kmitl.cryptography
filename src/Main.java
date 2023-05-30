import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import Data.CipherText;
import Elgamal.*;
import Key.KeyPair;
import Utils.ObjectIO;

public class Main {
    public static void main(String[] args) throws Exception {
        ElgamalGenerator generator = new ElgamalGenerator();
        ElgamalEncryption encryptor = new ElgamalEncryption();
        ElgamalDecryption decryptor = new ElgamalDecryption();
        ObjectIO objIO = new ObjectIO();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keySize = ");
        int keySpace = sc.nextInt();


        KeyPair key = generator.createKeyPair(keySpace);
        System.out.println(key.getPrivateKey().toString());
        System.out.println(key.getPublicKey().toString());


        CipherText cipher = encryptor.encrypt("Hello World, I'm Paul Hochaicharoen", key.getPublicKey());
        System.out.println(cipher.toString());

        String text = new String(decryptor.decrypt(cipher, key.getPrivateKey(), key.getPublicKey()));
        System.out.println("PlainText=" + text);

//        Path file = Paths.get("C:\\Users\\phochaic\\Desktop\\test\\Open-HEIC-on-Mac.jpg");

        // Converting the file into a byte array
        // using Files.readAllBytes() method
//        byte[] arr = Files.readAllBytes(file);
//
//        CipherText cipher = encryptor.encrypt(arr, key.getPublicKey());
//        objIO.WriteObjectToFile(cipher, "C:\\Users\\phochaic\\Desktop\\test\\Open-HEIC-on-Mac.encrypted");
//
//        CipherText data = (CipherText) objIO.ReadObjectFromFile("C:\\Users\\phochaic\\Desktop\\test\\Open-HEIC-on-Mac.encrypted");
//        objIO.writeBytesToFile(new File("C:\\Users\\phochaic\\Desktop\\test\\2-Open-HEIC-on-Mac.jpg"), decryptor.decrypt(data, key.getPrivateKey(), key.getPublicKey()));
//

//        // Printing the above byte array
//        System.out.println(Arrays.toString(arr));
    }
}