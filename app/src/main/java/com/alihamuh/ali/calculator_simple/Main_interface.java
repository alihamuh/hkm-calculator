package com.alihamuh.ali.calculator_simple;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;


public class Main_interface extends BaseActivity {
    private View subcontent_main_interface;

    private EditText primary; //variable to access lower console
    private EditText secondary; //variable to access upper console

    boolean superScript = false;
    boolean plusminus =false;

    private Button plusButton;
    private Button equalButton;
    private Button cancelButton;
    private Button minusButton;
    private Button productButton;
    private Button divideButton;


    Boolean fromEqual = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        subcontent_main_interface = findViewById(R.id.subcontent_main_interface);


        primary = (EditText) findViewById(R.id.primary);   //setting  lower console definite
        secondary = (EditText) findViewById(R.id.secondary);  //setting upper console definite

       secondary.setKeyListener(null);

        plusButton = (Button) findViewById(R.id.btn_plus);
        equalButton = (Button) findViewById(R.id.btn_equal);
        cancelButton = (Button) findViewById(R.id.btn_c);
        minusButton = (Button) findViewById(R.id.btn_minus);
        productButton = (Button) findViewById(R.id.btn_multiply);
        divideButton = (Button) findViewById(R.id.btn_divide);



        primary.setSelection(1);

    }




    public void slideUpDown(final View view) {
        if (!isPanelShown()) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_up);

            subcontent_main_interface.startAnimation(bottomUp);
            subcontent_main_interface.setVisibility(View.VISIBLE);


        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            subcontent_main_interface.startAnimation(bottomDown);
            subcontent_main_interface.setVisibility(View.GONE);


        }
    }


    private boolean isPanelShown() {
        return subcontent_main_interface.getVisibility() == View.VISIBLE;
    }




    public void One(View v) {

        numberButtonControl("1");

    }//end Button One


    public void Two(View v) {

        numberButtonControl("2");

    }//end Button Two

    public void Three(View v){

        numberButtonControl("3");
    }

    public void Four(View v){

        numberButtonControl("4");
    }

    public void Five(View v){

        numberButtonControl("5");
    }

    public void Six(View v){

        numberButtonControl("6");
    }

    public void Seven(View v){
        numberButtonControl("7");
    }

    public void Eight(View v){

        numberButtonControl("8");
    }

    public void Nine(View v){

        numberButtonControl("9");
    }

    public void Zero(View v){

        numberButtonControl("0");
    }

    public void Decimal(View v){

        numberButtonControl(".");
    }

    public void Sin(View v){

        otherButtonControl("sin");
    }

    public void Cos(View v){

        otherButtonControl("cos");
    }

    public void Tan(View v){

        otherButtonControl("tan");
    }


    public void PowTen(View v){

    otherButtonControl("10^");
    }

    public void Pi(View v){

        numberButtonControl("π");

    }

    public void power_n(View v){
/*
        int expressioLengthStart= -1;
        int expressionLengthEnd= -1;

        if(superScript){superScript =false;
            expressionLengthEnd = primary.getText().toString().length();
            superScriptSpliitter(expressioLengthStart,expressionLengthEnd);

        }else{
            superScript = true;
             expressioLengthStart = primary.getText().toString().length();
        superScriptSpliitter(expressioLengthStart,expressionLengthEnd);
        }
        */
        otherButtonControl("^");

    }

    public void asin(View v){
        otherButtonControl("asin");

    }

    public void acos(View v){

        otherButtonControl("acos");

    }

    public void atan(View v){

        otherButtonControl("atan");
    }

    public void sinh(View v){

        otherButtonControl("sinh");
    }

    public void cosh(View v){

        otherButtonControl("cosh");
    }

    public void tanh(View v){

        otherButtonControl("tanh");
    }

    public void sinhinv(View v){

        otherButtonControl("sinh⁻¹");
    }

    public void coshinv(View v){

        otherButtonControl("cosh⁻¹");
    }

    public void tanhinv(View v){

        otherButtonControl("tanh⁻¹");
    }

    public void brkOpen(View v){

        otherButtonControl("(");
    }

    public void brkClose(View v){

        otherButtonControl(")");
    }



    public void powsqr(View v){

        otherButtonControl("²");

    }

    public void sqrt(View v){

        otherButtonControl("\u221a");
    }


    public void cubert(View v){

        otherButtonControl("\u221b");
    }

    public void natural_e(View v){

        otherButtonControl("e");
    }

    public void naturalLog(View v){

        otherButtonControl("ln");
    }

    public void log(View v){

        otherButtonControl("log");
    }

    public void Plus(View v) {



        symbolButtonControl("+");


    }//end Plus Button


    public void Minus(View v) {



        symbolButtonControl("-");



    }//end Minus Button


    public void Product(View v) {




            symbolButtonControl("×");



    }//end Product Button


    public void Divide(View v) {



            symbolButtonControl("/");


    }//end Divide Button


    public void Equal(View v) {

//////////////////////////////////////////////////////////////////////////////////

           secondryEval(primary,secondary,0);
            primary.setText(""); //before animation no text

            Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_scale);

            Animation moveAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_move);


            AnimationSet text = new AnimationSet(false);
            text.addAnimation(scaleAnimation);
            text.addAnimation(moveAnimation);
            secondary.startAnimation(text);


            text.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                   ////////////nothing///////////////////////
                }


                               //Animation Listener for setting the Animation end procedures likeing setting text for primary

                @Override
                public void onAnimationEnd(Animation animation) {
                    primary.setText(secondary.getText());
                    secondary.setText("");
                    primary.setSelection(primary.getText().length());
                    fromEqual = false;

                }




                @Override
                public void onAnimationRepeat(Animation animation) {
                       /////////nothing/////////////////
                }
            });

