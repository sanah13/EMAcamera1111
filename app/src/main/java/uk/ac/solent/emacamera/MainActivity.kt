package uk.ac.solent.emacamera
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import io.fotoapparat.Fotoapparat
import io.fotoapparat.configuration.CameraConfiguration
import io.fotoapparat.log.fileLogger
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.selector.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.provider.MediaStore
import android.util.Log
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    lateinit var helper: MyHelper
    lateinit var fotoapparat: Fotoapparat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fotoapparat = Fotoapparat(
                context = this,
                view = camera_view,
                lensPosition = back(),
                logger = loggers(
                        logcat(),
                        fileLogger(this)
                ),
                cameraErrorCallback = { error -> }
        )
        val cameraConfiguration = CameraConfiguration(
                pictureResolution = highestResolution(),
                previewResolution = highestResolution(),
                focusMode = firstAvailable(
                        continuousFocusPicture(),
                        autoFocus(),
                        fixed()
                ),
                flashMode = firstAvailable(
                        autoRedEye(),
                        autoFlash(),
                        torch(),
                        off()
                ),
                antiBandingMode = firstAvailable(
                        auto(),
                        hz50(),
                        hz60(),
                        none()
                ),
                jpegQuality = manualJpegQuality(90),
                sensorSensitivity = lowestSensorSensitivity(),
                frameProcessor = { frame -> }
        )
        helper = MyHelper(this)
        bt1.setOnClickListener {
            val photoResult = fotoapparat.takePicture()
            val loc: String = "${Environment.getExternalStorageDirectory().getAbsolutePath()}/DCIM/img${System.currentTimeMillis()}.jpg";
            photoResult.saveToFile(File(loc))
            val name = ""
            helper.addImage(name, loc)
            fun getBytes(bitmap: Bitmap): ByteArray {
                val stream = ByteArrayOutputStream()
                bitmap.compress(CompressFormat.PNG, 0, stream)
                return stream.toByteArray()
            }
            fun getImage(image: ByteArray): Bitmap {
                return BitmapFactory.decodeByteArray(image, 0, image.size)
            }
        }
    }
    public override fun onActivityResult(requestcode: Int, resultcode: Int, intent: Intent) {
        super.onActivityResult(requestcode, resultcode, intent)
        if (resultcode == Activity.RESULT_OK) {
            if (requestcode == 101) {

            }
        }
    }
    override fun onStart() {
        super.onStart()
        fotoapparat.start()

    }
    override fun onStop() {
        super.onStop()
        fotoapparat.stop()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
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