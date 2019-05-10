package uk.ac.solent.emacamera
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_gallery.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap



class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        bt2.setOnClickListener {
            val clickintent = Intent(this, MainActivity::class.java)
            startActivity(clickintent)
        }
        val imageView = findViewById<ImageView>(R.id.img1)

        val file = "/document/primary:DCIM/photo1557497431023.jpg"
        val myBitmap = BitmapFactory.decodeFile (file.toAbsolutePath())

        val myImage = findViewById(R.id.imageviewTest) as ImageView

        myImage.setImageBitmap(myBitmap)

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
                var clickintent = Intent(this, GalleryActivity::class.java)
                startActivity(clickintent)
            }
            R.id.settings -> {
                var clickintent = Intent(this, GalleryActivity::class.java)
                startActivity(clickintent)
            }
            else ->
                super.onOptionsItemSelected(item)
        }
        return true;
    }

}

