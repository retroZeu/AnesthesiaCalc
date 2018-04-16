package com.example.mzdoes.anesthesiacalc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by zeucudatcapua2 on 4/16/18.
 */

public class Patient implements Parcelable, Serializable {
    private String name;
    private int weight;
    private boolean withEpinephrine;
    private boolean anesthesiaType; //true = lidocaine, false = bupivocaine

    public Patient() {
        name = "John";
        weight = 150;
        withEpinephrine = false;
        anesthesiaType = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isWithEpinephrine() {
        return withEpinephrine;
    }

    public void setWithEpinephrine(boolean withEpinephrine) {
        this.withEpinephrine = withEpinephrine;
    }

    public boolean isAnesthesiaType() {
        return anesthesiaType;
    }

    public void setAnesthesiaType(boolean anesthesiaType) {
        this.anesthesiaType = anesthesiaType;
    }

    public int calculateDose() {
        int allowableDose = 0;
        if (anesthesiaType) {
            if (withEpinephrine) {allowableDose = 7;} else {allowableDose = 3;}
        } else {allowableDose = 2;}

        //placeholder concentration
        int concentration = 10;

        return (allowableDose * (weight/10) * (1/concentration));
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", withEpinephrine=" + withEpinephrine +
                ", anesthesiaType=" + anesthesiaType +
                '}';
    }

    protected Patient(Parcel in) {
        name = in.readString();
        weight = in.readInt();
        withEpinephrine = in.readByte() != 0x00;
        anesthesiaType = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(weight);
        dest.writeByte((byte) (withEpinephrine ? 0x01 : 0x00));
        dest.writeByte((byte) (anesthesiaType ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };
}