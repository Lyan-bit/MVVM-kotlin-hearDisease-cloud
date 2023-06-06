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
            res.add(currentHeartDiseases[x].getId() + "")
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
        var obj = getHeartDiseaseByPK(x.getId())
        if (obj == null) {
            obj = HeartDisease.createByPKHeartDisease(x.getId())
        }
        obj.age = x.getAge()
        obj.sex = x.getSex()
        obj.cp = x.getCp()
        obj.trestbps = x.getTrestbps()
        obj.chol = x.getChol()
        obj.fbs = x.getFbs()
        obj.restecg = x.getRestecg()
        obj.thalach = x.getThalach()
        obj.exang = x.getExang()
        obj.oldpeak = x.getOldpeak()
        obj.slope = x.getSlope()
        obj.ca = x.getCa()
        obj.thal = x.getThal()
        obj.outcome = x.getOutcome()
        obj.id = x.getId()
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
