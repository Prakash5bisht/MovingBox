package com.example.movingbox

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.movingbox.databinding.FragmentBoxBinding


class Box : Fragment() {

    private val TAG = "Box"
    private var horizontalMargin : Int = 0
    private var verticalMargin : Int = 0

    private var text: String? = null
    private var totalBoxes : Int = 0
    private lateinit var boxBinding: FragmentBoxBinding



    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       boxBinding = FragmentBoxBinding.inflate(layoutInflater)
        boxBinding.message.text = text

        boxBinding.root.setOnClickListener{
            BoxAction.box = this
        }

        return boxBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var params = boxBinding.box.layoutParams as ViewGroup.MarginLayoutParams
        params.width = 100
        params.height = 100


        addToEnd(totalBoxes)

    }


    fun showId(id : Int){
        Log.d("Prakash", id.toString())
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: Int) =
            Box().apply {
                text = param1
                totalBoxes = param2
            }
    }

    fun move(direction: Direction){

        if(this::boxBinding.isInitialized){

            when(direction){

                Direction.LEFT -> {
                    if(horizontalMargin != 0){
                        horizontalMargin -= 100
                    }
                }

                Direction.RIGHT ->{
                    if(horizontalMargin < 500){
                        horizontalMargin += 100
                    }
                }

                Direction.UP -> {
                    if(verticalMargin != 0){
                        verticalMargin -= 100
                    }
                }

                else ->{
                    if(verticalMargin < 500){
                        verticalMargin += 100
                    }
                }
            }

            val params = boxBinding.box.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(horizontalMargin,verticalMargin,0,0)
            boxBinding.box.layoutParams = params
        }

    }

    private fun addToEnd(totalBoxes : Int){
        Log.d(TAG, "addedToEnd")
        Log.d(TAG, "total boxes : ${totalBoxes.toString()}")

            this.totalBoxes = totalBoxes
            horizontalMargin = totalBoxes * 100

            var params = boxBinding.box.layoutParams as ViewGroup.MarginLayoutParams

            params.setMargins(horizontalMargin,0,0,0)

            boxBinding.box.layoutParams = params

    }

}