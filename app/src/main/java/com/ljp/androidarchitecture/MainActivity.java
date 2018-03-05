package com.ljp.androidarchitecture;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ljp.androidarchitecture.fragments.UserProfileFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            UserProfileFragment userProfileFragment = new UserProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putString(UserProfileFragment.UID_KEY, "100");
            userProfileFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frameLayout, userProfileFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


}
