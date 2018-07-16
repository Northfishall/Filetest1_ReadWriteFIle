package com.example.a37013.filetest1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import DB.*;
import cRUD.*;

import java.util.HashMap;

import service.LoginService;

public class MainActivity extends Activity {
    private EditText et_username = null;
    private EditText et_password = null;
    private CheckBox cb_remeber_password = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);
        cb_remeber_password = findViewById(R.id.cb_remember_psw);
        HashMap<String , String> info = LoginService.getInfo((this));
        if(info!=null)
        {
            this.et_username.setText(info.get("username"));
            this.et_password.setText(info.get("password"));
        }
        createdb();
    }

    private void createdb() {
        PersonSqliteOpenHelper helper = new PersonSqliteOpenHelper(this);
        helper.getWritableDatabase();
        Toast.makeText(this,"数据库创建成功或者已经存在！",Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("WrongConstant")
    public void login(View view)
    {
        dao checker = new dao(this);

        String username = this.et_username.getText().toString().trim();
        String password = this.et_password.getText().toString().trim();
        String realpassword = null;
        if(!TextUtils.isEmpty(username))
        {
            realpassword = checker.find(username);
        }

        if(TextUtils.isEmpty(username)|| TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"用户名密码不为空",0).show();
        }
        else if(realpassword==null)
        {
            Toast.makeText(this,"用户名不存在",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(this.cb_remeber_password.isChecked())
            {
                boolean result = LoginService.saveInfo(this,username,password);
                if(result)
                {
                    Toast.makeText(this,"保存密码成功",0).show();
                }
                else
                {
                    Toast.makeText(this,"保存密码失败",0).show();
                }
            }
            if(realpassword.equals(password))
            {
                Toast.makeText(this,"登录成功",0).show();
            }
            else
            {
                Toast.makeText(this,"登录失败",0).show();
            }
        }
    }

    public void save(View view) {
        String username = this.et_username.getText().toString().trim();
        String password = this.et_password.getText().toString().trim();
        dao saver = new dao(this);
        saver.add(username,password);
        Toast.makeText(this,"成功写入数据库",Toast.LENGTH_SHORT).show();
    }
}
