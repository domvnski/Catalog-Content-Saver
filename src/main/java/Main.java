import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Path: "); //           C:/Users/domansma/IdeaProjects/demo
        String path = scanner.nextLine();

        File file = new File(path);
        Path filepath = file.toPath();
        File[] files = file.listFiles();
        BasicFileAttributes attributes = Files.readAttributes(filepath, BasicFileAttributes.class);
        String pattern = "yyyy-MM-dd, HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        System.out.println("Path content: ");
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        for (File f : files) {
            String formatted = simpleDateFormat.format(new Date(attributes.creationTime().toMillis()));
            object.put("Filename", f.getName());
            object.put("Size", f.length() + " bytes");
            object.put("Creation date", formatted);
            array.add(object);

            System.out.println(f.getName() + ", size: " + f.length() + " bytes, creation date: " + formatted + ";");
        }

        try (FileWriter fileWriter = new FileWriter("file.json")) {
            fileWriter.write(array.toJSONString());
            fileWriter.flush();
            System.out.println("\nSuccessfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + array);
        }
    }
}