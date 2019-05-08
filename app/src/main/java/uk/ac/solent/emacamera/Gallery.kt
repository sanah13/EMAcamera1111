package uk.ac.solent.emacamera

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap
import android.os.Environment
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_gallery.*
import uk.ac.solent.emacamera.R.id.bt1
import java.io.File

class Gallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        bt2.setOnClickListener {
            val clickintent = Intent(this, MainActivity::class.java)
            startActivity(clickintent)
        }
        val imageView = findViewById<ImageView>(R.id.img1)
        val imgResId = R.drawable.ic_launcher_background
        val img = imgResId
        imageView.setImageResource(imgResId)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.Gallery -> {
                var clickintent = Intent(this, Gallery::class.java)
                startActivity(clickintent)
            }
            R.id.settings -> {
                var clickintent = Intent(this, Gallery::class.java)
                startActivity(clickintent)
            }
            else ->
                super.onOptionsItemSelected(item)
        }
        return true;

    }

}