///////////////////////////////////////////////////////////////////////////////////////////////////

            fromEqual = true;


        primary.setSelection(primary.getText().length());     //to get the cursor to the right most position

    }//end Equal









    public void Del(View v) {

        String deleteChar = primary.getText().toString();

        int start = primary.getSelectionStart();


        String lnString = " ";
        if(start>1) {
            //still dont know the exact reason why length() behaves like this
            lnString = deleteChar.substring(start - 2, start);

        }


           String trignometryString = " ";
        if(start>2) {
            //still dont know the exact reason why length() behaves like this
            trignometryString = deleteChar.substring(start - 3, start);

        }

        String reverseTrignometryString = "";
        if(start>3) {

            reverseTrignometryString = deleteChar.substring(start - 4, start);

        }

        String reverseHyperString = "";
        if(start>5) {

            reverseHyperString = deleteChar.substring(start - 6, start);

        }

           if (start > 0) {

              if(reverseHyperString.equals("tanh⁻¹")||reverseHyperString.equals("cosh⁻¹")||reverseHyperString.equals("sinh⁻¹")){

                  primary.getText().delete(start-6,start);;

               }else if(reverseTrignometryString.equals("asin")||reverseTrignometryString.equals("acos")||reverseTrignometryString.equals("atan")||
                       reverseTrignometryString.equals("sinh")||reverseTrignometryString.equals("cosh")||reverseTrignometryString.equals("tanh")) {        //for 3 digit symbols

                  primary.getText().delete(start-4,start);

               }else if(trignometryString.equals("sin")||trignometryString.equals("cos")
                       ||trignometryString.equals("tan")||trignometryString.equals("log")){        //for 3 digit symbols

                  primary.getText().delete(start-3,start);

                }else if(lnString.equals("ln")) {

                  primary.getText().delete(start-2,start);

              }else{

                  primary.getText().delete(start-1,start);
                    fromEqual = false;
                    equalButton.setClickable(true);



                    if (deleteChar.length() > 1) {

                        delExtension();

                    }else{     secondary.setText("");      }

                }
        }

        ///////for long click of cancel///////////////////////////////////
        symButton[1].setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {

                primary.setText("");

                secondary.setText("");
                return true;    // <- set to true
            }
        });

      primary.getSelectionStart();             //setting cursor at right most

    }//end delete button








    public void secondryEval(EditText console, EditText console2,int symbToRemove) {

        String resultStr = console.getText().toString().substring(0,console.getText().toString().length()-symbToRemove);//getting to value which doesnot cause Math error
        if(resultStr.contains("tanh⁻¹")||resultStr.contains("cosh⁻¹")||resultStr.contains("sinh⁻¹")
                ||resultStr.contains("π")||resultStr.contains("∛")||resultStr.contains("√")||resultStr.contains("²")) {
            resultStr = resultStr.replaceAll("π", "" + Math.PI);
            resultStr =resultStr.replaceAll("tanh⁻¹","tanhinv");
            resultStr =resultStr.replaceAll("cosh⁻¹","coshinv");
            resultStr =resultStr.replaceAll("sinh⁻¹","sinhinv");
            resultStr =resultStr.replaceAll("∛","cubrt");
            resultStr =resultStr.replaceAll("√","sqrt");
            resultStr =resultStr.replaceAll("²","^2");

        }

        //resultStr = resultStr.replaceAll(resultStr,superScriptSpliitter());

        DecimalFormat df = new DecimalFormat("###.############");

        try {

            console2.setText("" + df.format(calculatorParser.eval(resultStr)));

        } catch (RuntimeException e) {

            console2.setText("Error");
        }

    }




