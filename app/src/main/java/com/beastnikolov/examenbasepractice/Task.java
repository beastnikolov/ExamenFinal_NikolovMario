package com.beastnikolov.examenbasepractice;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private int taskID;
    private String taskName;
    private Date taskCreation;
    private Date completionDate;
    private int completed;

    public Task(int taskID, String taskName, Date taskCreation, Date completionDate, int completed) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskCreation = taskCreation;
        this.completionDate = completionDate;
        this.completed = completed;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public Date getTaskCreation() {
        return taskCreation;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public int getCompleted() {
        return completed;
    }
}
