package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.aosproject.imagemarket.NetworkTask.FindPwdNetworkTask;
import com.aosproject.imagemarket.NetworkTask.NetworkTaskDY;
import com.aosproject.imagemarket.R;
import com.aosproject.imagemarket.Util.ShareVar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class FindPasswordActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    TextInputEditText editText;
    Button button;
    ImageView back;
    String newPwd;
    String email;
    String urlAddr,myIP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_find_password);
        newPwd = findPwd();


        textInputLayout = findViewById(R.id.layout_find_email);
        editText = findViewById(R.id.edit_text_find_email);
        button = findViewById(R.id.button_find_password);
        back = findViewById(R.id.back_image_in_find_password);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        urlAddr = ShareVar.macIP + "/jsp/updatePassword.jsp?";

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().trim().length()==0){
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0){
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()==0){
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }
        });
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editText.getText().toString();
                urlAddr = urlAddr + "email=" + email + "&pwd=" + newPwd;
                Log.e("Change PWD", urlAddr);
                String result = connectInsertData();
                if(result.equals("1")){
                    sendEmail();
                    onBackPressed();
                    Toast.makeText(FindPasswordActivity.this, "???????????? ???????????? ??????????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FindPasswordActivity.this, "???????????? ???????????? ???????????? ????????? ?????? ????????????.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void sendEmail(){
        String user = email; // ????????????
        String subject = "Im Joy ???????????? ?????????"; // ??????
        String message = "?????? ??????????????? " + newPwd + "?????????. ??????????????? ?????????????????????."; // ??????

        FindPwdNetworkTask networkTask = new FindPwdNetworkTask(FindPasswordActivity.this, user, subject, message);
        networkTask.execute();
    }

    private String findPwd() {
        String newPwd = "";
        for (int i=1; i<=7; i++) {
            Random rand = new Random();
            int random = rand.nextInt(10);
            String str = Integer.toString(random);
            newPwd += str;
        }

        return newPwd;
    }

    private String connectInsertData(){
        String result = null;

        try {
            NetworkTaskDY networkTask = new NetworkTaskDY(FindPasswordActivity.this, urlAddr, "insert");
            Object obj = networkTask.execute().get();
            result = (String)obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("Result",result);
        return result;
    }

}