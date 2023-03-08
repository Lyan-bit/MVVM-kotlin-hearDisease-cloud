package com.example.cloudbreastcancer.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cloudbreastcancer.R
import com.example.cloudbreastcancer.model.HeartDiseaseBean
import java.lang.Exception

class CreateFragment : Fragment(), View.OnClickListener {
    //Object declaration
    private lateinit var root: View
    private lateinit var myContext: Context
    private lateinit var classificationBean: HeartDiseaseBean

    lateinit var input1: EditText
    var input1Data: String = ""
    lateinit var input2: EditText
    var input2Data:  String = ""
    lateinit var input3: EditText
    var input3Data:  String = ""
    lateinit var input4: EditText
    var input4Data:  String = ""
    lateinit var input5: EditText
    var input5Data:  String = ""
    lateinit var input6: EditText
    var input6Data:  String = ""
    lateinit var input7: EditText
    var input7Data:  String = ""
    lateinit var input8: EditText
    var input8Data:  String = ""
    lateinit var input9: EditText
    var input9Data:  String = ""
    lateinit var input10: EditText
    var input10Data:  String = ""
    lateinit var input11: EditText
    var input11Data:  String = ""
    lateinit var input12: EditText
    var input12Data:  String = ""
    lateinit var input13: EditText
    var input13Data:  String = ""
    lateinit var input14: EditText
    var input14Data:  String = ""

    lateinit var resultTextView : TextView
    lateinit var buttonSave: Button
    lateinit var buttonCancel: Button

    companion object {
        fun newInstance(c: Context): CreateFragment {
            val fragment = CreateFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        root = inflater.inflate(R.layout.create_layout, container, false)

        classificationBean = HeartDiseaseBean(myContext)

        //UI components declaration
        input1 = root.findViewById(R.id.idField)
        input2 = root.findViewById(R.id.ageField)
        input3 = root.findViewById(R.id.genderField)
        input4 = root.findViewById(R.id.chestPainField)
        input5 = root.findViewById(R.id.trestbpsField)
        input6 = root.findViewById(R.id.cholestoralField)
        input7 = root.findViewById(R.id.fbsField)
        input8 = root.findViewById(R.id.restecgField)
        input9 = root.findViewById(R.id.thalachField)
        input10 = root.findViewById(R.id.exangField)
        input11 = root.findViewById(R.id.oldPeakField)
        input12 = root.findViewById(R.id.slopeField)
        input13 = root.findViewById(R.id.caField)
        input14 = root.findViewById(R.id.thalField)

        resultTextView = root.findViewById(R.id.result)

        buttonSave = root.findViewById(R.id.diagnoseCreate)
        buttonSave.setOnClickListener(this)

        buttonCancel = root.findViewById(R.id.diagnoseCancel)
        buttonCancel.setOnClickListener(this)

        return root

    }

    override fun onClick(v: View) {
        val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        when (v.id) {
            R.id.diagnoseCreate -> {
                createOK()
            }
            R.id.diagnoseCancel -> {
                createCancel()
            }
        }
    }

    private fun createOK () {
        input1Data = input1.text.toString()
        classificationBean.setId(input1Data)
        input2Data = input2.text.toString()
        classificationBean.setAge(input2Data)
        input3Data = input3.text.toString()
        classificationBean.setSex(input3Data)
        input4Data = input4.text.toString()
        classificationBean.setCp(input4Data)
        input5Data = input5.text.toString()
        classificationBean.setTrestbps(input5Data)
        input6Data = input6.text.toString()
        classificationBean.setChol(input6Data)
        input7Data = input7.text.toString()
        classificationBean.setFbs(input7Data)
        input8Data = input8.text.toString()
        classificationBean.setRestecg(input8Data)
        input9Data = input9.text.toString()
        classificationBean.setThalach(input9Data)
        input10Data = input10.text.toString()
        classificationBean.setExang(input10Data)
        input11Data = input11.text.toString()
        classificationBean.setOldpeak(input11Data)
        input12Data = input12.text.toString()
        classificationBean.setSlope(input12Data)
        input13Data = input13.text.toString()
        classificationBean.setCa(input13Data)
        input14Data = input14.text.toString()
        classificationBean.setThal(input14Data)
        classificationBean.setOutcome(resultTextView.text.toString())

        if (classificationBean.isCreateHeartDiseaseError()) {
            Log.w(javaClass.name, classificationBean.errors())
            Toast.makeText(myContext, "Errors: " + classificationBean.errors(), Toast.LENGTH_LONG).show()
        } else {
            classificationBean.createHeartDisease()
        }

    }

    private fun createCancel () {
        classificationBean.resetData()
        input1.text.clear()
        input2.text.clear()
        input3.text.clear()
        input4.text.clear()
        input5.text.clear()
        input6.text.clear()
        input7.text.clear()
        input8.text.clear()
        input9.text.clear()
        input10.text.clear()
        resultTextView.text = ""
    }
}
