package com.example.cloudbreastcancer.cloudDatabase

import com.example.breastcancerlocal.model.HeartDisease
import com.example.breastcancerlocal.model.HeartDiseaseVO
import com.google.firebase.database.*

class FirebaseDbi() {

    var database: DatabaseReference? = null

    companion object {
        private var instance: FirebaseDbi? = null
        fun getInstance(): FirebaseDbi {
            return instance ?: FirebaseDbi()
        }
    }

    init {
        connectByURL("https://breastcancer-3d45c-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    fun connectByURL(url: String) {
        database = FirebaseDatabase.getInstance(url).reference
        if (database == null) {
            return
        }
        val heartDiseaseListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get instances from the cloud database
                val heartDisease = dataSnapshot.value as Map<String, Object>?
                if (heartDisease != null) {
                    val keys = heartDisease.keys
                    for (key in keys) {
                        val x = heartDisease[key]
                        HeartDiseaseDAO.parseRaw(x)
                    }
                    // Delete local objects which are not in the cloud:
                    val locals = ArrayList<HeartDisease>()
                    locals.addAll(HeartDisease.HeartDiseaseAllInstances)
                    for (x in locals) {
                        if (keys.contains(x.id)) {
                            //check
                        } else {
                            HeartDisease.killHeartDisease(x.id)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            //cancel
            }
        }
        database!!.child("heartDiseases").addValueEventListener(heartDiseaseListener)
    }

    fun persistHeartDisease(ex: HeartDisease) {
        val evo = HeartDiseaseVO(ex)
        val key = evo.getId()
        if (database == null) {
            return
        }
        database!!.child("heartDiseases").child(key).setValue(evo)
    }

    fun deleteHeartDisease(ex: HeartDisease) {
        val key: String = ex.id
        if (database == null) {
            return
        }
        database!!.child("heartDiseases").child(key).removeValue()
    }
}
