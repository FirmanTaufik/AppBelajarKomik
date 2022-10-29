package com.app.appbelajarkomik.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.adapter.rvAdapterBookmark;
import com.app.appbelajarkomik.model.BookmarkModel;
import com.app.appbelajarkomik.utils.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {
    String TAG ="BookmarkActivityTAG";
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        getData();
    }

    private void getData() {
        mDatabase.child("bookmark")
                .child(Constant.getId(this))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<BookmarkModel> bookmarkModels = new ArrayList<>();
                        bookmarkModels.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            BookmarkModel bookmarkModel = dataSnapshot.getValue(BookmarkModel.class);
                            bookmarkModels.add(bookmarkModel);
                        }
                        recyclerView.setAdapter(new rvAdapterBookmark(BookmarkActivity.this, bookmarkModels));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}