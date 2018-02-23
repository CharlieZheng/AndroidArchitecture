package com.ljp.androidarchitecture;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ljp.androidarchitecture.fragments.UserProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(UserProfileFragment.UID_KEY, "100");
        userProfileFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frameLayout, userProfileFragment);
        fragmentTransaction.commit();
    }
}
