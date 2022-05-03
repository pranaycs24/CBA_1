package com.can.book.anything.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FetchData extends AppCompatActivity {
    private TextView textView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "FetchData_1";
    private CollectionReference notebookRef;
    private ListenerRegistration noteListener;
    private DataFetchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        textView = findViewById(R.id.textView);
//        notebookRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                String s = "";
//                Log.d(TAG, "Hello");
//                for(QueryDocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots){
//                    PersonalData data = queryDocumentSnapshot.toObject(PersonalData.class);
//
//                    String name = data.getName();
//                    String phone = data.getPhoneNumber();
//                    String serviceArea = data.getServiceArea();
//                    String address = data.getAddress();
//                    s += name +"\n" +phone+"\n" + address + "\n" +serviceArea+ "\n\n";
//                    Log.d(TAG, s);
//                }
//                textView.setText(s);
//            }
//        });

        setUpRecycleView();

    }
    private void setUpRecycleView() {
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        textView.setText(from);
        notebookRef = db.collection(from);
        Query query = notebookRef.orderBy("serviceArea", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<PersonalData> options = new FirestoreRecyclerOptions.Builder<PersonalData>()
                .setQuery(query, PersonalData.class)
                .build();
        adapter = new DataFetchAdapter(options, FetchData.this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
//        noteListener = notebookRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (error != null) {
//                    return;
//                }
//
//                String s = "";
//
//                for (QueryDocumentSnapshot documentSnapshot : value) {
//                    PersonalData note = documentSnapshot.toObject(PersonalData.class);
//                    note.setDocumentId(documentSnapshot.getId());
//
//                    String documentId = note.getDocumentId();
//                    String name = note.getName();
//                    String phone = note.getPhoneNumber();
//                    String serviceArea = note.getServiceArea();
//                    String address = note.getAddress();
//                    s += name +"\n" +phone+"\n" + address + "\n" +serviceArea+ "\n\n";
//                    Log.d(TAG, s);
//                }
//
//                textView.setText(s);
//            }
//        });
    }

    @Override
    protected void onStop() {
        super.onStop();
//        noteListener.remove();
        adapter.startListening();
    }
}