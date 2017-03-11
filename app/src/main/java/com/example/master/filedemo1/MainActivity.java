package com.example.master.filedemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String FILENAME = LOG_TAG + ".txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edit = (EditText) findViewById(R.id.editText);
        final Button bClear = (Button) findViewById(R.id.bClear);
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
            }
        });
        final Button bLoad = (Button)findViewById((R.id.bLoad));
        bLoad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                edit.setText(load());
            }
        });
        final Button bSave = (Button)findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(edit.getText().toString());
            }
        });
        File f = getFilesDir();
        Log.d(LOG_TAG,"Das Verzeichnis: "+f.getAbsolutePath());

    }

    private void save(String s){
        try(FileOutputStream fos = openFileOutput(FILENAME,MODE_PRIVATE);OutputStreamWriter  osw = new OutputStreamWriter(fos)){
            Log.d(LOG_TAG,"save() im try");
            osw.write(s);
        } catch (IOException e){
            Log.e(LOG_TAG,"save() Error",e);
        }
    }
    private String load() {
        StringBuilder sb= new StringBuilder();
        try(FileInputStream fis = openFileInput(FILENAME)){
            Log.d(LOG_TAG,"load() im try");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String s;
            while((s=br.readLine())!=null){
                Log.d(LOG_TAG,"load() im while");
                if(sb.length()>0){
                    sb.append("\n");
                }
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
