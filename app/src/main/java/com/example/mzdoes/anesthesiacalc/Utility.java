package com.example.mzdoes.anesthesiacalc;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zeucudatcapua2 on 3/28/18.
 */

public class Utility {

    public static int getNextInt(String string) {
        Scanner s = new Scanner(string);
        return s.nextInt();
    }
}