public void otherButtonControl(String symbol){

   if(secondary.getText().equals("Error")||secondary.getText().equals("type missing expression")){

            secondary.setText("type missing expression");

        }else if (primary.getSelectionStart() != primary.getSelectionEnd()) {   //for deleting selection

            int start = primary.getSelectionStart();  //getting cursor starting position
            int end = primary.getSelectionEnd();      //getting cursor ending position
            String selectedStr = primary.getText().toString().substring(start, end);    //getting the selected Text
            primary.setText(primary.getText().toString().replace(selectedStr, symbol));
            primary.setSelection(start + 1);

        } else {

       if(primary.getText().toString().equals("0")){

        primary.setText(""+symbol);
           primary.setSelection(primary.getText().length());
       }else if (fromEqual) {         //check this from equal it should be made true and false


                primary.setText(secondary.getText() + symbol);
                fromEqual = false;


            } else {

                int start = primary.getSelectionStart();   //this is to get the the cursor position

                primary.getText().insert(start, symbol);
            }


                }

}





    public void symbolButtonControl(String replaceSymbol){

        if(primary.getText().toString().equals("0")){

              if(replaceSymbol.equals("+")||replaceSymbol.equals("-")) {
                  primary.setText("" + replaceSymbol);
                  primary.setSelection(primary.getText().length());
              }else{primary.setText("0"); primary.setSelection(primary.getText().length());}

        }else if(primary.getText().toString().length()==0){

                  //////do nothing for now/////////////////////
        }else if(secondary.getText().equals("Error")||secondary.getText().equals("type missing expression")){

            secondary.setText("type missing expression");

        }else if (primary.getSelectionStart() != primary.getSelectionEnd()) {   //for deleting selection

            int start = primary.getSelectionStart();  //getting cursor starting position
            int end = primary.getSelectionEnd();      //getting cursor ending position
            String selectedStr = primary.getText().toString().substring(start, end);    //getting the selected Text
            primary.setText(primary.getText().toString().replace(selectedStr, replaceSymbol));
            primary.setSelection(start + 1);

        } else{


            if (fromEqual) {         //check this from equal it should be made true and false


                primary.setText(secondary.getText() + replaceSymbol);
                fromEqual = false;


            } else {


                String primaryString = primary.getText().toString();    //converting so as to use for char

                Character lastChar = primaryString.charAt(primaryString.length()-1);



                if(lastChar.equals('+')||lastChar.equals('-')||lastChar.equals('/')||lastChar.equals('×')){  //form replacing one symbol with another

                    primary.setText(primaryString.substring(0,primaryString.length()-1)+replaceSymbol);

                    primary.setSelection(primary.getText().length());

                }else {

                    int start = primary.getSelectionStart();   //this is to get the the cursor position

                    primary.getText().insert(start, replaceSymbol);



                }

            }
        }
    }






    public void numberButtonControl(String number) {

        if(superScript) {

            int start = primary.getSelectionStart();   //this is to get the the cursor position

            number = superscript(number);
            primary.getText().insert(start, number);

            secondryEval(primary, secondary,0);

        }else if (primary.getSelectionStart() != primary.getSelectionEnd()) {//for deleting selection


            int start = primary.getSelectionStart();  //getting cursor starting position
            int end = primary.getSelectionEnd();      //getting cursor ending position
            String selectedStr = primary.getText().toString().substring(start, end);    //getting the selected Text
            primary.setText(primary.getText().toString().replace(selectedStr, number));
            primary.setSelection(start + 1);

            secondryEval(primary, secondary,0);
        } else {


            if (fromEqual) {         //check this from equal it should be made true and false


                primary.setText(secondary.getText() + number);
                fromEqual = false;

            }else if (primary.getText().toString().equals("0")) {      //for zero correction

                primary.setText(number);

                primary.setSelection(primary.getText().length());

            } else {     //for else

                int start = primary.getSelectionStart();   //this is to get the the cursor position

                primary.getText().insert(start, number);

                secondryEval(primary, secondary,0);
            }

        }
    }







    public void delExtension(){

        String primaryString = primary.getText().toString();    //converting so as to use for char

        Character lastChar = primaryString.charAt(primaryString.length()-1);

        String beforeTrigRemovealSymbString = " ";

        if(primaryString.length()>3) {
            beforeTrigRemovealSymbString = primaryString.substring(primaryString.length() - 3, primaryString.length());
        }

        String beforeRTrigRemovealSymbString = " ";

        if(primaryString.length()>4){

            beforeRTrigRemovealSymbString = primaryString.substring(primaryString.length()-4,primaryString.length());
        }

        String beforeHyperRemovelString = " ";

        if(primaryString.length()>6){

            beforeHyperRemovelString = primaryString.substring(primaryString.length()-6,primaryString.length());
        }


        String beforeLnString="";

        if(primaryString.length()>2){

            beforeHyperRemovelString = primaryString.substring(primaryString.length()-2,primaryString.length());
        }


        if ((lastChar >= '0' && lastChar <= '9') || lastChar == '.')  {  //form replacing one symbol with another

            ///////////////nothing changes////////////////////////
            secondryEval(primary, secondary,0);

        }else if(beforeHyperRemovelString.equals("tanh⁻¹")||beforeHyperRemovelString.equals("cosh⁻¹")||beforeHyperRemovelString.equals("sinh⁻¹")) {

            secondryEval(primary,secondary,7);

        }else if (beforeRTrigRemovealSymbString.equals("asin")||beforeRTrigRemovealSymbString.equals("acos")||beforeRTrigRemovealSymbString.equals("atan")||
                beforeRTrigRemovealSymbString.equals("sinh")||beforeRTrigRemovealSymbString.equals("cosh")||beforeRTrigRemovealSymbString.equals("tanh")){

            secondryEval(primary,secondary,5);

        }else if(lastChar.equals('+')||lastChar.equals('-')||lastChar.equals('×')||lastChar.equals('/')){

            secondryEval(primary,secondary,1);
        }else if(beforeTrigRemovealSymbString.equals("sin")||beforeTrigRemovealSymbString.equals("cos")
                ||beforeTrigRemovealSymbString.equals("tan")||beforeTrigRemovealSymbString.equals("log")){

            secondryEval(primary,secondary,4);

        }else if(beforeLnString.equals("ln")){

            secondryEval(primary,secondary,3);

        }else{  secondary.setText(""); }
        }

