package com.example.mzdoes.anesthesiacalc;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private FloatingActionButton nextButton;

    private Patient patient;

    private String nameHolder;
    private int weightHolder;
    private boolean withEpinephrineHolder, anesthesiaTypeHolder; //type: true = lidocaine, false = bupivocaine


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patient = new Patient();

        mPager = findViewById(R.id.viewPager);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        nextButton = findViewById(R.id.floatingActionButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + mPager.getCurrentItem());
                if (mPager.getCurrentItem() == 0) {
                    nameHolder = ((NameWeightFragment)page).getNameHolder();
                    weightHolder = ((NameWeightFragment)page).getWeightHolder();
                    Toast.makeText(MainActivity.this, nameHolder + ", " + weightHolder, Toast.LENGTH_SHORT).show();
                    mPager.setCurrentItem(getItem(+1), true);
                } else if (mPager.getCurrentItem() == 1) {
                    anesthesiaTypeHolder = ((AnesthesiaFragment)page).isType();
                    withEpinephrineHolder = ((AnesthesiaFragment)page).isEpinephrine();
                    patient.setName(nameHolder);
                    patient.setWeight(weightHolder);
                    patient.setAnesthesiaType(anesthesiaTypeHolder);
                    patient.setWithEpinephrine(withEpinephrineHolder);
                    Toast.makeText(MainActivity.this, patient.toString(), Toast.LENGTH_LONG).show();
                    mPager.setCurrentItem(getItem(+1), true);
                } else if (mPager.getCurrentItem() == 2) {
                    mPager.setCurrentItem(0, true);
                }

            }
        });
    }

    private int getItem(int i) {return mPager.getCurrentItem() + i;}

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return NameWeightFragment.newInstance();
                case 1: return AnesthesiaFragment.newInstance();
                case 2: return ResultsFragment.newInstance(patient);
                default: return NameWeightFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public String getNameHolder() {
        return nameHolder;
    }

    public void setNameHolder(String nameHolder) {
        this.nameHolder = nameHolder;
    }

    public int getWeightHolder() {
        return weightHolder;
    }

    public void setWeightHolder(int weightHolder) {
        this.weightHolder = weightHolder;
    }

    public boolean isWithEpinephrineHolder() {
        return withEpinephrineHolder;
    }

    public void setWithEpinephrineHolder(boolean withEpinephrineHolder) {
        this.withEpinephrineHolder = withEpinephrineHolder;
    }

    public boolean isAnesthesiaTypeHolder() {
        return anesthesiaTypeHolder;
    }

    public void setAnesthesiaTypeHolder(boolean anesthesiaTypeHolder) {
        this.anesthesiaTypeHolder = anesthesiaTypeHolder;
    }
}
