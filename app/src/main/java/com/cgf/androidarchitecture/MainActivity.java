package com.cgf.androidarchitecture;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cgf.androidarchitecture.fragments.FragmentBookSearch;
import com.cgf.androidarchitecture.fragments.UserProfileFragment;

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

        boolean type = getIntent().getBooleanExtra(MainActivity.class.getName(), false);
        Fragment fragment;
        if (savedInstanceState == null) {
            if (type) {
                Bundle bundle = new Bundle();
                bundle.putString(UserProfileFragment.UID_KEY, "100");
                fragment = new UserProfileFragment();
                fragment.setArguments(bundle);
            } else {
                fragment = new FragmentBookSearch();
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


}
