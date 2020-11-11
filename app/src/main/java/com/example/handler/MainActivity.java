package com.example.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView=(TextView)findViewById(R.id.txt);
//        final EditText number=(EditText) findViewById(R.id.num);
//        String nn=number.getText().toString();
//        final int num=Integer.parseInt(nn);


        //final String finalTxt = {""};
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                textView.setText(msg.arg1+" "+msg.obj);
            }
        };

        final Runnable myWorker=new Runnable() {
            @Override
            public void run() {
                int a=17;
                int i=2;
                while (i<(a/2)){
                    if((a%i)==0)
                    {
                        Message msg=new Message();
                        msg.arg1=a;
                        msg.obj="不是素数";
                        handler.sendMessage(msg);
                    }
                    else {
                        i++;
                    }
                }
                if(i==(a/2)&&(a%i)!=0)
                {

                    Message msg=new Message();
                    msg.arg1=a;
                    msg.obj="是素数";
                    handler.sendMessage(msg);
                }
                //Message msg=handler.obtainMessage();
                //handler.sendMessage(msg);
            }
        };

        Button button=(Button)findViewById(R.id.judge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workTHread=new Thread(null,myWorker,"workThread");
                workTHread.start();
            }
        });
    }
}