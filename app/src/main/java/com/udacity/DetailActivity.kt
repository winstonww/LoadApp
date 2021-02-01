package com.udacity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        val status = intent.getIntExtra(getString(R.string.status_id), 0)
        val color = intent.getIntExtra(getString(R.string.color_id), 0)
        val message = intent.getIntExtra(getString(R.string.message_id),0)

        // Check which radio button was clicked
        initializeViews(status, color, message)
    }

    fun initializeViews(statusValueText: Int, statusValueColor: Int, fileNameText: Int) {
        status_value.setText(resources.getString(statusValueText))
        status_value.setTextColor(resources.getColor(statusValueColor))
        file_name_value.setText(resources.getString(fileNameText))
    }

    fun onOkayButtonClicked(view: View) {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

}
