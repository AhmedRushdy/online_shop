package com.example.yourdevices.ui;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;
import com.example.yourdevices.adapters.CarAdapter;
import com.example.yourdevices.models.Order;
import com.example.yourdevices.models.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference ,orderReferance;
    FirebaseUser currentUser;
    private RecyclerView carProducts;
    private CarAdapter carAdapter;
    private ArrayList<Products> productsList;
    private Button confirmProcess, confirmBottomSheet, deleteBottomSheet;
    private float totalPrice = 0;
    TextView total;
    String deletedItem = null;
    ImageView ivConfirmed;
    Order order;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        firebaseInitiation();
        key = FirebaseAuth.getInstance().getCurrentUser().getUid();

        carProducts = findViewById(R.id.car_rv);
        confirmProcess = findViewById(R.id.confirm_process);
        ivConfirmed = findViewById(R.id.iv_confirm);

        total = findViewById(R.id.total_price);
        productsList = new ArrayList<>();
        carProducts.setHasFixedSize(true);
        confirmProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CartActivity.this
                        , R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.bottom_sheet_layout, (ConstraintLayout) findViewById(R.id.bottom_sheet));
                bottomSheetDialog.setContentView(bottomSheetView);
                confirmBottomSheet = bottomSheetDialog.findViewById(R.id.confirm);
                confirmBottomSheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendToOrder();
                        databaseReference.child(currentUser.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(CartActivity.this, "Confirmed successfully", Toast.LENGTH_SHORT).show();
                                    carProducts.setVisibility(View.INVISIBLE);
                                    ivConfirmed.setImageDrawable(getResources().getDrawable(R.drawable.ic_delivery_cuate));
                                    ivConfirmed.setVisibility(View.VISIBLE);
                                    bottomSheetDialog.dismiss();


                                }
                            }
                        });
                    }

                });
                deleteBottomSheet = bottomSheetDialog.findViewById(R.id.delete);
                deleteBottomSheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseReference.child(currentUser.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(CartActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                                    carProducts.setVisibility(View.INVISIBLE);
                                    ivConfirmed.setImageDrawable(getResources().getDrawable(R.drawable.ic_delete_cart));
                                    ivConfirmed.setVisibility(View.VISIBLE);
                                    bottomSheetDialog.dismiss();


                                }
                            }
                        });
                    }
                });
                bottomSheetDialog.show();
            }
        });


        carProducts.setLayoutManager(new LinearLayoutManager(this));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        carProducts.addItemDecoration(dividerItemDecoration);

        databaseReference.child(currentUser.getUid()).

                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot != null) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Products products = dataSnapshot.getValue(Products.class);
                                productsList.add(products);
                                totalPrice+=products.getPrice();

                            }

                            total.setText(String.valueOf(totalPrice));
                            order = new Order(totalPrice,productsList);

                            carAdapter = new CarAdapter(getApplicationContext(), productsList);
                            carAdapter.notifyDataSetChanged();
                            carProducts.setAdapter(carAdapter);
                        }
                        else {
                            ivConfirmed.setImageDrawable(getResources().getDrawable(R.drawable.ic_delete_cart));
                            ivConfirmed.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(carProducts);


    }

    final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
//                    if (productsList.size() > 0) {
//                        productsList.remove(position);
//                        deletedItem = productsList.get(position).getId();
//                        deleteFromFirebase(deletedItem);
//                        carAdapter.notifyItemRemoved(position);
//                    }
                    productsList.remove(position);

                    break;
                case ItemTouchHelper.RIGHT:
//                    if (productsList.size() > 0) {
//                        productsList.remove(position);
//                        deletedItem = productsList.get(position).getId();
//                        deleteFromFirebase(deletedItem);
//                        carAdapter.notifyItemRemoved(position);
//                    }
                    productsList.remove(position);

                    break;
                default:
                    return;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorRed))
                    .addSwipeRightActionIcon(R.drawable.ic_delete)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorRed))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


    private void firebaseInitiation() {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("cart");

    }

    private void deleteFromFirebase(String deletedItem) {
        databaseReference.child(currentUser.getUid()).child(deletedItem).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CartActivity.this, "Deleted from Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendToOrder(){
        orderReferance =firebaseDatabase.getReference().child("orders");
        orderReferance.child(currentUser.getUid()).setValue(order);

    }
}