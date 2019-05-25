package com.arp.mvvmbaseandroid.util

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

/*
* Top Level Functions to reduce Boilerplate
* */
const val BUNDLE_KEY = "bundle_intent"

fun <T> AppCompatActivity.startActivityExt(className: Class<T>, bundle: Bundle? = null) {
    val intent = Intent(this, className)
    bundle?.let {
        intent.putExtra(BUNDLE_KEY, it)
    }
    startActivity(intent)
}

fun String.getFormattedDate(dateFormat: String = "dd MMM yyyy , HH:mm aaa"): String {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val targetFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    val date = originalFormat.parse(this)
    return targetFormat.format(date)
}

/* Default : Fragment is replaced. */
//fun <R : BaseFragment> inflateFragment(fragmentManager: androidx.fragment.app.FragmentManager, res: Int, fragment: R, add: Boolean = false) {
//    val transaction = fragmentManager.beginTransaction()
//    if (add)
//        transaction.add(res, fragment)
//    else
//        transaction.replace(res, fragment)
//    transaction.commit()
//}
