package edu.uncc.assignment04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment04.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM_USER = "USER";

    private User mParam;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = (User) getArguments().getSerializable(ARG_PARAM_USER);
        }
    }

    FragmentProfileBinding fragmentProfileBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
        fragmentProfileBinding.textViewUserName.setText(mParam.getName());
        fragmentProfileBinding.textViewUserAgeGroup.setText(mParam.getMood_value());
        switch (mParam.getMood_image_resource()) {
            case 0:
                fragmentProfileBinding.imageViewMood.setImageResource(R.drawable.not_well);
                fragmentProfileBinding.textViewUserMood.setText("Not Well");
                break;
            case 1:
                fragmentProfileBinding.imageViewMood.setImageResource(R.drawable.sad);
                fragmentProfileBinding.textViewUserMood.setText("Sad");
                break;
            case 2:
                fragmentProfileBinding.imageViewMood.setImageResource(R.drawable.ok);
                fragmentProfileBinding.textViewUserMood.setText("OK");
                break;
            case 3:
                fragmentProfileBinding.imageViewMood.setImageResource(R.drawable.good);
                fragmentProfileBinding.textViewUserMood.setText("Good");
                break;
            case 4:
                fragmentProfileBinding.imageViewMood.setImageResource(R.drawable.very_good);
                fragmentProfileBinding.textViewUserMood.setText("Very Good");
                break;

        }


        fragmentProfileBinding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goBack();
            }
        });
    }

    ProfileFragmentListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ProfileFragmentListener) context;
    }

    public interface ProfileFragmentListener{
        void goBack();
    }
}