package com.beastnikolov.examenbasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactInfoActivity extends AppCompatActivity {
    Contact contact;
    DataBaseManager dataBaseManager;
    ImageView contactIcon;
    TextView contactName;
    TextView contactNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        dataBaseManager = new DataBaseManager(getApplicationContext());
        contact = (Contact) getIntent().getSerializableExtra("contact");
        contactName = findViewById(R.id.textView_contactInfo_Name);
        contactNumber = findViewById(R.id.textView_contactInfo_Number);
        contactName.setText(contact.getContactName());
        contactNumber.setText(contact.getContactNumber());
    }

    public void goBackContactInformation(View v) {
        finish();
    }

    public void deleteContact(View v) {
        dataBaseManager.deleteContact(contact.getContactID());
        finish();
    }
}
