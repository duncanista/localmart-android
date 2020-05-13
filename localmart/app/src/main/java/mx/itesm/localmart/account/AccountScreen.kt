package mx.itesm.localmart.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity__account.*
import mx.itesm.localmart.R
import mx.itesm.localmart.account.communities.CommunitiesScreen
import mx.itesm.localmart.account.profile.ProfileScreen
import mx.itesm.localmart.account.settings.SettingsScreen

class AccountScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__account)

        imgArrow1.setOnClickListener{
            startActivity(Intent(this, ProfileScreen::class.java))
        }

        imgArrow2.setOnClickListener{
            startActivity(Intent(this, CommunitiesScreen::class.java))
        }
        imgArrow3.setOnClickListener{
            startActivity(Intent(this, SettingsScreen::class.java))
        }
    }
}
