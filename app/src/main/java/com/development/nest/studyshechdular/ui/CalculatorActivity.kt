package com.development.nest.studyshechdular.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.development.nest.studyshechdular.R

class CalculatorActivity : AppCompatActivity() {
    lateinit var tvInput:TextView
    lateinit var tvResult:TextView
    lateinit var log:CardView
    lateinit var xpowy:CardView
    lateinit var clear:CardView
    lateinit var sin:CardView
    lateinit var cos:CardView
    lateinit var tan:CardView
    lateinit var backSpace:CardView
    lateinit var square:CardView
    lateinit var inverse:CardView
    lateinit var equal:CardView
   // lateinit var factorial:CardView
    lateinit var sqrt:CardView
    lateinit var pi:CardView
    lateinit var num7:CardView
    lateinit var num8:CardView
    lateinit var num9:CardView
    lateinit var num4:CardView
    lateinit var num5:CardView
    lateinit var num6:CardView
    lateinit var num1:CardView
    lateinit var num2:CardView
    lateinit var num3:CardView
    lateinit var divide:CardView
    lateinit var multiply:CardView
    lateinit var minus:CardView
    lateinit var plus:CardView
    lateinit var posneg:CardView
    lateinit var num0:CardView
    lateinit var dot:CardView
    private var count = 0
    private var expression = ""
    private var text = ""
    private var result = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)
        init()
        tvResult.setText("0")
    }
    fun init(){
        tvInput= findViewById(R.id.tvInput)
        equal= findViewById(R.id.equal)
        tvResult= findViewById(R.id.tvResult)
        square= findViewById(R.id.square)
        xpowy= findViewById(R.id.xpowy)
        log= findViewById(R.id.log)
        clear= findViewById(R.id.clear)
        sin= findViewById(R.id.sin)
        cos= findViewById(R.id.cos)
        tan= findViewById(R.id.tan)
        backSpace= findViewById(R.id.backSpace)
        inverse= findViewById(R.id.inverse)
       // factorial= findViewById(R.id.factorial)
        sqrt= findViewById(R.id.sqrt)
        pi= findViewById(R.id.pi)
        num7= findViewById(R.id.num7)
        num8= findViewById(R.id.num8)
        num9= findViewById(R.id.num9)
        num4= findViewById(R.id.num4)
        num5= findViewById(R.id.num5)
        num6= findViewById(R.id.num6)
        num1= findViewById(R.id.num1)
        num2= findViewById(R.id.num2)
        num3= findViewById(R.id.num3)
        num0= findViewById(R.id.num0)
        divide= findViewById(R.id.divide)
        multiply= findViewById(R.id.multiply)
        minus= findViewById(R.id.minus)
        plus= findViewById(R.id.plus)
        posneg= findViewById(R.id.posneg)
        dot= findViewById(R.id.dot)

        num0.setOnClickListener {
            tvResult.setText(tvResult.text.toString() + "0")
        }
        num1.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "1")
        }
        num2.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "2")
        }
        num3.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "4")
        }
        num4.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "4")
        }
        num5.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "5")
        }
        num6.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "6")
        }
        num7.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "7")
        }
        num8.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "8")
        }
        num9.setOnClickListener {

            tvResult.setText(tvResult.text.toString() + "9")
        }
        dot.setOnClickListener {
            if (count == 0 && tvResult.length() != 0) {
                tvResult.setText(tvResult.getText().toString() + ".")
                count++
            }
        }
        inverse.setOnClickListener {
            tvResult.setText("1/" + tvResult.getText())
        }
        posneg.setOnClickListener {
            if (tvResult.length() != 0) {
                val s: String = tvResult.getText().toString()
                val arr = s.toCharArray()
                if (arr[0] == '-') tvResult.setText(s.substring(1, s.length))
                else
                    tvResult.setText("-$s")
            }
        }
        clear.setOnClickListener {
            tvResult.setText("")
            tvInput.setText("")
            count = 0
            expression = ""
        }
        plus.setOnClickListener {
            operationClicked("+")
        }
        minus.setOnClickListener {
            operationClicked("-")
        }
        divide.setOnClickListener {
            operationClicked("/")
        }
        multiply.setOnClickListener {
            operationClicked("*")
        }
        backSpace.setOnClickListener {
            text = tvResult.getText().toString()
            if (text.length > 0) {
                if (text.endsWith(".")) {
                    count = 0
                }
                var newText = text.substring(0, text.length - 1)
                //to delete the data contained in the brackets at once
                if (text.endsWith(")")) {
                    val a = text.toCharArray()
                    var pos = a.size - 2
                    var counter = 1
                    //to find the opening bracket position
                    for (i in a.size - 2 downTo 0) {
                        if (a[i] == ')') {
                            counter++
                        } else if (a[i] == '(') {
                            counter--
                        } else if (a[i] == '.') {
                            count = 0
                        }
                        //if opening bracket pair for the last bracket is found
                        if (counter == 0) {
                            pos = i
                            break
                        }
                    }
                    newText = text.substring(0, pos)
                }
                //if e2 edit text contains only - sign or sqrt or any other text functions
                // at last then clear the edit text e2
                if (newText == "-" || newText.endsWith("sqrt") || newText.endsWith("log") || newText.endsWith(
                        "ln"
                    )
                    || newText.endsWith("sin") || newText.endsWith("asin") || newText.endsWith("asind") || newText.endsWith(
                        "sinh"
                    )
                    || newText.endsWith("cos") || newText.endsWith("acos") || newText.endsWith("acosd") || newText.endsWith(
                        "cosh"
                    )
                    || newText.endsWith("tan") || newText.endsWith("atan") || newText.endsWith("atand") || newText.endsWith(
                        "tanh"
                    )
                    || newText.endsWith("cbrt")
                ) {
                    newText = ""
                } else if (newText.endsWith("^") || newText.endsWith("/")) newText =
                    newText.substring(
                        0,
                        newText.length - 1
                    ) else if (newText.endsWith("pi") || newText.endsWith("e^")) newText =
                    newText.substring(0, newText.length - 2)
                tvResult.setText(newText)
            }
        }
        sqrt.setOnClickListener {
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                tvResult.setText("1/($text)")
            }
        }
        square.setOnClickListener {
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                tvResult.setText("($text)^2")
            }
        }
        xpowy.setOnClickListener {
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                tvResult.setText("e^($text)")
            }
        }

        log.setOnClickListener {
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                tvResult.setText("log($text)")
            }
        }
        sin.setOnClickListener {
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                tvResult.setText("sin($text)")
            }
        }
        cos.setOnClickListener {
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                tvResult.setText("cos($text)")
            }
        }
        tan.setOnClickListener {
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                tvResult.setText("tan($text)")
            }
        }
        equal.setOnClickListener {
            /*for more knowledge on DoubleEvaluator and its tutorial go to the below link
                http://javaluator.sourceforge.net/en/home/ */
            if (tvResult.length() != 0) {
                text = tvResult.getText().toString()
                expression = tvInput.getText().toString() + text
            }
            tvInput.setText("")
            if (expression.length == 0) expression = "0.0"
            try {
//                    evaluate the expression
                result = Evaluator().evaluate(expression)
                tvResult.setText(result.toString() + "")
//                    insert expression and result in sqlite database if expression is valid and not 0.0
//                    if (String.valueOf(result).equals("6.123233995736766E-17")) {
//                        result = 0.0;
//                        e2.setText(result + "");
//                    } else if (String.valueOf(result).equals("1.633123935319537E16"))
//                        e2.setText("infinity");
//                    else
//                        e2.setText(result + "");
//                    if (!expression.equals("0.0"))
//                        dbHelper.insert("SCIENTIFIC", expression + " = " + result);
            } catch (e: Exception) {
                tvResult.setText("Invalid Expression")
                tvInput.setText("")
                expression = ""
                e.printStackTrace()
            }
        }
    }
    private fun operationClicked(operation: String) {
        if (tvResult.length() != 0) {
            val text: String = tvResult.getText().toString()
            tvInput.setText(tvInput.getText().toString() + text + operation)
            tvResult.setText("")
            count = 0
        } else {
            val text: String = tvInput.getText().toString()
            if (text.length > 0) {
                val newText = text.substring(0, text.length - 1) + operation
                tvInput.setText(newText)
            }
        }
    }
}