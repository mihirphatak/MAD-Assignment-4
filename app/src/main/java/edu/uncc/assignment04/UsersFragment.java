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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.assignment04.databinding.FragmentUsersBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    public List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    UsersAdapter adapter;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public UsersFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment UsersFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static UsersFragment newInstance(String param1, String param2) {
//        UsersFragment fragment = new UsersFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        User one = new User("Mihir", "25-34 years old", 4);
        User two = new User("Aniket", "18-24 years old", 3);
        users.add(one);
        users.add(two);
    }

    FragmentUsersBinding fragmentUsersBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUsersBinding = FragmentUsersBinding.inflate(inflater, container, false);
        return fragmentUsersBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Users");
        adapter = new UsersAdapter(getActivity(), users);
        fragmentUsersBinding.listView.setAdapter(adapter);

        fragmentUsersBinding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoAddUserInfo();
            }
        });

        fragmentUsersBinding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.clear();
                adapter.notifyDataSetChanged();
            }
        });

        fragmentUsersBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = users.get(i);
                mListener.gotoUserProfile(user);
            }
        });
    }

    UserFramgmentListener mListener;

    interface UserFramgmentListener {
        void gotoAddUserInfo();

        void gotoUserProfile(User user);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UserFramgmentListener) context;
    }

    class UsersAdapter extends ArrayAdapter<User> {

        public UsersAdapter(@NonNull Context context, @NonNull List<User> objects) {
            super(context, R.layout.list_item_user, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_user, parent, false);
            }

            TextView textViewUserName = convertView.findViewById(R.id.textViewUserName);
            TextView textViewUserAgeGroup = convertView.findViewById(R.id.textViewUserAgeGroup);
            ImageView imageViewUserMood = convertView.findViewById(R.id.imageViewUserMood);

            User user = getItem(position);

            Log.d("user_data", "getView: " + user);

            textViewUserName.setText(user.getName());
            textViewUserAgeGroup.setText(user.getMood_value());
            switch (user.getMood_image_resource()) {
                case 0:
                    imageViewUserMood.setImageResource(R.drawable.not_well);
                    break;
                case 1:
                    imageViewUserMood.setImageResource(R.drawable.sad);
                    break;
                case 2:
                    imageViewUserMood.setImageResource(R.drawable.ok);
                    break;
                case 3:
                    imageViewUserMood.setImageResource(R.drawable.good);
                    break;
                case 4:
                    imageViewUserMood.setImageResource(R.drawable.very_good);
                    break;
            }
            return convertView;
        }
    }
}