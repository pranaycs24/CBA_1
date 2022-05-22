package com.can.book.anything.admin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class DataFetchAdapter extends FirestoreRecyclerAdapter<PersonalData, DataFetchAdapter.DataFetchHolder> {

    private Context context;

    public DataFetchAdapter(@NonNull FirestoreRecyclerOptions<PersonalData> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull DataFetchHolder holder, int position, @NonNull PersonalData model) {
        holder.texViewName.setText(model.getName());
        holder.textViewAdd.setText(model.getServiceArea());
        holder.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = model.getPhoneNumber();
//                Toast.makeText(context, model.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);// Uri.parse(model.getPhoneNumber(), phone, ))
                intent.setData(Uri.parse("tel:" + phoneNumber));
                context.startActivity(intent);
//                if (intent.resolveActivity(context.getPackageManager()) != null) {
//                    Toast.makeText(context, "TEL", Toast.LENGTH_SHORT).show();
//
//                }
            }
        });
    }

    @NonNull
    @Override
    public DataFetchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.data_info, parent, false);
        return new DataFetchHolder(v);
    }

    class DataFetchHolder extends RecyclerView.ViewHolder{
        TextView texViewName;
        TextView textViewAdd;
        ImageButton buttonCall;

        public DataFetchHolder(@NonNull View itemView) {
            super(itemView);
            texViewName  = itemView.findViewById(R.id.text_view_title);
            textViewAdd = itemView.findViewById(R.id.text_view_address);
            buttonCall = itemView.findViewById(R.id.button_call);

        }
    }

}
