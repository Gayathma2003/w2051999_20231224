package lk.gayathma.oop.coursework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String Log_File = "logs.txt";

    public static void log(String message){
        String timeStampedMessage = LocalDateTime.now() + ": "+message;
        System.out.println(timeStampedMessage);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Log_File, true))){
            writer.write(timeStampedMessage);
            writer.newLine();
        }catch (IOException e){
            System.out.println("Failed writing to log file: " +e.getMessage());
        }
    }
}
