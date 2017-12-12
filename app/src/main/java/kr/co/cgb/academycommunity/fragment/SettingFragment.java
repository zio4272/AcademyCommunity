package kr.co.cgb.academycommunity.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import kr.co.cgb.academycommunity.IndexActivity;
import kr.co.cgb.academycommunity.LoginActivity;
import kr.co.cgb.academycommunity.MainActivity;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.util.ContextUtil;
import kr.co.cgb.academycommunity.util.GlobalData;

/**
 * Created by PC on 2017-11-27.
 */

public class SettingFragment extends Fragment {


    private android.widget.ImageView backBtn;
    private android.widget.Switch switch2;
    private android.widget.Button logoutBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_fragment_item, container, false);
        this.logoutBtn = (Button) v.findViewById(R.id.logoutBtn);
        this.switch2 = (Switch) v.findViewById(R.id.switch2);
        this.backBtn = (ImageView) v.findViewById(R.id.backBtn);



        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    private void setValues() {

    }

    private void setupEvents() {

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("로그아웃")
                        .setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                                ContextUtil.logout(getActivity());
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });

    }
}

