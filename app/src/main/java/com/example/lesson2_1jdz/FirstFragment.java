package com.example.lesson2_1j;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson2_1jdz.Model;
import com.example.lesson2_1jdz.R;
import com.example.lesson2_1jdz.RecyclerAdapter;
import com.example.lesson2_1jdz.SecondFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FirstFragment extends Fragment implements RecyclerAdapter.OnClickListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FloatingActionButton fab;

    private String mParam1;
    private String mParam2;

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fr_container, new SecondFragment()).
                    commit();
        });
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        Model model = new Model();
        if (mParam1 != null && mParam2 != null) {
            model.setTitle(mParam1);
            model.setDesc(mParam2);
            recyclerAdapter.adModel(model, this);
        }
    }

    @Override
    public void onClick(Model model) {
        getFragmentManager().beginTransaction().replace(R.id.fr_container, SecondFragment.newInstance(model.getTitle(), model.getDesc()))
                .commit();
    }
}