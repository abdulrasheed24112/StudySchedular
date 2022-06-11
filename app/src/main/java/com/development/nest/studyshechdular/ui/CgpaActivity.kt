package com.development.nest.studyshechdular.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.development.nest.studyshechdular.R
import com.development.nest.studyshechdular.databinding.ActivityCgpaBinding
import com.development.nest.studyshechdular.ui.dialog.AlertDialogFragment

class CgpaActivity : AppCompatActivity() {
    private var newSemesterButton: Button? = null
    private var calculateCgpaButton: Button? = null
    private var linearLayoutOne: LinearLayout? = null
    private  var linearLayoutSingleTwo:LinearLayout? = null
    private  var linearLayoutSingleThree:LinearLayout? = null
    private  var linearLayoutSingleFour:LinearLayout? = null
    private var linearLayoutSingleFive: LinearLayout? = null
    private  var linearLayoutSingleSix:LinearLayout? = null
    private  var linearLayoutSingleSeven:LinearLayout? = null
    private  var linearLayoutSingleEight:LinearLayout? = null
    private var linearLayoutSingleNine: LinearLayout? = null
    private  var linearLayoutSingleTen:LinearLayout? = null
    private  var linearLayoutSingleEleven:LinearLayout? = null
    private  var linearLayoutSingleTwelve:LinearLayout? = null
    private  var linearLayoutSingleThirteen:LinearLayout? = null
    private var relativeLayoutParent: RelativeLayout? = null
    private  var relativeLayoutLast:RelativeLayout? = null
    var layoutParams: RelativeLayout.LayoutParams? = null
    private var creditOne: EditText? = null
    private  var creditTwo:EditText? = null
    private  var creditThree:EditText? = null
    private  var creditFour:EditText? = null
    private  var creditFive:EditText? = null
    private  var creditSix:EditText? = null
    private  var creditSeven:EditText? = null
    private  var creditEight:EditText? = null
    private var creditNine: EditText? = null
    private  var creditTen:EditText? = null
    private  var creditEleven:EditText? = null
    private  var creditTwelve:EditText? = null
    private var sgpaOne: EditText? = null
    private  var sgpaTwo:EditText? = null
    private  var sgpaThree:EditText? = null
    private  var sgpaFour:EditText? = null
    private  var sgpaFive:EditText? = null
    private  var sgpaSix:EditText? = null
    private  var sgpaSeven:EditText? = null
    private  var sgpaEight:EditText? = null
    private var sgpaNine: EditText? = null
    private  var sgpaTen:EditText? = null
    private  var sgpaEleven:EditText? = null
    private  var sgpaTwelve:EditText? = null
    lateinit var back: ImageView
    private val binding by lazy{
      ActivityCgpaBinding.inflate(layoutInflater)
    }

    var i = 2
    var j = 1
    var credits = IntArray(11)
    var sgpas = DoubleArray(11)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

        layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        newSemesterButton!!.setOnClickListener {
            newButtonClicked() }

