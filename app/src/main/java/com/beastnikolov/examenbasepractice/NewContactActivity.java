package com.beastnikolov.examenbasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class NewContactActivity extends AppCompatActivity {
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
            //contactPicture.buildDrawingCache();
          //  bitmap = Bitmap.createBitmap(contactPicture.getDrawingCache());
           // byte[] byteArray = imageUtilities.getBitMapByteArray(bitmap);
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.contact);
            byte[] byteArray = imageUtilities.getBitMapByteArray(icon);
            String newContactName = contactNameEDIT.getText().toString();
            String newContactNumber= contactNumberEDIT.getText().toString();
            dataBaseManager.insertContact(byteArray,newContactName,newContactNumber);
            finish();
        }
    }
}
