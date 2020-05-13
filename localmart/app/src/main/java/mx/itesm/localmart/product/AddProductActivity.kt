package mx.itesm.localmart.product

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_add_product.*
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth
import mx.itesm.localmart.categories.Category
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class AddProductActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1234

    //Firebase
    private var firestore = FirebaseFirestore.getInstance()
    private val Auth: Auth = Auth()

    internal var filepath: Uri? = null
    var storage = FirebaseStorage.getInstance()
    var storageReference = storage!!.reference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        var categoryOptions = Category.arrCategories.map { it.name }.toTypedArray()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryOptions)

        spinnerCategories.adapter = arrayAdapter

        spinnerCategories.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO require category
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //TODO upload selected category to firebase
            }
        }

        btnImage.setOnClickListener {
            fileChooser()
        }

        btnUpload.setOnClickListener {
            val currentUser = Auth.fbAuth?.currentUser
            val userUid = currentUser?.uid!!
            val productName = etName.text.toString()
            val productDescription = etDescription.text.toString()
            val price = etPrice.text.toString().toFloat()

            val imageUri = uploadImage()
            val newProduct = NewProduct(userUid, productName, productDescription, false, imageUri, price)

            firestore.collection("products")
                .add(newProduct)
                .addOnSuccessListener { uid ->

                }
                .addOnFailureListener { e ->

                }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST &&
                resultCode == Activity.RESULT_OK &&
                data != null && data.data != null){

            filepath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
                imgAddProduct!!.setImageBitmap(bitmap)
            } catch (e:IOException){
                e.printStackTrace()
            }

        }
    }

    private fun uploadImage(): String{
        if (filepath != null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            val imageRef = storageReference!!.child("productImages/" + UUID.randomUUID().toString())
            imageRef.putFile(filepath!!)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "File uploaded", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Failed to upload", Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {taskSnapShot->
                    val progress = 100.0 * taskSnapShot.bytesTransferred/taskSnapShot.totalByteCount
                    progressDialog.setMessage("Uploaded " + progress.toInt()+"%...")
                }
            return imageRef.toString()
        }
        return "no image"
    }

    private fun fileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), PICK_IMAGE_REQUEST)

    }

}

data class NewProduct(var seller: String, var name: String, var description: String, var sold: Boolean, var image: String, var price: Float)
