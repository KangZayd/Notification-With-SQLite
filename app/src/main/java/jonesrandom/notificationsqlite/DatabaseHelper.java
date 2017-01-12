package jonesrandom.notificationsqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NAMA_DATABASE = "Notif";
    public static final String NAMA_TABEL = "tabel_notif";

    public static final String ROW_ID = "id";
    public static final String ROW_TITLE = "tittle";
    public static final String ROW_MESSAGE = "message";

    public static final String QUERY = "CREATE TABLE " + NAMA_TABEL + "( "
            + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ROW_TITLE + " TEXT,"
            + ROW_MESSAGE + " TEXT)";

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, NAMA_DATABASE, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NAMA_TABEL);
    }

    public void dummy() {

        Cursor cur = db.rawQuery("SELECT * FROM " + NAMA_TABEL, null);

        if (cur.getCount() < 1) {

            List<ModelData> data = new ArrayList<>();
            data.add(new ModelData(1, "Ini Tittle Notif ", "Hai Ini Pesan Notif "));

            ContentValues v = new ContentValues();

            for (int i = 0; i < 10; i++) {

                int ii = i + 1;
                v.put(ROW_TITLE, data.get(0).getTittle() + ii);
                v.put(ROW_MESSAGE, data.get(0).getMessage() + ii);

                db.insert(NAMA_TABEL, null, v);
            }

        }

        cur.close();

    }

    public ModelData getDataNotification(String Tittle) {

        Cursor cur = db.rawQuery("SELECT * FROM " + NAMA_TABEL + " WHERE " + ROW_TITLE + " ='" + Tittle + "'", null);
        cur.moveToFirst();
        ModelData data = new ModelData();
        data.setId(cur.getInt(0));
        data.setTittle(cur.getString(1));
        data.setMessage(cur.getString(2));

        cur.close();
        return data;
    }

    public List<ModelData> getList() {

        List<ModelData> data = new ArrayList<>();

        Cursor cur = db.rawQuery("SELECT * FROM " + NAMA_TABEL, null);
        if (cur.moveToFirst()) {
            do {
                data.add(new ModelData(cur.getInt(0), cur.getString(1), cur.getString(2)));
            } while (cur.moveToNext());
        }

        cur.close();
        return data;
    }

}
