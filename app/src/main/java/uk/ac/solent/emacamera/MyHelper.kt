package uk.ac.solent.emacamera
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import android.content.ContentValues
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import android.graphics.Bitmap.CompressFormat




class MyHelper(ctx:Context) : SQLiteOpenHelper(ctx,"GalleryDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Photos (Id INTEGER PRIMARY KEY, ImageName VARCHAR(255), ImageLocation VARCHAR(255), album INTEGER, date VARCHAR(255), time VARCHAR(255) )")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS People")
        onCreate(db)
    }
    fun findImage(name: String): List<image> {
        val Photos = mutableListOf<image>()
        val db = getReadableDatabase()
        val cursor = db.rawQuery("SELECT * FROM Photos WHERE ImageName=?", arrayOf<String>(name))
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                val p = image(
                        cursor.getLong(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("loc")),
                        cursor.getLong(cursor.getColumnIndex("album")),
                        cursor.getLong(cursor.getColumnIndex("date")),
                        cursor.getLong(cursor.getColumnIndex("time")))
                Photos.add(p)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return Photos
    }
    fun addImage(name: String, loc: String): Long {
        val db = getWritableDatabase()
        val stmt = db.compileStatement("INSERT INTO Photos (ImageName, ImageLocation) VALUES (?,?)")
        stmt.bindString(1, name)
        stmt.bindString(2, loc)
        val id = stmt.executeInsert()
        return id
    }
    fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }
    }
    fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }


