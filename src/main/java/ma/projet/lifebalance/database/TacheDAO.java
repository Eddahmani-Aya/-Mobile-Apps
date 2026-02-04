package ma.projet.lifebalance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ma.projet.lifebalance.models.Tache;

import java.util.ArrayList;

public class TacheDAO {
    private SQLiteDatabase db;
    private final DatabaseHelper helper;

    public TacheDAO(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        if (db == null || !db.isOpen()) {
            db = helper.getWritableDatabase();
        }
    }

    public void close() {
        helper.close();
    }

    public void insertTache(Tache tache) {
        ContentValues values = new ContentValues();
        values.put("titre", tache.getTitre());
        values.put("description", tache.getDescription());
        values.put("date", tache.getDate());
        values.put("statut", tache.getStatut());
        db.insert("Tache", null, values);
    }

    public ArrayList<Tache> getAllTaches() {
        ArrayList<Tache> liste = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Tache", null);
        if (cursor.moveToFirst()) {
            do {
                Tache t = new Tache(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                t.setId(cursor.getInt(0));
                liste.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return liste;
    }

    public void updateTache(Tache tache) {
        ContentValues values = new ContentValues();
        values.put("titre", tache.getTitre());
        values.put("description", tache.getDescription());
        values.put("date", tache.getDate());
        values.put("statut", tache.getStatut());
        db.update(
                "Tache",
                values,
                "id = ?",
                new String[]{String.valueOf(tache.getId())}
        );
    }

    public void deleteTache(int id) {
        db.delete(
                "Tache",
                "id = ?",
                new String[]{String.valueOf(id)}
        );
    }
}
