package com.iousave.www;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.iousave.www.fragments.FragmentBookSearch;
import com.iousave.www.fragments.FragmentVerificationCode;
import com.iousave.www.fragments.UserProfileFragment;

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

        Type type = (Type) getIntent().getSerializableExtra(MainActivity.class.getName());
        Fragment fragment;
        if (savedInstanceState == null) {
            switch (type) {
                case _0:

                    Bundle bundle = new Bundle();
                    bundle.putString(UserProfileFragment.UID_KEY, "100");
                    fragment = new UserProfileFragment();
                    fragment.setArguments(bundle);
                    break;
                case _1:
                    fragment = new FragmentBookSearch();
                    break;
                case _2:
                    fragment = new FragmentVerificationCode();
                    break;
                default:
                    fragment = new FragmentVerificationCode();
                    break;
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

    public enum Type {
        _0, _1, _2
    }
}
