package com.example.p2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class frag2 extends Fragment {


    public frag2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag2, container, false);

        // 버튼 컴포넌트 초기화
        Button enroll = v.findViewById(R.id.enroll);

// 버튼 이벤트 추가
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// userid를 가지고 RegisterActivity 로 이동
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}