package com.example.david.better_david_joe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class BetterCalc extends AppCompatActivity {

    private static final String TAG = "CALCLOG";

    private String num1S = "0";
    private String num2S = "";
    private int num1 = 0;
    private String sign = "";
    private int num2 = 0;
    private int phase = 1;
    private String prev = "0";
    private double result = 0.0;
    private String resultS = "0";
    private int clear = 0;
    private String error = "n/a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_screen);


    }

    public void updateEquation() {
        clear = 0;
        TextView equationText = findViewById(R.id.globalEquation);
        String toSet;

        if (num1S.length() > 1 && num1S.substring(0, 1).equals("0")) {
            num1S = num1S.substring(1);
        }

        if (num2S.length() > 1 && num2S.substring(0, 1).equals("0")) {
            num2S = num2S.substring(1);
        }

        if (phase == 1) {
            toSet = num1S + sign;
        } else {
            toSet = num1S + sign + num2S;
        }

        equationText.setText(toSet);
    }

    public void updateResult() {

        TextView resultText = findViewById(R.id.globalResult);
        resultText.setText(resultS);
    }


    public void equals(View view) {
        if (phase == 3) {
            result = calculateEquation();
            setResult();
            num1 = 0;
            num2 = 0;
            num1S = "0";
            num2S = "";
            sign = "";
            phase = 0;
            updateResult();
            updateEquation();
            phase = 1;
        } else if (phase == 1) {
            result = calculateEquation();
            setResult();
            num1S = "0";
            num1 = 0;
            updateResult();
            updateEquation();
            clear = 1;
        } else {
            num2 = num1;
            num2S = num1S;
            phase = 3;
            updateEquation();
        }
    }

    public void setResult() {
        if (error.equals("n/a")){
            resultS = "≈" + (int) result + "" + " thousands";
        } else if (error.equals("integerOverflowError")) {
            resultS = "≈" + (int) result + " big numbers";
            error = "n/a";
        } else if (error.equals("divideByZero")) {
            resultS = "≈" + (int) result + " impossibilities";
            error = "n/a";
        }
    }


    public double calculateEquation() {

        double result;

        switch (sign) {
            case "-":
                result = num1 - num2;

                if ((long) num1 - (long) num2 > Integer.MAX_VALUE
                        || (long) num1 - (long) num2 < -Integer.MAX_VALUE) {
                    result = 3.0;
                }
                break;
            case "+":
                result = num1 + num2;

                if ((long) num1 + (long) num2 > Integer.MAX_VALUE
                        || (long) num1 + (long) num2 < -Integer.MAX_VALUE) {
                    result = 3.0;
                }

                break;
            case "/":

                if (num2 == 0) {
                    result = 3.0;
                    error = "divideByZero";
                    return result;
                }

                result = num1 / num2;

                if ((long) num1 / (long) num2 > Integer.MAX_VALUE
                        || (long) num1 / (long) num2 < -Integer.MAX_VALUE) {
                    result = 3.0;
                }

                break;
            case "*":

                result = num1 * num2;

                if ((long) num1 * (long) num2 > Integer.MAX_VALUE
                        || (long) num1 * (long) num2 < -Integer.MAX_VALUE) {
                    result = 3.0;
                    error = "integerOverflowError";
                    return result;
                }
                break;
            default:
                result = num1;
                break;
        }


        result = (int) (Math.round(result/1000.0));

        return result;
    }

    public void addNumber(int n) {

        String newString;
        if (phase == 1) {
            newString = num1S + n;
        } else {
            newString = num2S + n;
        }

        if (Long.parseLong(newString) > Integer.MAX_VALUE) {
            return;
        }

        if (phase == 1) {
            if (n != 0 || num1 != 0) {
                num1S = num1S + n;
                num1 = Integer.parseInt(num1S);
                updateEquation();
            }
        } else if (phase == 2) {
            num2S = num2S + n;
            num2 = Integer.parseInt(num2S);
            updateEquation();
            phase = 3;
        } else {
            if (n != 0 || num2 != 0) {
                num2S = num2S + n;
                num2 = Integer.parseInt(num2S);
                updateEquation();
            }
        }

        prev = "" + n;
    }

    public void setSymbol(String signArg) {
        sign = signArg;
        if (phase == 1) {
            phase = 2;
            updateEquation();
        } else if (phase == 2 && num2 == 0) {
            updateEquation();
        }



    }

    public void backspace(View view) {
        if (phase == 1) {
            if (num1S.length() > 0) {

                if (num1S.length() == 2 && num1S.charAt(0) == '-'){
                    num1 = 0;
                    num1S = "0";
                    updateEquation();
                    return;
                }
                num1S = num1S.substring(0,num1S.length() - 1);
                if (num1S.length() > 0) {
                    num1 = Integer.parseInt(num1S);
                } else {
                    num1 = 0;
                    num1S = "0";
                }
            }
        } else if (phase == 3) {

            if (num2S.length() == 2 && num2S.charAt(0) == '-'){
                num2 = 0;
                num2S = "";
                updateEquation();
                return;
            }

            num2S = num2S.substring(0,num2S.length() - 1);
            if (num2S.length() > 0) {
                num2 = Integer.parseInt(num2S);
            } else {
                phase = 2;
                num2 = 0;
                num2S = "";
            }
        } else if (phase == 2) {
            sign = "";
            phase = 1;
        }

        updateEquation();
    }


    public void clear(View view) {

        if (clear == 0) {
            num1S = "0";
            num2S = "";
            num1 = 0;
            sign = "";
            num2 = 0;
            phase = 1;
            updateEquation();
            clear = 1;
        } else {
            clear = 0;
            result = 0.0;
            resultS = "0";
            updateResult();
        }
    }



    public void addOne(View view) {
        addNumber(1);
    }

    public void addTwo(View view) {
        addNumber(2);
    }

    public void addThree(View view) {
        addNumber(3);
    }

    public void addFour(View view) {
        addNumber(4);
    }

    public void addFive(View view) {
        addNumber(5);
    }

    public void addSix(View view) {
        addNumber(6);
    }

    public void addSeven(View view) {
        addNumber(7);
    }

    public void addEight(View view) {
        addNumber(8);
    }

    public void addNine(View view) {
        addNumber(9);
    }

    public void addZero(View view) {
        addNumber(0);
    }

    public void chooseTimes(View view) {
        setSymbol("*");
    }

    public void chooseMinus(View view) {
        setSymbol("-");
    }

    public void choosePlus(View view) {
        setSymbol("+");
    }

    public void chooseDivide(View view) {
        setSymbol("/");
    }

    public void negate(View view) {

        if (phase == 1) {

            if (num1 == 0) {
                return;
            }

            num1 = -1 * num1;
            num1S = "-" + num1S;
            updateEquation();
        } else if (phase == 3) {

            if (num2 == 0) {
                return;
            }

            num2 = -1 * num2;
            num2S = "-" + num2S;
            updateEquation();
        }
    }

    public void altClear(View view) {

        if (phase == 1) {
            num1 = 0;
            num1S = num1 + "";
        } else if (phase == 2) {
            sign = "";
            phase = 1;
        } else {
            num2 = 0;
            num2S = "";
            phase = 2;
        }

        updateEquation();

    }
}