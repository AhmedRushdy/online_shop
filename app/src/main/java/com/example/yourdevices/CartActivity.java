package com.example.yourdevices;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.models.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    DatabaseReference databaseReference;
    FirebaseUser currentUser;
    private RecyclerView carProducts;
    private CarAdapter carAdapter;
    private ArrayList<Products> productsList;
    private View view;
    private float totalPrice = 0;
    TextView total;
    String deletedItem = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        firebaseInitiation();
        carProducts = findViewById(R.id.car_rv);
        carProducts.setLayoutManager(new LinearLayoutManager(this));

        final String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
        total = findViewById(R.id.total_price);
        productsList = new ArrayList<>();
        carProducts.setHasFixedSize(true);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        carProducts.addItemDecoration(dividerItemDecoration);

        databaseReference.child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Products products = dataSnapshot.getValue(Products.class);
                        productsList.add(products);

                    }
                    carAdapter = new CarAdapter(getApplicationContext(), productsList);
                    carAdapter.notifyDataSetChanged();
                    carProducts.setAdapter(carAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        FirebaseRecyclerOptions<Products> options =
//                new FirebaseRecyclerOptions.Builder<Products>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference()
//                                .child("cart").child(key), Products.class)
//                        .build();
//        for (Products options1 : options.getSnapshots()) {
//            totalPrice += options1.getPrice();
//            Toast.makeText(this, totalPrice + "", Toast.LENGTH_SHORT).show();
//            total.setText("" + totalPrice);
//        }
//        Button button = findViewById(R.id.confirm_process);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        carAdapter = new CarAdapter(options);
//        carProducts.setAdapter(carAdapter);


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
                    if (productsList.size() > 0) {
                        productsList.remove(position);
                        deletedItem = productsList.get(position).getId();
                        deleteFromFirebase(deletedItem);
                        carAdapter.notifyItemRemoved(position);
                    }
                    break;
                case ItemTouchHelper.RIGHT:
                    if (productsList.size() > 0) {
                        productsList.remove(position);
                        deletedItem = productsList.get(position).getId();
                        deleteFromFirebase(deletedItem);
                        carAdapter.notifyItemRemoved(position);
                    }
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

    @Override
    protected void onStart() {
        super.onStart();
//        carAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        carAdapter.stopListening();

    }

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
}