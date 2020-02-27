package com.beastnikolov.examenbasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class NewContactActivity extends AppCompatActivity {
    private static final int SELECTED_PIC = 1;
    EditText contactNameEDIT;
    EditText contactNumberEDIT;
    ImageView contactIcon;
    ImageUtilities imageUtilities;
    DataBaseManager dataBaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        imageUtilities = new ImageUtilities();
        dataBaseManager = new DataBaseManager(getApplicationContext());
        contactNameEDIT = findViewById(R.id.editText_newContact_name);
        contactNumberEDIT = findViewById(R.id.editText_newContact_number);
        contactIcon = findViewById(R.id.imageView_newContact_icon);
    }

    public void cancelNewContact(View view) {
        finish();
    }

    public void createNewContact(View view) {
        if (!(contactNumberEDIT.getText().toString().isEmpty() && contactNumberEDIT.getText().toString().isEmpty())) {
            contactIcon.buildDrawingCache();
            Bitmap icon = Bitmap.createBitmap(contactIcon.getDrawingCache());
            byte[] byteArray = imageUtilities.getBitMapByteArray(icon);
            //Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.contact);
            //byte[] byteArray = imageUtilities.getBitMapByteArray(icon);
            String newContactName = contactNameEDIT.getText().toString();
            String newContactNumber= contactNumberEDIT.getText().toString();
            dataBaseManager.insertContact(byteArray,newContactName,newContactNumber);
            finish();
        }
    }

    public void choosePicture(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PIC);
        //Picasso.get().load(new File(...)).into(imageView3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECTED_PIC && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            Picasso.get().load(imageUri).into(contactIcon);
        }
    }


}
