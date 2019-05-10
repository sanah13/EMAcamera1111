package uk.ac.solent.emacamera
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
class MainActivity : AppCompatActivity() {
    lateinit var helper: MyHelper
    lateinit var fotoapparat: Fotoapparat ////change to nullable variable ----> runtime permissions
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

            val loc : String = "${Environment.getExternalStorageDirectory().getAbsolutePath()}/DCIM/Camera/img${System.currentTimeMillis()}.jpg";
            photoResult.saveToFile(File(loc))
            val name    = ""
            helper.addImage(name, loc)
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