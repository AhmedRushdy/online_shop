package com.example.yourdevices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yourdevices.models.Products;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminAddProducts extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 111;
    private Button addProduct;
    private EditText etName, etDescription, etPrice, discount;
    private String proName, proDescription;
    private float price;
    private ImageView proImage;
    private Intent i;
    String productCategory;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Uri imageUri;
    private StorageReference mStorageRef;
    StorageReference filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_products);

        addProduct = findViewById(R.id.add_product_btn);
        etName = findViewById(R.id.add_product_name);
        etPrice = findViewById(R.id.add_product_price);
        etDescription = findViewById(R.id.add_product_describtion);
        discount = findViewById(R.id.add_discount);
        proImage = findViewById(R.id.product_img);


        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        i = getIntent();
        productCategory = i.getStringExtra("111");
        proImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromAlbum();
            }
        });
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });
    }

    private void getImageFromAlbum() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

    }

    private void addProduct(Uri img) {

//        Map<String, Object> productMap = new HashMap<>();
//        productMap.put("product name", product.getProName().toString());
//        productMap.put("product description", product.getProDescribtion().toString());
//        productMap.put("product price", product.getPrice());
//        productMap.put("product image", product.getImage().toString());
            myRef.child("category").child(productCategory).push().setValue(new Products(proName, proDescription, img.toString()
                    , price));



    }

    private void validation() {
        proName = etName.getText().toString();
        proDescription = etDescription.getText().toString();
        price = Float.parseFloat(etPrice.getText().toString());

        if (proName.isEmpty()) {
            etName.setError("you should enter product name");
            if (proDescription.isEmpty())
                etDescription.setError("you should enter product Describtion");
            if (etPrice.getText().toString().isEmpty())
                etPrice.setError("you should enter product etPrice");

            if (imageUri == null)
                addProduct.setError("you should choose image");

        } else {

            Toast.makeText(this, proName + " " + proDescription + " " + price, Toast.LENGTH_SHORT).show();
            uploadImage();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            proImage.setImageURI(imageUri);

        } else {
            Toast.makeText(AdminAddProducts.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }

    }

    private void uploadImage() {

       filePath = mStorageRef.child(productCategory).child(System.currentTimeMillis()+".jpg");

        filePath.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }
                return filePath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
                    Uri downUri = task.getResult();
                    Toast.makeText(AdminAddProducts.this, ""+downUri, Toast.LENGTH_SHORT).show();
                    addProduct(downUri);
                }
            }
        });
    }
}