package sample.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveLoadFile {

    public static void save(String string) throws IOException {
        System.out.println("ermwk;g;oikwrglmi;wremg3w4'omikgikm'3w4gmk3w4mkgkmlw4    "+string);
        String name=string.substring(0,string.indexOf(","));

        string="s:"+string.substring(name.length());
        File file=new File("save\\"+name+".txt");
        file.createNewFile();

        FileWriter fileWriter = new FileWriter( file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(string);
        printWriter.close();



    }
}
