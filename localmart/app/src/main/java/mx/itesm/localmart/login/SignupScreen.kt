package mx.itesm.localmart.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup_screen.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R

class SignupScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)

        btnCreate.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        tvLogin.setOnClickListener{
            startActivity(Intent(this, LoginScreen::class.java))
        }
    }
}
