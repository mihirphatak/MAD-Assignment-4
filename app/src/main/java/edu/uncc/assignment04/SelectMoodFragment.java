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
import android.widget.ImageView;
import android.widget.TextView;

import edu.uncc.assignment04.databinding.FragmentSelectMoodBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectMoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectMoodFragment extends Fragment {

    Integer[] moodValues = {0, 1, 2, 3, 4};

    MoodAdapter adapter;

    public SelectMoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSelectMoodBinding fragmentSelectMoodBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSelectMoodBinding = FragmentSelectMoodBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return fragmentSelectMoodBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Mood");
        adapter = new MoodAdapter(getActivity(), moodValues);
        fragmentSelectMoodBinding.listView.setAdapter(adapter);

        fragmentSelectMoodBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.selectMood(moodValues[i]);
            }
        });

        fragmentSelectMoodBinding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.moodCancel();
            }
        });
    }

    class MoodAdapter extends ArrayAdapter<Integer> {

        public MoodAdapter(@NonNull Context context, @NonNull Integer[] objects) {
            super(context, R.layout.list_item_mood, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_mood, parent, false);
            }
            ImageView imageViewMood = convertView.findViewById(R.id.imageViewMood);
            TextView textViewMood = convertView.findViewById(R.id.textViewMood);

            switch (position) {
                case 0:
                    imageViewMood.setImageResource(R.drawable.not_well);
                    textViewMood.setText("Not Well");
                    break;
                case 1:
                    imageViewMood.setImageResource(R.drawable.sad);
                    textViewMood.setText("Sad");
                    break;
                case 2:
                    imageViewMood.setImageResource(R.drawable.ok);
                    textViewMood.setText("Okay");
                    break;
                case 3:
                    imageViewMood.setImageResource(R.drawable.good);
                    textViewMood.setText("Good");
                    break;
                case 4:
                    imageViewMood.setImageResource(R.drawable.very_good);
                    textViewMood.setText("Very Good");
                    break;
            }
            return convertView;
        }
    }

    SelectMoodListener mListener;

    interface SelectMoodListener {
        void selectMood(int value);

        void moodCancel();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        mListener = (SelectMoodListener) context;
        super.onAttach(context);
    }
}