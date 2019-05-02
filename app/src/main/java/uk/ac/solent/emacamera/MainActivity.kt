package uk.ac.solent.emacamera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import io.fotoapparat.Fotoapparat
import io.fotoapparat.configuration.CameraConfiguration
import io.fotoapparat.log.fileLogger
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.selector.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var fotoapparat: Fotoapparat ////change to nullable variable ----> runtime permissions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fotoapparat =  Fotoapparat(
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
        bt1.setOnClickListener{
            fotoapparat.takePicture().saveToFile( File("${Environment.getExternalStorageDirectory().getAbsolutePath()}DCIM/Camera/photos.jpg"))
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
}
