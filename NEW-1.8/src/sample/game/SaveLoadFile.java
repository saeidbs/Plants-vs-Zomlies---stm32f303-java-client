package sample.game;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.utills.Utill;

import java.io.*;

public class SaveLoadFile {

    public static void save(String string) throws IOException {
        // System.out.println("ermwk;g;oikwrglmi;wremg3w4'omikgikm'3w4gmk3w4mkgkmlw4    "+string);
        String name = string.substring(0, string.indexOf(","));

        string = "s:" + string.substring(name.length());
        File file = new File("save\\" + name + ".txt");
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(string);
        printWriter.close();


    }

    public static String load(String name) {
        String line="",load="";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader("save\\" + name + ".txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                load+=line;
               // System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Save "+name+" not Found!!");
            alert.setTitle("ERROR");
            alert.setHeaderText("File Not Found");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(0, new Image("\\sample\\icon.png"));
            alert.show();
            System.out.println(
                    "Unable to open file '" +
                            name + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + name + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
            return load;
    }
}
