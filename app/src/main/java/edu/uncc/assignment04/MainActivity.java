// Assignment 4
// MainActivity.java
// Mihir Phatak and Aniket Shendre - Group 3

package edu.uncc.assignment04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UsersFragment.UserFramgmentListener, AddUserFragment.AddUserFragmentListener, SelectAgeGroupFragment.SelectAgeGroupListener, SelectMoodFragment.SelectMoodListener, ProfileFragment.ProfileFragmentListener
{

    ArrayList<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.rootView, new UsersFragment(), "users-fragment").commit();
    }

    @Override
    public void gotoAddUserInfo() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new AddUserFragment(), "add-user-fragment").addToBackStack(null).commit();
    }

    @Override
    public void gotoUserProfile(User user) {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, ProfileFragment.newInstance(user)).addToBackStack(null).commit();
    }

    @Override
    public void gotoSelectAge() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new SelectAgeGroupFragment()).addToBackStack(null).commit();
    }

    @Override
    public void gotoSelectMood() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new SelectMoodFragment()).addToBackStack(null).commit();
    }

    @Override
    public void submitUser(User user) {
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if(usersFragment != null) {
            usersFragment.addUser(user);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void gotoAddUser(String ageGroup) {
        AddUserFragment addUserFragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (addUserFragment != null) {
            addUserFragment.setAge(ageGroup);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancel() {
        AddUserFragment addUserFragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (addUserFragment != null) {
            addUserFragment.setAge(null);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void selectMood(int value) {
        AddUserFragment addUserFragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (addUserFragment != null) {
            addUserFragment.setValue(value);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void moodCancel() {
        AddUserFragment addUserFragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (addUserFragment != null) {
            addUserFragment.setValue(-1);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }
}