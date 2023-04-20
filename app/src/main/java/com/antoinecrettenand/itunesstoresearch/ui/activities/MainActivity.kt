package com.antoinecrettenand.itunesstoresearch.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.antoinecrettenand.itunesstoresearch.R
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItemType
import com.antoinecrettenand.itunesstoresearch.ui.viewmodels.MainViewModel
import com.google.android.material.appbar.AppBarLayout


class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var mainViewModel: MainViewModel
    private lateinit var spinner: Spinner
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupToolbar()
        setupSearchView()
        populateSpinnerWithMediaTypeChoices()

        val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[MainViewModel::class.java]

        mainViewModel.hasResults.observe(this) {
            if (!it) Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar() {
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setupSearchView() {
        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchView.windowToken, 0)

                if (query.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity, "Search query is empty", Toast.LENGTH_SHORT).show()
                    return false
                }

                val rawMediaType: String = spinner.selectedItem as String
                val itunesItemType: ItunesItemType = ItunesItemType.fromString(rawMediaType)
                mainViewModel.search(query, itunesItemType, "CH")
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }

    private fun populateSpinnerWithMediaTypeChoices() {
        spinner = findViewById(R.id.searchMediaType)

        val adapter = ArrayAdapter.createFromResource(
            this, R.array.media_types_array, R.layout.spinner_item
        )

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}

