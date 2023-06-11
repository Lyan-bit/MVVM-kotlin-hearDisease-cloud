package com.example.cloudbreastcancer.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.breastcancerlocal.model.HeartDisease
import com.example.breastcancerlocal.model.HeartDiseaseVO
import com.example.cloudbreastcancer.cloudDatabase.FirebaseDbi

class HeartDiseaseCRUDViewModel (context: Context): ViewModel() {

    private var cdb: FirebaseDbi = FirebaseDbi.getInstance()

    companion object {
        private var instance: HeartDiseaseCRUDViewModel? = null
        fun getInstance(context: Context): HeartDiseaseCRUDViewModel {
            return instance ?: HeartDiseaseCRUDViewModel(context)
        }
    }
    
    private var currentHeartDisease: HeartDiseaseVO? = null
    private var currentHeartDiseases: ArrayList<HeartDiseaseVO> = ArrayList()

    fun listHeartDisease(): ArrayList<HeartDiseaseVO> {
        val heartDiseases: ArrayList<HeartDisease> = HeartDisease.HeartDiseaseAllInstances
        currentHeartDiseases.clear()
        for (i in heartDiseases.indices) {
            currentHeartDiseases.add(HeartDiseaseVO(heartDiseases[i]))
        }
        return currentHeartDiseases
    }

    fun stringListHeartDisease(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentHeartDiseases.indices) {
            res.add(currentHeartDiseases[x].toString() + "")
        }
        return res
    }

    fun getHeartDiseaseByPK(value: String): HeartDisease? {
        return HeartDisease.HeartDiseaseIndex[value]
    }

    fun retrieveHeartDisease(value: String): HeartDisease? {
        return getHeartDiseaseByPK(value)
    }

    fun allHeartDiseaseids(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentHeartDiseases.indices) {
            res.add(currentHeartDiseases[x].id + "")
        }
        return res
    }

    fun setSelectedHeartDisease(x: HeartDiseaseVO) {
        currentHeartDisease = x
    }

    fun setSelectedHeartDisease(i: Int) {
        if (i < currentHeartDiseases.size) {
            currentHeartDisease = currentHeartDiseases[i]
        }
    }

    fun getSelectedHeartDisease(): HeartDiseaseVO? {
        return currentHeartDisease
    }

    fun persistHeartDisease(x: HeartDisease) {
        val vo = HeartDiseaseVO(x)
        cdb.persistHeartDisease(x)
        currentHeartDisease = vo
    }

    fun editHeartDisease(x: HeartDiseaseVO) {
        var obj = getHeartDiseaseByPK(x.id)
        if (obj == null) {
            obj = HeartDisease.createByPKHeartDisease(x.id)
        }
        obj.age = x.age
        obj.sex = x.sex
        obj.cp = x.cp
        obj.trestbps = x.trestbps
        obj.chol = x.chol
        obj.fbs = x.fbs
        obj.restecg = x.restecg
        obj.thalach = x.thalach
        obj.exang = x.exang
        obj.oldpeak = x.oldpeak
        obj.slope = x.slope
        obj.ca = x.ca
        obj.thal = x.thal
        obj.outcome = x.outcome
        obj.id = x.id
        cdb.persistHeartDisease(obj)
        currentHeartDisease = x
    }

    fun createHeartDisease(x: HeartDiseaseVO) {
        editHeartDisease(x)
    }

    fun deleteHeartDisease(id: String) {
        val obj = getHeartDiseaseByPK(id)
        if (obj != null) {
            cdb.deleteHeartDisease(obj)
            HeartDisease.killHeartDisease(id)
        }
        currentHeartDisease = null
    }
}
