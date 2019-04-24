package com.mapache.sharedpreferences

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    val REQUEST_PERMISSION = 1
    val nameFile = "message.txt"
    val file = File(Environment.getExternalStorageDirectory().path, nameFile)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lectorStart = BufferedReader(InputStreamReader(FileInputStream(file)))
        tv_message.text = lectorStart.readLine()

        bt_save.setOnClickListener{
            var fos = OutputStreamWriter(FileOutputStream(file))
            fos.write(et_message.text.toString().trim())
            fos.flush()
            //fos.close()
        }

        bt_read.setOnClickListener{
            var lector = BufferedReader(InputStreamReader(FileInputStream(file)))
            tv_message.text = lector.readLine()
        }
    }

    override fun onStart() {
        super.onStart()
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_PERMISSION )
            }
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION )
        }
    }
}
