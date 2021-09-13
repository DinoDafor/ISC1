package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        char[] buf = new char[256];

        ToolsForCipher tools = new ToolsForCipher();
        tools.writeInFile("text.txt", "hello my new perfect world z");


        CaesarCipher cipher = new CaesarCipher();
        tools.readFromFile("text.txt", buf);
        String st = new String(buf).trim();
        String cipheredMessage = cipher.cipher(st, 3);
        System.out.println(cipheredMessage);

        tools.writeInFile("text.txt", cipheredMessage);
        tools.readFromFile("text.txt", buf);
        st = new String(buf).trim();

        String decipheredSentence = cipher.decipher(st, 3);
        System.out.println(decipheredSentence);




    }
}
class ToolsForCipher {

    void writeInFile(String fileName, String text){

        try(FileWriter writer = new FileWriter(fileName, false))
        {
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    void readFromFile(String fileName, char[] buf){
        try(FileReader reader = new FileReader("text.txt"))
        {

            int c;
            while((c = reader.read(buf))>0){

                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


}
class CaesarCipher {
    String cipher(String message, int offset) {

        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';

                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;

                char newCharacter = (char) ('a' + newAlphabetPosition);

                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    String decipher(String message, int offset) {
        return cipher(message, 26 - (offset % 26));

    }
}
