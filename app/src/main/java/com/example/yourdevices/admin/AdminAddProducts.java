package com.example.yourdevices.admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yourdevices.R;
import com.example.yourdevices.models.Products;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminAddProducts extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 111;
    private Button addProduct;
    private EditText etName, etQuantity, etDescribtion, etPrice, discount;
    private String proName, proDescribtion;
    private float price, proDiscount;
    int proQuantity;
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
        etQuantity = findViewById(R.id.add_product_quantity);
        discount = findViewById(R.id.add_discount);
        proImage = findViewById(R.id.product_img);
        etDescribtion = findViewById(R.id.add_product_description);

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

        String key = myRef.child("category").child(productCategory).push().getKey();
        myRef.child("category").child(productCategory).child(key).setValue(new Products(key, proName, proDescribtion, img.toString(), proQuantity, price,0));

    }

    private void validation() {
        proName = etName.getText().toString();
        price = Float.parseFloat(etPrice.getText().toString());
       // proDiscount = Float.parseFloat(discount.getText().toString());
        proQuantity = Integer.parseInt(etQuantity.getText().toString());
        proDescribtion = etDescribtion.getText().toString().trim();

        if (proName.isEmpty()) {
            etName.setError("you should enter product name");
            if (proQuantity == 0)
                etQuantity.setError("you should enter product quantity");
            if (etPrice.getText().toString().isEmpty())
                etPrice.setError("you should enter product Price");
            if (etDescribtion.getText().toString().isEmpty())
                etDescribtion.setError("you should enter product description");
            if (proDiscount == 0)
                addProduct.setError("you should enter discount");
            if (imageUri == null)
                addProduct.setError("you should choose image");

        } else {

            Toast.makeText(this, proName + " " + proQuantity + " " + price, Toast.LENGTH_SHORT).show();
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

        filePath = mStorageRef.child(productCategory).child(System.currentTimeMillis() + ".jpg");

        filePath.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return filePath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downUri = task.getResult();
                    Toast.makeText(AdminAddProducts.this, "" + downUri, Toast.LENGTH_SHORT).show();
                    addProduct(downUri);
                }
            }
        });
    }
}