package uk.ac.solent.emacamera

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_gallery.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.ListView
import java.nio.file.Files.exists
import java.util.*
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat.startActivity
import java.io.ByteArrayOutputStream
class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        lateinit var helper: MyHelper

        ImageView = (ImageView) findViewById (R.id.img1);
        ImageView.setImageBitmap(getImageFromBLOB(object.getBlob(object.getColumnIndex("album"))));
        public static Bitmap getImageFromBLOB (byte[] mBlob){
            byte[] = mBlob;
            return BitmapFactory.decodeByteArray( 0, byte[].length);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
        }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.Gallery -> {
                var clickintent = Intent(this, GalleryActivity::class.java)
                startActivity(clickintent)
            }
            R.id.settings -> {
                var clickintent = Intent(this, SettingsActivity::class.java)
                startActivity(clickintent)
            }
            else ->
                super.onOptionsItemSelected(item)
        }
        return true;
    }


}


