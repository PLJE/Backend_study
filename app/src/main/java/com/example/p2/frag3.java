package com.example.p2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.w3c.dom.Text;


public class frag3 extends Fragment {

    private Button bt_logout;

    private String nickname;
    private String profileImg;

    private TextView nick;
    private ImageView image;

    public frag3() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag3 , container , false);
        bt_logout = (Button)v.findViewById(R.id.bt_logout);

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback()
                {
                    @Override
                    public void onCompleteLogout()
                    {
                        Intent intent = new Intent(getActivity(),MainActivity.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);
                    }
                });
            }
        });

        nick = (TextView)v.findViewById(R.id.tv_nickname);

        nickname = ((SubActivity)getActivity()).getNick();

        nick.setText(nickname + " 님");


        return v;
    }
}