int finalEnd;
    public String superScriptSpliitter(int start,int end) {


        if(superScript) {
            String primarywithSuperscript = primary.getText().toString().substring(start, primary.length());
            finalEnd = start;
            return primary.getText().toString().replace(primary.getText().toString(),superScriptConverter(primarywithSuperscript));
        }else{
            String primarywithSuperscript = primary.getText().toString().substring(end, end);

            return primary.getText().toString().replace(primary.getText().toString(),superScriptConverter(primarywithSuperscript));

        }

    }



public  String superScriptConverter(String str){

    str = str.replaceAll("⁰","0");
    str = str.replaceAll("¹","1");
    str = str.replaceAll("²","2");
    str = str.replaceAll("³","3");
    str = str.replaceAll("⁴","4");
    str = str.replaceAll("⁵","5");
    str = str.replaceAll("⁶","6");
    str = str.replaceAll("⁷","7");
    str = str.replaceAll("⁸","8");
    str = str.replaceAll("⁹","9");
    return str;

}



    public static String superscript(String str) {
        str = str.replaceAll("0", "\u2070");
        str = str.replaceAll("1", "\u00b9");
        str = str.replaceAll("2", "\u00b2");
        str = str.replaceAll("3", "\u00b3");
        str = str.replaceAll("4", "\u2074");
        str = str.replaceAll("5", "\u2075");
        str = str.replaceAll("6", "\u2076");
        str = str.replaceAll("7", "\u2077");
        str = str.replaceAll("8", "\u2078");
        str = str.replaceAll("9", "\u2079");
        return str;
    }



}