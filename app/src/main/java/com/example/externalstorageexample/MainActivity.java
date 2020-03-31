package com.example.externalstorageexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText uname, pwd;
    Button saveBtn;
    FileOutputStream fstream;
    Intent intent;
    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String[] mPermission = {android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (EditText)findViewById(R.id.txtName);
        pwd = (EditText)findViewById(R.id.txtPwd);
        saveBtn = (Button)findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString()+"\n";
                String password = pwd.getText().toString();
                try {
                    if ((ContextCompat.checkSelfPermission(MainActivity.this, mPermission[0]) != PackageManager.PERMISSION_GRANTED)
                                || (ContextCompat.checkSelfPermission(MainActivity.this, mPermission[1]) != PackageManager.PERMISSION_GRANTED)) {

                        ActivityCompat.requestPermissions(MainActivity.this, mPermission, 23);
                    }
                    else {
                        if (username.length() == 1 || password.length() == 0)
                            Toast.makeText(MainActivity.this, "either of field is empty", Toast.LENGTH_SHORT).show();
                        else {
                            myExternalFile = new File(getExternalFilesDir(filepath), filename);
                            fstream = new FileOutputStream(myExternalFile);
                            fstream.write(username.getBytes());
                            fstream.write(password.getBytes());
                            fstream.close();
                            Toast.makeText(getApplicationContext(), "Details Saved in " + myExternalFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                            intent = new Intent(MainActivity.this, ExtenalDetails.class);
                            startActivity(intent);
                        }
                    }

                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}
