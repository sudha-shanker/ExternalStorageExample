package com.example.externalstorageexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExtenalDetails extends AppCompatActivity {
    FileInputStream fstream;
    Intent intent;
    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extenal_details);
        TextView result = findViewById(R.id.resultView);
        Button back = findViewById(R.id.btnBack);
        try {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
            fstream = new FileInputStream(myExternalFile);
            StringBuffer sbuffer = new StringBuffer();
            int i;
            while ((i = fstream.read())!= -1){
                sbuffer.append((char)i);
            }
            fstream.close();
            String details[] = sbuffer.toString().split("\n");
            result.setText("Name: "+ details[0]+"\nPassword: "+details[1]);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ExtenalDetails.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
