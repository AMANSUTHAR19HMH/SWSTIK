package com.abc.agrios;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class homemain extends Fragment {

    private TextView tvGrain, tvFruits, tvOrganic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homemain, container, false);

        // Initialize TextView elements
        tvGrain = view.findViewById(R.id.grainstxtbtn);
        tvFruits = view.findViewById(R.id.fruittxtbtn);
        tvOrganic = view.findViewById(R.id.organictxtbtn);

        // Set click listeners for TextView elements
        tvGrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), grain.class);
                startActivity(intent);
            }
        });
        tvFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), freshfruits.class);
                startActivity(intent);
            }
        });
        tvOrganic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), organicvegetables.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
