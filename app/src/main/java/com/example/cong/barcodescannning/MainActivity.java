package com.example.cong.barcodescannning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button scanBtnName, scanBtnId, scanBtnLocate;
    private TextView formatTxt;
    private EditText contentTxtName,contentTxtId, contentTxtLocate;
    int check=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtnName = (Button)findViewById(R.id.scan_button_name);
        scanBtnId = (Button)findViewById(R.id.scan_button_id);
        scanBtnLocate = (Button)findViewById(R.id.scan_button_locate);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxtName = (EditText) findViewById(R.id.scan_content_name);
        contentTxtId = (EditText) findViewById(R.id.scan_content_id);
        contentTxtLocate = (EditText) findViewById(R.id.scan_content_locate);

        scanBtnName.setOnClickListener(onScanName);
        scanBtnId.setOnClickListener(onScanId);
        scanBtnLocate.setOnClickListener(onScanLocate);
    }
    private View.OnClickListener onScanName=new View.OnClickListener(){
        public void onClick(View v){
            if(v.getId()==R.id.scan_button_name){
                IntentIntegrator scanIntegrator= new IntentIntegrator(MainActivity.this);
                scanIntegrator.setBeepEnabled(false);
                check=1;
                scanIntegrator.initiateScan();
            }
        }
    };
    private View.OnClickListener onScanId=new View.OnClickListener(){
        public void onClick(View v){
            if(v.getId()==R.id.scan_button_id){
                IntentIntegrator scanIntegrator= new IntentIntegrator(MainActivity.this);
                scanIntegrator.setBeepEnabled(false);
                check=2;
                scanIntegrator.initiateScan();
            }
        }
    };
    private View.OnClickListener onScanLocate=new View.OnClickListener(){
        public void onClick(View v){
            if(v.getId()==R.id.scan_button_locate){
                IntentIntegrator scanIntegrator= new IntentIntegrator(MainActivity.this);
                scanIntegrator.setBeepEnabled(false);
                check=3;
                scanIntegrator.initiateScan();
            }
        }
    };

    public void onActivityResult(int requestCode,int resultCode, Intent intent){
        IntentResult scaningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scaningResult != null){
            String scanContent = scaningResult.getContents();
            String scanFormat = scaningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            if(check==1) {
                contentTxtName.setText(scanContent);
            }
            else if(check==2) {
                contentTxtId.setText(scanContent);
            }
            else if(check==3) {
                contentTxtLocate.setText(scanContent);
            }
        }
        else
            Toast.makeText(getApplicationContext(),"No data received",Toast.LENGTH_SHORT).show();
    }
}
