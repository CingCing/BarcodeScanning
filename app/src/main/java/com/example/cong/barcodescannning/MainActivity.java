package com.example.cong.barcodescannning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(onScan);
    }
    private View.OnClickListener onScan=new View.OnClickListener(){
        public void onClick(View v){
            if(v.getId()==R.id.scan_button){
                IntentIntegrator scanIntegrator= new IntentIntegrator(MainActivity.this);
                scanIntegrator.setBeepEnabled(false);
                scanIntegrator.initiateScan();
            }
        }
    };
    public void onActivityResult(int requestCode,int resultCode, Intent intent){
        IntentResult scaningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scaningResult != null){
            String scanContetn = scaningResult.getContents();
            String scanFormat = scaningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("Content: "+ scanContetn);
        }
        else
            Toast.makeText(getApplicationContext(),"No data received",Toast.LENGTH_SHORT).show();
    }
}
