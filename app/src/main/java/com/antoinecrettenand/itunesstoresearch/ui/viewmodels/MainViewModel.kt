package com.antoinecrettenand.itunesstoresearch.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItem
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItemType
import com.antoinecrettenand.itunesstoresearch.data.repository.ItunesSearchRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ItunesSearchRepository()
    val itunesItemList: LiveData<List<ItunesItem>>

    val hasResults: LiveData<Boolean>

    private val _selectedItunesItem = MutableLiveData<ItunesItem?>()
    val selectedItunesItem: LiveData<ItunesItem?> get() = _selectedItunesItem

    init {
        this.itunesItemList = repository.itemList
        hasResults = Transformations.map(repository.itemList) { it.isNotEmpty() }
    }

    fun search(searchInput: String, itunesItemType: ItunesItemType, countryCode: String = "CH") {
        repository.search(searchInput, itunesItemType, countryCode)
    }

    fun selectItunesItem(item: ItunesItem?) {
        _selectedItunesItem.value = item
    }
}