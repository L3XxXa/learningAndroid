package com.example.learningcaching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.File

class MainActivity : AppCompatActivity() {
    private val filename = "superfile"
    private lateinit var textToCache : EditText
    private lateinit var cachedText : TextView
    private lateinit var cacheButton : Button
    private lateinit var getCacheButton: Button
    private lateinit var cacheFile : File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        File.createTempFile(filename, null, applicationContext.cacheDir)
        cacheFile = File(applicationContext.cacheDir, filename)
        textToCache = findViewById(R.id.messageToCache)
        cachedText = findViewById(R.id.cachedText)
        cacheButton = findViewById(R.id.buttonToCache)
        cacheButton.setOnClickListener {
            cacheData()
        }
        getCacheButton = findViewById(R.id.getCache)
        getCacheButton.setOnClickListener {
            getCache()
        }
    }

    private fun cacheData() {
        cacheFile.writeText(textToCache.text.toString())
        Toast.makeText(applicationContext, "Successfully cached data", Toast.LENGTH_SHORT).show()
    }

    private fun getCache() {
        val textValue = java.lang.StringBuilder()
        cacheFile.forEachLine {
            textValue.append(it)
        }
        cachedText.text = textValue.toString()
    }


}