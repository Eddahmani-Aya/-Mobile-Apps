package com.example.tp_12;

import android.content.Context;
import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "notes.txt";

    // Sauvegarder une note
    public static void saveNote(Context context, String title, String desc, String lang) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            String id = UUID.randomUUID().toString();
            writer.write(id + ";" + title + ";" + desc + ";" + lang);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lire toutes les notes
    public static List<DataClass> readNotes(Context context) {
        List<DataClass> list = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput("notes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    list.add(new DataClass(parts[0], parts[1], parts[2], parts[3]));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // Fichier n'existe pas encore, pas grave
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Supprimer une note
    public static void deleteNote(Context context, String id) {
        List<DataClass> notes = readNotes(context);
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            for (DataClass n : notes) {
                if (!n.getKey().equals(id)) {
                    writer.write(n.getKey() + ";" + n.getDataTitle() + ";" + n.getDataDesc() + ";" + n.getDataLang());
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mettre à jour une note
    public static void updateNote(Context context, String id, String newTitle, String newDesc, String newLang) {
        List<DataClass> notes = readNotes(context);
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            for (DataClass n : notes) {
                if (n.getKey().equals(id)) {
                    writer.write(id + ";" + newTitle + ";" + newDesc + ";" + newLang);
                } else {
                    writer.write(n.getKey() + ";" + n.getDataTitle() + ";" + n.getDataDesc() + ";" + n.getDataLang());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
