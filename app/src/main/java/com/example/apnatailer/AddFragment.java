package com.example.apnatailer;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment implements View.OnClickListener {
    Button btnAddNext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add,container,false);
        Button button=view.findViewById(R.id.btnAddNext);
        button.setOnClickListener(AddFragment.this);
        return view;


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),Measurement.class));
    }
}
