package com.example.cloudbreastcancer.usecase

import android.content.Context
import com.example.breastcancerlocal.model.HeartDisease
import com.example.cloudbreastcancer.viewModel.ClassificationViewModel
import com.example.cloudbreastcancer.viewModel.HeartDiseaseCRUDViewModel

class DiagnoseBean (c: Context) {
    private var classificationViewModel: ClassificationViewModel = ClassificationViewModel.getInstance(c)
    private var crudViewModel: HeartDiseaseCRUDViewModel = HeartDiseaseCRUDViewModel.getInstance(c)

    private var classification = ""
    private var instanceClassification: HeartDisease? = null

    private var errors = ArrayList<String>()

    fun setclassification(classificationx: String) {
        classification = classificationx
    }

    fun resetData() {
        classification = ""
    }

    fun isdiagnoseerror(): Boolean {
        errors.clear()
        instanceClassification = crudViewModel.getHeartDiseaseByPK(classification)
        if (instanceClassification == null) {
            errors.add("classification must be a valid Classification id")
        }
        return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

    fun diagnose (): String {
        return classificationViewModel.classify(instanceClassification!!)
    }
}