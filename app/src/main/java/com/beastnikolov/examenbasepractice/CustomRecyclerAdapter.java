package com.beastnikolov.examenbasepractice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MyHolder> {
    ArrayList<Contact> contactArrayList;
    Context context;

    public CustomRecyclerAdapter(ArrayList<Contact> contactArrayList, Context context) {
        this.contactArrayList = contactArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerAdapter.MyHolder holder, int position) {
        ImageUtilities imageUtilities = new ImageUtilities();

        final Contact tempContact = contactArrayList.get(position);
        holder.contactIcon.setImageBitmap(imageUtilities.ByteArrayToBitmap(tempContact.getImageByteArray()));
        holder.contactName.setText(tempContact.getContactName());
        holder.contactNumber.setText(tempContact.getContactNumber());
       /* holder.contactIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,tempContact.getContactName(),Toast.LENGTH_SHORT).show();
            }
        });
        */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ContactInfoActivity.class);
                intent.putExtra("contact",tempContact);
               // intent.putExtra("id",tempContact.getContactID());
                Toast.makeText(context,"SELECTED ID: " + tempContact.getContactID(),Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView contactIcon;
        TextView contactName;
        TextView contactNumber;
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            contactIcon = itemView.findViewById(R.id.imageView_contactIcon);
            contactName = itemView.findViewById(R.id.textView_ContactName);
            contactNumber = itemView.findViewById(R.id.textView_ContactNumber);
            this.itemView = itemView;
        }
    }
}


