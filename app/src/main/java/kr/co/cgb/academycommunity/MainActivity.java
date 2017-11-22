package kr.co.cgb.academycommunity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private android.widget.Button signupBtn;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder loginPopup = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_login_item, (ViewGroup) findViewById(R.id.layout_root));

                loginPopup.setTitle("로그인하시겠습니까?");
                loginPopup.setView(layout);

                loginPopup.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(mContext, IndexActivity.class);
                        startActivity(intent);

                        Toast.makeText(mContext, "로그인성공", Toast.LENGTH_SHORT).show();

                    }
                });

                loginPopup.setNegativeButton("취소", null);
                loginPopup.show();


            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.signupBtn = (Button) findViewById(R.id.signupBtn);

    }
}
