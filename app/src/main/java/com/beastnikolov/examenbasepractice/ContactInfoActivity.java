package com.beastnikolov.examenbasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactInfoActivity extends AppCompatActivity {
    Task task;
    DataBaseManager dataBaseManager;
    EditText editTaskName;
    CheckBox checkBoxComplete;
    TextView creationDate;
    LinearLayout linearLayout;
    TextView completionDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        dataBaseManager = new DataBaseManager(getApplicationContext());
        task = (Task) getIntent().getSerializableExtra("task");
        editTaskName = findViewById(R.id.editText_edit_taskName);
        creationDate = findViewById(R.id.editText_edit_creationDate);
        checkBoxComplete = findViewById(R.id.checkBox_edit_complete);
        linearLayout = findViewById(R.id.linearlayout_completiondates);
        completionDate = findViewById(R.id.textView_editText_edit_date);
        editTaskName.setText(task.getTaskName());
        creationDate.setText(String.valueOf(task.getTaskCreation()));
        if (task.getCompleted() == 0) {
            checkBoxComplete.setChecked(false);
            linearLayout.setVisibility(View.INVISIBLE);
        } else {
            checkBoxComplete.setChecked(true);
            linearLayout.setVisibility(View.VISIBLE);
            completionDate.setText(task.getCompletionDate().toString());
        }
    }

    /*public void goBackContactInformation(View v) {
        finish();
    }

     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                int completionStatus = 0;
                if (checkBoxComplete.isChecked()) {
                    completionStatus = 1;
                }
            //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Task updatedTask;
                if (checkBoxComplete.isChecked()) {
                    updatedTask = new Task(task.getTaskID(), editTaskName.getText().toString(), task.getTaskCreation(),new Date(),completionStatus);
                } else {
                    updatedTask = new Task(task.getTaskID(), editTaskName.getText().toString(), task.getTaskCreation(),null,completionStatus);
                }
                dataBaseManager.updateTask(updatedTask);
                finish();
                return true;
            case R.id.action_settings:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteContact(View v) {
        //dataBaseManager.deleteTask(task.getContactID());
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }
}
