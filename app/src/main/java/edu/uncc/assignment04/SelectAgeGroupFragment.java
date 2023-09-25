package edu.uncc.assignment04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import edu.uncc.assignment04.databinding.FragmentSelectAgeGroupBinding;

public class SelectAgeGroupFragment extends Fragment {

    String[] ageGroups = {"Under 12 years old", "12-17 years old", "18-24 years old", "25-34 years old", "35-44 years old", "45-54 years old", "55-64 years old", "65-74 years old", "75 years or older"};

    ArrayAdapter<String> adapter;

    public SelectAgeGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSelectAgeGroupBinding fragmentSelectAgeGroupBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSelectAgeGroupBinding = FragmentSelectAgeGroupBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return fragmentSelectAgeGroupBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Age Group");
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, ageGroups);
        fragmentSelectAgeGroupBinding.listView.setAdapter(adapter);

        fragmentSelectAgeGroupBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.gotoAddUser(ageGroups[i]);
            }
        });

        fragmentSelectAgeGroupBinding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancel();
            }
        });
    }

    SelectAgeGroupListener mListener;

    interface SelectAgeGroupListener {
        void gotoAddUser(String ageGroup);

        void cancel();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        mListener = (SelectAgeGroupListener) context;
        super.onAttach(context);
    }
}