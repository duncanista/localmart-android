package mx.itesm.localmart.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_screen.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        button.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        textView2.setOnClickListener{
            startActivity(Intent(this, SignupScreen::class.java))
        }
    }
}
