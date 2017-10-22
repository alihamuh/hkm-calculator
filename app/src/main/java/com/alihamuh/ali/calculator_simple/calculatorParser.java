package com.alihamuh.ali.calculator_simple;

/**
 * Created by Ali on 5/26/2017.
 */

public class calculatorParser {

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;    //defining two int values pos and ch and pos with initialization of -1

            void nextChar() {                    //a method named nextChar whose return is void, possibly nextChar is a constructor....it s for going to next character within limits of the string length
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;     //initializing ch  using(condition ? if condition true :if condition false:else if..etc)
            }

            boolean eat(int charToEat) {          //a method named eat with boolean i.e true false as return
                while (ch == ' ')
                    nextChar();      //a one line while loop......for space probably ....nextChar is a method
                if (ch == charToEat) {             //an if statement if ch is similar to cahr to eat then nextChar......A thing to note is that nextChar is always where some undesirable char comes
                    nextChar();
                    return true;                 //return eat with true....probably eat means to remove an undesirable character....nextChar means to go to next character
                }
                return false;                       //As evident for above it probably means that to not eat the char
            }

            double parse() {                            //return is double
                nextChar();                               ///will pass through the character through nextChar
                double x = parseExpression();             //divide expression into desirable parts using parseExpression method
                if (pos < str.length())
                    throw new RuntimeException("Math Error");  //throwing exception if int pos is smaller then the string length
                return x;                                        //x will be returned
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term            //two types of terms + terms and - terms
            // term = factor | term `*` factor | term `/` factor                            //* and / are terms
            // factor = `+` factor | `-` factor | `(` expression `)`                   //+,-,(,),number,functionName and ^ are factors
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {                                                       //an unconditional infinite loop...so that the whole string is passed through
                    if (eat('+')) x += parseTerm(); // addition             //if char is + then...
                    else if (eat('-'))
                        x -= parseTerm(); // subtraction          //if char is - then....
                    else
                        return x;                                               //else the string will be returned for other methods to use
                }
            }

            double parseTerm() {
                double x = parseFactor();                       // x is taking vales in decending order x goes like x->parse()->parseExpression()->parseTerm()->parsefactor()...in each the desired values are taken
                for (; ; ) {
                    if (eat('×')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus           //recursion here
                if (eat('-')) return -parseFactor(); // unary minus        //recursion here

                double x;                           //for using executing and returning
                int startPos = this.pos;           //taking value form outside pos int variable
                if (eat('(')) { // parentheses   //for (  value
                    x = parseExpression();                      //parse expression till )
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                        nextChar();      //parseFactor trelling next char to pass through values because they are double numbers
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("sinh")) x = Math.sinh(Math.toRadians(x));
                    else if (func.equals("cosh")) x = Math.cosh(Math.toRadians(x));
                    else if (func.equals("tanh")) x = Math.tanh(Math.toRadians(x));
                    else if (func.equals("acos")) x = Math.acos(x);
                    else if (func.equals("asin")) x = Math.asin(x);
                    else if (func.equals("atan")) x =Math.atan(x);
                    else if (func.equals("e")) x = Math.exp(x);
                    else if (func.equals("sinhinv")) x = Math.log(x+Math.sqrt(x*x + 1.0));
                    else if(func.equals("coshinv")) x = Math.log(x + Math.sqrt(x*x - 1.0));
                    else if (func.equals("tanhinv")) x = 0.5*Math.log( (x + 1.0) / (x - 1.0) );
                    else if (func.equals("ln")) x = Math.log(x);
                    else if (func.equals("log")) x = Math.log10(x);
                    else if(func.equals("cubrt")) x=Math.cbrt(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Math Error");
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
