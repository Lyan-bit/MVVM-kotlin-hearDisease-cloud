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
            if (pars.size == 0) {
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
            val x: HeartDisease = HeartDisease.HeartDiseaseIndex.get(id) ?: return false
            return true
        }

        fun getCachedInstance(id: String): HeartDisease? {
            return HeartDisease.HeartDiseaseIndex.get(id)
        }

    fun parseCSV(line: String?): HeartDisease? {
        if (line == null) {
            return null
        }
        val line1vals: ArrayList<String> = Ocl.tokeniseCSV(line)
        var HeartDiseasex: HeartDisease? = HeartDisease.HeartDiseaseIndex.get(line1vals[14])
        if (HeartDiseasex == null) {
            HeartDiseasex = HeartDisease.createByPKHeartDisease(line1vals[14])
        }
        HeartDiseasex.age = line1vals[0].toInt()
        HeartDiseasex.sex = line1vals[1].toInt()
        HeartDiseasex.cp = line1vals[2].toFloat()
        HeartDiseasex.trestbps = line1vals[3].toInt()
        HeartDiseasex.chol = line1vals[4].toInt()
        HeartDiseasex.fbs = line1vals[5].toInt()
        HeartDiseasex.restecg = line1vals[6].toInt()
        HeartDiseasex.thalach = line1vals[7].toInt()
        HeartDiseasex.exang = line1vals[8].toInt()
        HeartDiseasex.oldpeak = line1vals[9].toInt()
        HeartDiseasex.slope = line1vals[10].toInt()
        HeartDiseasex.ca = line1vals[11].toInt()
        HeartDiseasex.thal = line1vals[12].toInt()
        HeartDiseasex.outcome = line1vals[13]
        HeartDiseasex.id = line1vals[14]
        return HeartDiseasex
    }


        fun parseJSON(obj: JSONObject?): HeartDisease? {
            return if (obj == null) {
                null
            } else try {
                val id = obj.getString("id")
                var HeartDiseasex: HeartDisease? = HeartDisease.HeartDiseaseIndex.get(id)
                if (HeartDiseasex == null) {
                    HeartDiseasex = HeartDisease.createByPKHeartDisease(id)
                }
                HeartDiseasex.age = obj.getDouble("age").toInt()
                HeartDiseasex.sex = obj.getDouble("sex").toInt()
                HeartDiseasex.cp = obj.getDouble("cp").toFloat()
                HeartDiseasex.trestbps = obj.getDouble("trestbps").toInt()
                HeartDiseasex.chol = obj.getDouble("chol").toInt()
                HeartDiseasex.fbs = obj.getDouble("fbs").toInt()
                HeartDiseasex.restecg = obj.getDouble("restecg").toInt()
                HeartDiseasex.thalach = obj.getDouble("thalach").toInt()
                HeartDiseasex.exang = obj.getDouble("exang").toInt()
                HeartDiseasex.oldpeak = obj.getDouble("oldpeak").toInt()
                HeartDiseasex.slope = obj.getDouble("slope").toInt()
                HeartDiseasex.ca = obj.getDouble("ca").toInt()
                HeartDiseasex.thal = obj.getDouble("thal").toInt()
                HeartDiseasex.outcome = obj.getString("outcome")
                HeartDiseasex.id = obj.getString("id")
                HeartDiseasex
            } catch (e: Exception) {
                null
            }
        }

    fun makeFromCSV(lines: String?): ArrayList<HeartDisease> {
        val result: ArrayList<HeartDisease> = ArrayList<HeartDisease>()
        if (lines == null) {
            return result
        }
        val rows: ArrayList<String> = Ocl.parseCSVtable(lines)
        for (item in rows.indices) {
            val row = rows[item]
            if (row == null || row.trim { it <= ' ' }.length == 0) {
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
                var HeartDiseasex: HeartDisease? = HeartDisease.HeartDiseaseIndex.get(id)
                if (HeartDiseasex == null) {
                    HeartDiseasex = HeartDisease.createByPKHeartDisease(id)
                }
                HeartDiseasex.age = (map["age"]as Long?)!!.toLong().toInt()
                HeartDiseasex.sex = (map["sex"] as Long?)!!.toLong().toInt()
                HeartDiseasex.cp = (map["cp"]as Long?)!!.toLong().toFloat()
                HeartDiseasex.trestbps = (map["trestbps"] as Long?)!!.toLong().toInt()
                HeartDiseasex.chol = (map["chol"]as Long?)!!.toLong().toInt()
                HeartDiseasex.fbs = (map["fbs"] as Long?)!!.toLong().toInt()
                HeartDiseasex.restecg = (map["restecg"]as Long?)!!.toLong().toInt()
                HeartDiseasex.thalach = (map["thalach"] as Long?)!!.toLong().toInt()
                HeartDiseasex.exang = (map["exang"]as Long?)!!.toLong().toInt()

                HeartDiseasex.oldpeak = (map["oldpeak"]as Long?)!!.toLong().toInt()
                HeartDiseasex.slope = (map["slope"]as Long?)!!.toLong().toInt()
                HeartDiseasex.ca = (map["ca"]as Long?)!!.toLong().toInt()
                HeartDiseasex.thal = (map["thal"]as Long?)!!.toLong().toInt()
                HeartDiseasex.outcome = map["outcome"].toString()
                HeartDiseasex.id = map["id"].toString()
                return HeartDiseasex
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