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
        val radioButtonId = intent.getIntExtra(getString(R.string.radio_button_id), 0)

        // Check which radio button was clicked
        when (radioButtonId) {
            R.id.radioButton1 -> {
                initializeViews("Success", R.color.colorPrimaryDark, R.string.glide)
            }

            R.id.radioButton2 -> {
                initializeViews("Fail", R.color.red, R.string.loadapp)
            }

            R.id.radioButton3 -> {
                initializeViews("Success", R.color.colorPrimaryDark, R.string.retrofit)
            }
            else -> {
                Log.i("DetailActivity", "Unknown radio button: ${radioButtonId}")
            }
        }
    }

    fun initializeViews(statusValueText: String, statusValueColor: Int, fileNameText: Int) {
        status_value.setText(statusValueText)
        status_value.setTextColor(resources.getColor(statusValueColor))
        file_name_value.setText(fileNameText)
    }

    fun onOkayButtonClicked(view: View) {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

}
