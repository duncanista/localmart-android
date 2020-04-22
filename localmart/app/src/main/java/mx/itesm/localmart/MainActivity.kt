package mx.itesm.localmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mx.itesm.localmart.categories.CategoriesScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTest.setOnClickListener {

            val intent = Intent(this, CategoriesScreen::class.java)
            startActivity(intent)
        }
    }



}