        calculateCgpaButton!!.setOnClickListener {
            if (creditOne?.text!!.isEmpty()  || sgpaTwo?.text!!.isEmpty()){
                Toast.makeText(this, "Please Enter Values", Toast.LENGTH_SHORT).show()
            }else{

            calculateCgpa()} }
        back.setOnClickListener {
            onBackPressed()
        }
        //displayNative()

    }
    private fun init() {
        back=findViewById(R.id.back_cgpa)
        relativeLayoutLast = findViewById(R.id.relativeLayoutLast)
        linearLayoutOne = findViewById(R.id.linearLayoutOne)
        linearLayoutSingleTwo = findViewById(R.id.linearLayoutSingleTwo)
        linearLayoutSingleThree = findViewById(R.id.linearLayoutSingleThree)
        linearLayoutSingleFour = findViewById(R.id.linearLayoutSingleFour)
        linearLayoutSingleFive = findViewById(R.id.linearLayoutSingleFive)
        linearLayoutSingleSix = findViewById(R.id.linearLayoutSingleSix)
        linearLayoutSingleSeven = findViewById(R.id.linearLayoutSingleSeven)
        linearLayoutSingleEight = findViewById(R.id.linearLayoutSingleEight)
        linearLayoutSingleNine = findViewById(R.id.linearLayoutSingleNine)
        linearLayoutSingleTen = findViewById(R.id.linearLayoutSingleTen)
        linearLayoutSingleEleven = findViewById(R.id.linearLayoutSingleEleven)
        linearLayoutSingleTwelve = findViewById(R.id.linearLayoutSingleTwelve)
        linearLayoutSingleThirteen = findViewById(R.id.linearLayoutSingleThirteen)
        newSemesterButton = findViewById(R.id.newSemesterButton)
        calculateCgpaButton = findViewById(R.id.calculateCgpaButton)
        relativeLayoutParent = findViewById(R.id.relativeLayoutParent)
        creditOne = findViewById(R.id.creditOne)
        creditTwo = findViewById(R.id.creditTwo)
        creditThree = findViewById(R.id.creditThree)
        creditFour = findViewById(R.id.creditFour)
        creditFive = findViewById(R.id.creditFive)
        creditSix = findViewById(R.id.creditSix)
        creditSeven = findViewById(R.id.creditSeven)
        creditEight = findViewById(R.id.creditEight)
        creditNine = findViewById(R.id.creditNine)
        creditTen = findViewById(R.id.creditTen)
        creditEleven = findViewById(R.id.creditEleven)
        creditTwelve = findViewById(R.id.creditTwelve)
        sgpaOne = findViewById(R.id.sgpaOne)
        sgpaTwo = findViewById(R.id.sgpaTwo)
        sgpaThree = findViewById(R.id.sgpaThree)
        sgpaFour = findViewById(R.id.sgpaFour)
        sgpaFive = findViewById(R.id.sgpaFive)
        sgpaSix = findViewById(R.id.sgpaSix)
        sgpaSeven = findViewById(R.id.sgpaSeven)
        sgpaEight = findViewById(R.id.sgpaEight)
        sgpaNine = findViewById(R.id.sgpaNine)
        sgpaTen = findViewById(R.id.sgpaTen)
        sgpaEleven = findViewById(R.id.sgpaEleven)
        sgpaTwelve = findViewById(R.id.sgpaTwelve)
    }
    private fun calculateCgpa() {
        if (j == 1) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
        } else if (j == 2) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
        } else if (j == 3) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
        } else if (j == 4) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
        } else if (j == 5) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
        } else if (j == 6) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
            credits[5] = creditSix!!.text.toString().toInt()
            sgpas[5] = sgpaSix!!.text.toString().toDouble()
        } else if (j == 7) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
            credits[5] = creditSix!!.text.toString().toInt()
            sgpas[5] = sgpaSix!!.text.toString().toDouble()
            credits[6] = creditSeven!!.text.toString().toInt()
            sgpas[6] = sgpaSeven!!.text.toString().toDouble()
        } else if (j == 8) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
            credits[5] = creditSix!!.text.toString().toInt()
            sgpas[5] = sgpaSix!!.text.toString().toDouble()
            credits[6] = creditSeven!!.text.toString().toInt()
            sgpas[6] = sgpaSeven!!.text.toString().toDouble()
            credits[7] = creditEight!!.text.toString().toInt()
            sgpas[7] = sgpaEight!!.text.toString().toDouble()
        } else if (j == 9) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
            credits[5] = creditSix!!.text.toString().toInt()
            sgpas[5] = sgpaSix!!.text.toString().toDouble()
            credits[6] = creditSeven!!.text.toString().toInt()
            sgpas[6] = sgpaSeven!!.text.toString().toDouble()
            credits[7] = creditEight!!.text.toString().toInt()
            sgpas[7] = sgpaEight!!.text.toString().toDouble()
            credits[8] = creditNine!!.text.toString().toInt()
            sgpas[8] = sgpaNine!!.text.toString().toDouble()
        } else if (j == 10) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
            credits[5] = creditSix!!.text.toString().toInt()
            sgpas[5] = sgpaSix!!.text.toString().toDouble()
            credits[6] = creditSeven!!.text.toString().toInt()
            sgpas[6] = sgpaSeven!!.text.toString().toDouble()
            credits[7] = creditEight!!.text.toString().toInt()
            sgpas[7] = sgpaEight!!.text.toString().toDouble()
            credits[8] = creditNine!!.text.toString().toInt()
            sgpas[8] = sgpaNine!!.text.toString().toDouble()
            credits[9] = creditTen!!.text.toString().toInt()
            sgpas[9] = sgpaTen!!.text.toString().toDouble()
        } else if (j == 11) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
            credits[5] = creditSix!!.text.toString().toInt()
            sgpas[5] = sgpaSix!!.text.toString().toDouble()
            credits[6] = creditSeven!!.text.toString().toInt()
            sgpas[6] = sgpaSeven!!.text.toString().toDouble()
            credits[7] = creditEight!!.text.toString().toInt()
            sgpas[7] = sgpaEight!!.text.toString().toDouble()
            credits[8] = creditNine!!.text.toString().toInt()
            sgpas[8] = sgpaNine!!.text.toString().toDouble()
            credits[9] = creditTen!!.text.toString().toInt()
            sgpas[9] = sgpaTen!!.text.toString().toDouble()
            credits[10] = creditEleven!!.text.toString().toInt()
            sgpas[10] = sgpaEleven!!.text.toString().toDouble()
        } else if (j == 12) {
            credits[0] = creditOne!!.text.toString().toInt()
            sgpas[0] = sgpaOne!!.text.toString().toDouble()
            credits[1] = creditTwo!!.text.toString().toInt()
            sgpas[1] = sgpaTwo!!.text.toString().toDouble()
            credits[2] = creditThree!!.text.toString().toInt()
            sgpas[2] = sgpaThree!!.text.toString().toDouble()
            credits[3] = creditFour!!.text.toString().toInt()
            sgpas[3] = sgpaFour!!.text.toString().toDouble()
            credits[4] = creditFive!!.text.toString().toInt()
            sgpas[4] = sgpaFive!!.text.toString().toDouble()
            credits[5] = creditSix!!.text.toString().toInt()
            sgpas[5] = sgpaSix!!.text.toString().toDouble()
            credits[6] = creditSeven!!.text.toString().toInt()
            sgpas[6] = sgpaSeven!!.text.toString().toDouble()
            credits[7] = creditEight!!.text.toString().toInt()
            sgpas[7] = sgpaEight!!.text.toString().toDouble()
            credits[8] = creditNine!!.text.toString().toInt()
            sgpas[8] = sgpaNine!!.text.toString().toDouble()
            credits[9] = creditTen!!.text.toString().toInt()
            sgpas[9] = sgpaTen!!.text.toString().toDouble()
            credits[10] = creditEleven!!.text.toString().toInt()
            sgpas[10] = sgpaEleven!!.text.toString().toDouble()
            credits[11] = creditTwelve!!.text.toString().toInt()
            sgpas[11] = sgpaTwelve!!.text.toString().toDouble()
        }
        var totalCredit = 0
        var finalCgpa = 0.0
        var temp = 0.0
        var totalSgpa = 0.0
        var totalSemester = 0
        for (k in credits.indices) {
            if (credits[k] != 0 && sgpas[k] != 0.0) {
                temp += (credits[k] * sgpas[k])
                totalCredit += credits[k]
                totalSgpa += sgpas[k]
                totalSemester += 1
            }
        }
        finalCgpa = temp / totalCredit
        val dialogFragment = AlertDialogFragment()
        val args = Bundle()
        args.putDouble("totalSgpa", totalSgpa)
        args.putDouble("totalCgpa", finalCgpa)
        args.putDouble("totalCredit", totalCredit.toDouble())
        args.putInt("totalSemester", totalSemester)
        dialogFragment.arguments = args
        dialogFragment.show(supportFragmentManager, "dialog")
        Toast.makeText(this, finalCgpa.toString(), Toast.LENGTH_SHORT).show()
    }
    private fun newButtonClicked() {
        if (i > 12) {
            Toast.makeText(applicationContext, "Semester can't be more than 12", Toast.LENGTH_SHORT)
                .show()
        } else if (i == 2) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleThree)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleThree!!.visibility = View.VISIBLE
            i++
        } else if (i == 3) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleFour)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleFour!!.visibility = View.VISIBLE
            i++
        } else if (i == 4) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleFive)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleFive!!.visibility = View.VISIBLE
            i++
        } else if (i == 5) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleSix)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleSix!!.visibility = View.VISIBLE
            i++
        } else if (i == 6) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleSeven)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleSeven!!.visibility = View.VISIBLE
            i++
        } else if (i == 7) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleEight)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleEight!!.visibility = View.VISIBLE
            i++
        } else if (i == 8) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleNine)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleNine!!.visibility = View.VISIBLE
            i++
        } else if (i == 9) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleTen)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleTen!!.visibility = View.VISIBLE
            i++
        } else if (i == 10) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleEleven)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleEleven!!.visibility = View.VISIBLE
            i++
        } else if (i == 11) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleTwelve)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleTwelve!!.visibility = View.VISIBLE
            i++
        } else if (i == 12) {
            layoutParams!!.addRule(RelativeLayout.BELOW, R.id.linearLayoutSingleThirteen)
            relativeLayoutLast!!.layoutParams = layoutParams
            linearLayoutSingleThirteen!!.visibility = View.VISIBLE
            i++
        }
        j++
    }
}