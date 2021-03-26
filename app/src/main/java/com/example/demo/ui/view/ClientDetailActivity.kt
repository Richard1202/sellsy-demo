package com.example.demo.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.data.model.Client
import kotlinx.android.synthetic.main.activity_client_detail.*

class ClientDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_detail)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.title = "Detail"
        }

        val client: Client = intent.getParcelableExtra(CLIENT)

        Glide.with(this).load(client.picture)
            .circleCrop()
            .into(imageView)

        name.text = client.fullName.trim()
        join_date.text = client.joinDate.trim()

        telephone.text = client.telephone
        mobile.text = client.mobile
        email.text = client.email

        billing_address.text = client.billingAddr1
        billing_city.text = client.billingTown
        billing_state.text = client.billingState
        billing_country.text = client.billingCountry

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> {
            }
        }
        return true
    }

    companion object {
        private const val CLIENT = "client"

        fun startActivity(context: Context?, client: Client) = context?.startActivity(
            Intent(context, ClientDetailActivity::class.java).apply {
                putExtra(CLIENT, client)
            }
        )
    }
}
