package com.beastnikolov.examenbasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class NewContactActivity extends AppCompatActivity {
    EditText newTaskNameEDIT;
    DataBaseManager dataBaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        dataBaseManager = new DataBaseManager(getApplicationContext());
        newTaskNameEDIT = findViewById(R.id.editText_newTask_name);
    }

    public void cancelNewTask(View view) {
        finish();
    }

    public void createnewTask(View view) {
        if (!(newTaskNameEDIT.getText().toString().isEmpty())) {
            String newTaskName = newTaskNameEDIT.getText().toString();
            dataBaseManager.insertTask(newTaskName);
            finish();
        }
    }

    /*public void choosePicture(View v) {
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
     */


}
