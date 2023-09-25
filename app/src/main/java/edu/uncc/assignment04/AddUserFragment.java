package edu.uncc.assignment04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import edu.uncc.assignment04.databinding.FragmentAddUserBinding;

public class AddUserFragment extends Fragment {

    public String age;

    public int value = -1;

    public void setAge(String age) {
        this.age = age;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddUserBinding fragmentAddUserBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAddUserBinding = FragmentAddUserBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return fragmentAddUserBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add User");

        if (age == null) {
            fragmentAddUserBinding.textViewAgeGroup.setText("N/A");
        } else {
            fragmentAddUserBinding.textViewAgeGroup.setText(age);
        }

        switch (value) {
            case 0:
                fragmentAddUserBinding.imageViewMood.setImageResource(R.drawable.not_well);
                fragmentAddUserBinding.textViewMood.setText("Not Well");
                break;
            case 1:
                fragmentAddUserBinding.imageViewMood.setImageResource(R.drawable.sad);
                fragmentAddUserBinding.textViewMood.setText("Sad");
                break;
            case 2:
                fragmentAddUserBinding.imageViewMood.setImageResource(R.drawable.ok);
                fragmentAddUserBinding.textViewMood.setText("Okay");
                break;
            case 3:
                fragmentAddUserBinding.imageViewMood.setImageResource(R.drawable.good);
                fragmentAddUserBinding.textViewMood.setText("Good");
                break;
            case 4:
                fragmentAddUserBinding.imageViewMood.setImageResource(R.drawable.very_good);
                fragmentAddUserBinding.textViewMood.setText("Very Good");
                break;
            default:
                fragmentAddUserBinding.imageViewMood.setImageResource(0);
                fragmentAddUserBinding.textViewMood.setText("N/A");
        }

        fragmentAddUserBinding.buttonSelectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoSelectAge();
            }
        });

        fragmentAddUserBinding.buttonSelectMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoSelectMood();
            }
        });

        fragmentAddUserBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fragmentAddUserBinding.editTextText.getText().toString();
                String age = fragmentAddUserBinding.textViewAgeGroup.getText().toString();

                if (name == null || name.length() == 0) {
                    Toast.makeText(getActivity(), "Please enter name.", Toast.LENGTH_SHORT).show();
                } else if (age.equals("N/A") || age == null) {
                    Toast.makeText(getActivity(), "Please select age group.", Toast.LENGTH_SHORT).show();
                } else if (value == -1) {
                    Toast.makeText(getActivity(), "Please select mood value.", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, age, value);
                    mListener.submitUser(user);
                }
            }
        });
    }

    AddUserFragmentListener mListener;

    interface AddUserFragmentListener {
        void gotoSelectAge();

        void gotoSelectMood();

        void submitUser(User user);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        mListener = (AddUserFragmentListener) context;
        super.onAttach(context);
    }
}