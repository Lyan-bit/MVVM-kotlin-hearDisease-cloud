package com.example.cloudbreastcancer.cloudDatabase

import com.example.breastcancerlocal.model.HeartDisease
import com.example.cloudbreastcancer.viewModel.Ocl
import org.json.JSONObject
import java.lang.Exception
import org.json.JSONArray
import kotlin.collections.ArrayList

class HeartDiseaseDAO {

    companion object {

        fun getURL(command: String?, pars: ArrayList<String>, values: ArrayList<String>): String {
            var res = "base url for the data source"
            if (command != null) {
                res += command
            }
            if (pars.isNotEmpty()) {
                return res
            }
            res = "$res?"
            for (item in pars.indices) {
                val par = pars[item]
                val vals = values[item]
                res = "$res$par=$vals"
                if (item < pars.size - 1) {
                    res = "$res&"
                }
            }
            return res
        }

        fun isCached(id: String?): Boolean {
             HeartDisease.HeartDiseaseIndex[id] ?: return false
            return true
        }

        fun getCachedInstance(id: String): HeartDisease? {
            return HeartDisease.HeartDiseaseIndex[id]
        }

    fun parseCSV(line: String?): HeartDisease? {
        if (line == null) {
            return null
        }
        val line1vals: List<String> = Ocl.tokeniseCSV(line)
        var heartDiseasex: HeartDisease? = HeartDisease.HeartDiseaseIndex[line1vals[14]]
        if (heartDiseasex == null) {
            heartDiseasex = HeartDisease.createByPKHeartDisease(line1vals[14])
        }
        heartDiseasex.age = line1vals[0].toInt()
        heartDiseasex.sex = line1vals[1].toInt()
        heartDiseasex.cp = line1vals[2].toFloat()
        heartDiseasex.trestbps = line1vals[3].toInt()
        heartDiseasex.chol = line1vals[4].toInt()
        heartDiseasex.fbs = line1vals[5].toInt()
        heartDiseasex.restecg = line1vals[6].toInt()
        heartDiseasex.thalach = line1vals[7].toInt()
        heartDiseasex.exang = line1vals[8].toInt()
        heartDiseasex.oldpeak = line1vals[9].toInt()
        heartDiseasex.slope = line1vals[10].toInt()
        heartDiseasex.ca = line1vals[11].toInt()
        heartDiseasex.thal = line1vals[12].toInt()
        heartDiseasex.outcome = line1vals[13]
        heartDiseasex.id = line1vals[14]
        return heartDiseasex
    }


        fun parseJSON(obj: JSONObject?): HeartDisease? {
            return if (obj == null) {
                null
            } else try {
                val id = obj.getString("id")
                var heartDiseasex: HeartDisease? = HeartDisease.HeartDiseaseIndex[id]
                if (heartDiseasex == null) {
                    heartDiseasex = HeartDisease.createByPKHeartDisease(id)
                }
                heartDiseasex.age = obj.getDouble("age").toInt()
                heartDiseasex.sex = obj.getDouble("sex").toInt()
                heartDiseasex.cp = obj.getDouble("cp").toFloat()
                heartDiseasex.trestbps = obj.getDouble("trestbps").toInt()
                heartDiseasex.chol = obj.getDouble("chol").toInt()
                heartDiseasex.fbs = obj.getDouble("fbs").toInt()
                heartDiseasex.restecg = obj.getDouble("restecg").toInt()
                heartDiseasex.thalach = obj.getDouble("thalach").toInt()
                heartDiseasex.exang = obj.getDouble("exang").toInt()
                heartDiseasex.oldpeak = obj.getDouble("oldpeak").toInt()
                heartDiseasex.slope = obj.getDouble("slope").toInt()
                heartDiseasex.ca = obj.getDouble("ca").toInt()
                heartDiseasex.thal = obj.getDouble("thal").toInt()
                heartDiseasex.outcome = obj.getString("outcome")
                heartDiseasex.id = obj.getString("id")
                heartDiseasex
            } catch (e: Exception) {
                null
            }
        }

    fun makeFromCSV(lines: String?): ArrayList<HeartDisease> {
        val result: ArrayList<HeartDisease> = ArrayList<HeartDisease>()
        if (lines == null) {
            return result
        }
        val rows: List<String> = Ocl.parseCSVtable(lines)
        for (item in rows.indices) {
            val row = rows[item]
            if (row == null || row.trim { it <= ' ' }.isNotEmpty()) {
                //trim
            } else {
                val x: HeartDisease? = parseCSV(row)
                if (x != null) {
                    result.add(x)
                }
            }
        }
        return result
    }


        fun parseJSONArray(jarray: JSONArray?): ArrayList<HeartDisease>? {
            if (jarray == null) {
                return null
            }
            val res: ArrayList<HeartDisease> = ArrayList<HeartDisease>()
            val len = jarray.length()
            for (i in 0 until len) {
                try {
                    val x = jarray.getJSONObject(i)
                    if (x != null) {
                        val y: HeartDisease? = parseJSON(x)
                        if (y != null) {
                            res.add(y)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return res
        }


        fun writeJSON(x: HeartDisease): JSONObject? {
            val result = JSONObject()
            try {
                result.put("age", x.age)
                result.put("sex", x.sex)
                result.put("cp", x.cp)
                result.put("trestbps", x.trestbps)
                result.put("chol", x.chol)
                result.put("fbs", x.fbs)
                result.put("restecg", x.restecg)
                result.put("thalach", x.thalach)
                result.put("exang", x.exang)
                result.put("oldpeak", x.oldpeak)
                result.put("slope", x.slope)
                result.put("ca", x.ca)
                result.put("thal", x.thal)
                result.put("outcome", x.outcome)
                result.put("id", x.id)
            } catch (e: Exception) {
                return null
            }
            return result
        }


        fun parseRaw(obj: Any?): HeartDisease? {
             if (obj == null) {
                 return null
            }
            try {
                val map = obj as HashMap<String, Object>
                val id: String = map["id"].toString()
                var heartDiseasex: HeartDisease? = HeartDisease.HeartDiseaseIndex[id]
                if (heartDiseasex == null) {
                    heartDiseasex = HeartDisease.createByPKHeartDisease(id)
                }
                heartDiseasex.age = (map["age"]as Long?)!!.toLong().toInt()
                heartDiseasex.sex = (map["sex"] as Long?)!!.toLong().toInt()
                heartDiseasex.cp = (map["cp"]as Long?)!!.toLong().toFloat()
                heartDiseasex.trestbps = (map["trestbps"] as Long?)!!.toLong().toInt()
                heartDiseasex.chol = (map["chol"]as Long?)!!.toLong().toInt()
                heartDiseasex.fbs = (map["fbs"] as Long?)!!.toLong().toInt()
                heartDiseasex.restecg = (map["restecg"]as Long?)!!.toLong().toInt()
                heartDiseasex.thalach = (map["thalach"] as Long?)!!.toLong().toInt()
                heartDiseasex.exang = (map["exang"]as Long?)!!.toLong().toInt()

                heartDiseasex.oldpeak = (map["oldpeak"]as Long?)!!.toLong().toInt()
                heartDiseasex.slope = (map["slope"]as Long?)!!.toLong().toInt()
                heartDiseasex.ca = (map["ca"]as Long?)!!.toLong().toInt()
                heartDiseasex.thal = (map["thal"]as Long?)!!.toLong().toInt()
                heartDiseasex.outcome = map["outcome"].toString()
                heartDiseasex.id = map["id"].toString()
                return heartDiseasex
            } catch (e: Exception) {
                return null
            }
        }

        fun writeJSONArray(es: ArrayList<HeartDisease>): JSONArray {
            val result = JSONArray()
            for (i in 0 until es.size) {
                val ex: HeartDisease = es[i]
                val jx = writeJSON(ex)
                if (jx == null) {
                    //null
                } else {
                    try {
                        result.put(jx)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return result
        }
    }
}
