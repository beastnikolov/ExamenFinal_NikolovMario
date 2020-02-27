package com.beastnikolov.examenbasepractice;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    DataBaseManager dataBaseManager;
    ArrayList<Contact> contactArrayList;
    Contact contact;
    RecyclerView recyclerView;
    CustomRecyclerAdapter customRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBaseManager = new DataBaseManager(getApplicationContext());
        contactArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewContactActivity.class);
                startActivity(intent);
            }
        });


        /*
        DataBaseManager dataBaseManager = new DataBaseManager(this.getApplicationContext());
        ImageUtilities imageUtilities = new ImageUtilities();
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.contact);
        byte[] imagetest = imageUtilities.getBitMapByteArray(icon);
        dataBaseManager.insertContact(imagetest,"Mario","692622437");
        */

        fillRecyclerView();

        customRecyclerAdapter = new CustomRecyclerAdapter(contactArrayList,MainActivity.this);
        recyclerView.setAdapter(customRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                dataBaseManager.deleteContact(contactArrayList.get(viewHolder.getAdapterPosition()).getContactID());
                fillRecyclerView();
                customRecyclerAdapter.notifyDataSetChanged();
            }
        });

        helper.attachToRecyclerView(recyclerView);

    }

    public void fillRecyclerView() {
        contactArrayList.clear();
        Cursor cursor = dataBaseManager.getAllContacts();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            byte[] imageByteArray = cursor.getBlob(1);
            String name = cursor.getString(2);
            String phoneNumber = cursor.getString(3);
            contact = new Contact(id,imageByteArray,name,phoneNumber);
            contactArrayList.add(contact);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            dataBaseManager.deleteAllContacts();
            Toast.makeText(getApplicationContext(),"Deleted all contacts",Toast.LENGTH_SHORT).show();
            fillRecyclerView();
            customRecyclerAdapter.notifyDataSetChanged();
        }
        if (id == R.id.action_dummies) {
            dataBaseManager.createDummies(getResources());
            Toast.makeText(getApplicationContext(),"Created Dummy Contacts",Toast.LENGTH_SHORT).show();
            fillRecyclerView();
            customRecyclerAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        fillRecyclerView();
        customRecyclerAdapter.notifyDataSetChanged();
    }

}
