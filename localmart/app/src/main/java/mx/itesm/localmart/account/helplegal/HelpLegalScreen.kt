package mx.itesm.localmart.account.helplegal

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_help_legal_screen.*
import mx.itesm.localmart.R

class HelpLegalScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_legal_screen)

        tvPrivacy.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://localmarto.web.app/"))
            startActivity(intent)
        }

        tvTermsAndConditions.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://localmarto.web.app/"))
            startActivity(intent)
        }

        btnSupport.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://localmarto.web.app/"))
            startActivity(intent)
        }
    }
}
