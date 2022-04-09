package com.development.nest.studyshechdular.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.development.nest.studyshechdular.R

class AlertDialogFragment : DialogFragment() {

    var textViewTotalSemester: TextView? = null
    var textViewTotalSgpa: TextView? = null
    var textViewTotalCredit: TextView? = null
    var textViewTotalCgpa: TextView? = null
    var buttonOk: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_alert_dialog, container, false)
        textViewTotalCgpa = view!!.findViewById<TextView>(R.id.textViewTotalCgpa)
        textViewTotalSemester = view!!.findViewById<TextView>(R.id.textViewTotalSemester)
        textViewTotalCredit = view!!.findViewById<TextView>(R.id.textViewTotalCredit)
        textViewTotalSgpa = view!!.findViewById<TextView>(R.id.textViewTotalSgpa)
        textViewTotalCgpa!!.setText(
            java.lang.String.format(
                "%.2f",
                requireArguments().getDouble("totalCgpa")
            )
        )
        textViewTotalSemester!!.setText(Integer.toString(requireArguments().getInt("totalSemester")))
        textViewTotalSgpa!!.setText(java.lang.Double.toString(requireArguments().getDouble("totalSgpa")))
        textViewTotalCredit!!.setText(java.lang.Double.toString(requireArguments().getDouble("totalCredit")))
        buttonOk = view!!.findViewById<Button>(R.id.buttonOk)
        buttonOk!!.setOnClickListener(View.OnClickListener { dismiss() })

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            AlertDialogFragment().apply {
            }
    }
}