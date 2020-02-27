package com.beastnikolov.examenbasepractice;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Contact implements Serializable {
    private int contactID;
    private byte[] imageByteArray;
    private String contactName;
    private String contactNumber;

    public Contact(int contactID, byte[] imageByteArray, String contactName, String contactNumber) {
        this.contactID = contactID;
        this.imageByteArray = imageByteArray;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public int getContactID() {
        return contactID;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

}
