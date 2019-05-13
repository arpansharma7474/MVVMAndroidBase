package com.arp.mvvmbaseandroid.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arp.mvvmbaseandroid.R
import com.arp.mvvmbaseandroid.common.startActivityExt
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.transactionObservable.observe(this,
            Observer<TransactionList> {
                text_view.text = it!!.transactions[0].toString()
            })

        viewModel.error.observe(this,
            Observer<Throwable> {
                Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_LONG).show()
            })

    }


}
