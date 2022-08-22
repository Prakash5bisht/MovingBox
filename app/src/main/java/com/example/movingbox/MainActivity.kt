package com.example.movingbox

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import com.example.movingbox.databinding.ActivityMainBinding
import com.example.movingbox.databinding.FragmentBoxBinding
import java.lang.Exception
import kotlin.with as kotlinWith

val TAG = "prakash"
class MainActivity : AppCompatActivity() {

    var frag = Box()
    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        var count : Int = 1

        val list = mutableListOf<Box>()

        mainBinding.addBox.setOnClickListener {

            list.add(Box.newInstance(count.toString(),list.size))

            supportFragmentManager.beginTransaction().apply {
                add(R.id.container, list[list.size-1])
                commit()
            }


            count += 1
        }


        mainBinding.up.setOnClickListener {
            BoxAction.box.move(Direction.UP)
        }

        mainBinding.down.setOnClickListener {
            BoxAction.box.move(Direction.DOWN)
//            val params = mainBinding.container.layoutParams as ViewGroup.MarginLayoutParams
//            Log.d(TAG, params.width.toString())
        }

        mainBinding.left.setOnClickListener {
            BoxAction.box.move(Direction.LEFT)
        }

        mainBinding.right.setOnClickListener {
            Log.d(TAG, "right")

            BoxAction.box.move(Direction.RIGHT)
        }



    }

}