package com.example.cloudbreastcancer.viewModel

import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import com.example.breastcancerlocal.model.HeartDisease
import com.example.breastcancerlocal.model.HeartDiseaseVO
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class ClassificationViewModel (context: Context): ViewModel() {

    private val assetManager: AssetManager = context.assets
    private val crudViewModel = HeartDiseaseCRUDViewModel.getInstance(context)

    companion object {
        private var instance: ClassificationViewModel? = null
        fun getInstance(context: Context): ClassificationViewModel {
            return instance ?: ClassificationViewModel(context)
        }
    }

    //classification
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): ByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset, declaredLength)
    }

    fun classify(classification: HeartDisease) : String {
        var res : String
        lateinit var tflite : Interpreter
        lateinit var tflitemodel : ByteBuffer

        try{
            tflitemodel = loadModelFile(assetManager, "Heart_model_13F.tflite")
            tflite = Interpreter(tflitemodel)
        } catch(ex: Exception){
            ex.printStackTrace()
        }

        val done: Float = (classification.age.toFloat() - 29) / (77 - 29)
        val dtwo = (classification.sex ).toFloat()
        val dthree = ((classification.cp - 0) / (3 - 0)).toFloat()
        val dfour = ((classification.trestbps - 94) / (200 - 94)).toFloat()
        val dfive = ((classification.chol - 126) / (564 - 126)).toFloat()
        val dsix = (classification.fbs).toFloat()
        val dseven = ((classification.restecg - 0) / (2 - 0)).toFloat()
        val deight = ((classification.thalach - 71) / (202 - 71)).toFloat()
        val dnine = (classification.exang).toFloat()
        val dten = ((classification.oldpeak - 0) / (6.2 - 0)).toFloat()
        val deleven = ((classification.slope - 0) / (2 - 0)).toFloat()
        val dtwelve = ((classification.ca - 0) / (4 - 0)).toFloat()
        val dthirteen = ((classification.thal - 0) / (3 - 0)).toFloat()

        val inputVal: FloatArray = floatArrayOf(done, dtwo, dthree, dfour, dfive, dsix, dseven,deight, dnine, dten, deleven, dtwelve, dthirteen)
        val outputVal: ByteBuffer = ByteBuffer.allocateDirect(8)
        outputVal.order(ByteOrder.nativeOrder())

        tflite.run(inputVal, outputVal)
        outputVal.rewind()

        val result = FloatArray(2)
        for (i in 0..1) {
            result[i] = outputVal.float
        }

        if (result[0]>result[1])
            res = "Result is negative"

        else
            res = "Result is positive"

        classification.outcome = res
        crudViewModel.editHeartDisease(HeartDiseaseVO( classification))

        return res

    }
}
