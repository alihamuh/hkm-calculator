package com.alihamuh.ali.calculator_simple;



import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Ali on 5/1/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {







    Button[] numButton= new Button[10];
    int[] Viewid = new int[]{R.id.btn_cos,
            R.id.btn_c,
            R.id.btn_dec,
            R.id.btn_divide,
            R.id.btn_equal,
            R.id.btn_minus,
            R.id.btn_multiply,
            R.id.btn_plus,
            R.id.btn_sin,
            R.id.btn_tan,R.id.btn_acos,R.id.btn_asin,R.id.btn_atan,R.id.btn_sinh,R.id.btn_cosh,R.id.btn_tanh,R.id.btn_sinhinv,
    R.id.btn_coshinv,R.id.btn_tanhinv,R.id.btn_brkopen,R.id.btn_brkclose,R.id.btn_log10,R.id.btn_natural,R.id.btn_pi,R.id.btn_loge,R.id.btn_power,
    R.id.btn_plusminus,R.id.btn_sqrt,R.id.btn_cubert,R.id.powten};   //only front interface for now


    Button[] symButton = new Button[30];


        @Override
        protected void onCreate(Bundle savedInstanceState) {


                super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_interface);


            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/segoeuil.ttf");


            for (int i=0;i<10; i++) {

                String btnId = "btn"+"_"+i;

                int resId = getResources().getIdentifier(btnId, "id", getPackageName());

                numButton[i] = ((Button) findViewById(resId));
                numButton[i].setTypeface(custom_font);

            }

            for(int i=0; i<30; i++){                             //using for loop to apply font
                symButton[i]=(Button)findViewById(Viewid[i]);

                symButton[i].setTypeface(custom_font);


            }

            TextView secondary = (TextView)findViewById(R.id.secondary);
            secondary.setTypeface(custom_font);

            EditText primary = (EditText)findViewById(R.id.primary);
            primary.setTypeface(custom_font);



        }











}
