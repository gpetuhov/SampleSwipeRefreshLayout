package com.gpetuhov.android.sampleswiperefreshlayout

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This listener will be triggered on swipe.
        // Progress bar shows automatically.
        swiperefresh.setOnRefreshListener { refreshData() }

        // We can configure colors in the progress bar like this
        swiperefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        )
    }

    // Inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_refresh -> {
                // If the refresh is triggered by menu, not by swipe gesture,
                // then we must manually show progress bar.
                swiperefresh.isRefreshing = true

                refreshData()

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun refreshData() {
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null, {
            delay(3000)

            launch(Dispatchers.Main) {
                // Manually dismiss progress bar
                swiperefresh.isRefreshing = false

                toast("Refresh complete")
            }
        })

    }
}
