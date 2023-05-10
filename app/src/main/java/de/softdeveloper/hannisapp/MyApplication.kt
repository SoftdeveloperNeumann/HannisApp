package de.softdeveloper.hannisapp

import android.app.Application

class MyApplication: Application() {
    var counter:Int = 0

    val a1 = arrayOfNulls<Int>(6)
    val a2 = arrayOfNulls<Int>(6)
    val a3 = arrayOfNulls<Int>(6)
    val a4 = arrayOfNulls<Int>(6)
    val a5 = arrayOfNulls<Int>(2)


    val array = arrayOf(a1, a2, a3, a4, a5,)

    override fun onCreate() {
        super.onCreate()

        a1[0] = R.drawable.image1
        a1[1] = R.drawable.image2
        a1[2] = R.drawable.image3
        a1[3] = R.drawable.image4
        a1[4] = R.drawable.image5
        a1[5] = R.drawable.image6

        a2[0] = R.drawable.image7
        a2[1] = R.drawable.image8
        a2[2] = R.drawable.image9
        a2[3] = R.drawable.image10
        a2[4] = R.drawable.image11
        a2[5] = R.drawable.image12

        a3[0] = R.drawable.image6
        a3[1] = R.drawable.image4
        a3[2] = R.drawable.image5
        a3[3] = R.drawable.image5
        a3[4] = R.drawable.image5
        a3[5] = R.drawable.image5

        a4[0] = R.drawable.image1
        a4[1] = R.drawable.image2
        a4[2] = R.drawable.image3
        a4[3] = R.drawable.image4
        a4[4] = R.drawable.image5
        a4[5] = R.drawable.image6

        a5[0] = R.drawable.image7
        a5[1] = R.drawable.image8
//        a5[3] = R.drawable.image9
//        a5[4] = R.drawable.image10

//        a6[0] = R.drawable.image11
//        a6[1] = R.drawable.image12
//        a6[2] = R.drawable.image3
//        a6[3] = R.drawable.image4
//        a6[4] = R.drawable.image5


    }
}