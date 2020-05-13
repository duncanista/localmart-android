package mx.itesm.localmart.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.itesm.localmart.R

class ProductDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)

        val product = intent.getSerializableExtra("Product") as? Product
    }
}
