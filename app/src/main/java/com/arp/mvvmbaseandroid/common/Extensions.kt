package com.arp.mvvmbaseandroid.common

import java.text.SimpleDateFormat
import java.util.*

/*
* Top Level Functions to reduce Boilerplate
* */

//fun <T : BaseActivity> launchActivity(context: Context, className: Class<T>, bundle: Bundle? = null) {
//    launchActivity(context, Intent(context, className), bundle)
//}
//
//fun launchActivity(context: Context, intent: Intent, bundle: Bundle? = null) {
//    context.startActivity(intent, bundle)
//}

/* Default : Fragment is replaced. */
//fun <R : BaseFragment> inflateFragment(fragmentManager: androidx.fragment.app.FragmentManager, res: Int, fragment: R, add: Boolean = false) {
//    val transaction = fragmentManager.beginTransaction()
//    if (add)
//        transaction.add(res, fragment)
//    else
//        transaction.replace(res, fragment)
//    transaction.commit()
//}

fun String.getFormattedDate(dateFormat: String = "dd MMM yyyy , HH:mm aaa"): String {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val targetFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    val date = originalFormat.parse(this)
    return targetFormat.format(date)
}
