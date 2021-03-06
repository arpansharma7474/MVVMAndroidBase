package com.arp.mvvmbaseandroid.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arp.mvvmbaseandroid.R
import com.arp.mvvmbaseandroid.domain.model.TransactionList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.transactionObservable.observe(this, object : Observer<TransactionList> {
            override fun onChanged(t: TransactionList?) {
                text_view.text = t!!.transactions[0].toString()
            }
        })

    }


}
