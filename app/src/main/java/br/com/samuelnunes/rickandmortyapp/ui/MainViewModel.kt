package br.com.samuelnunes.rickandmortyapp.ui

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 04/08/2021 at 15:11
 */
class MainViewModel: ViewModel() {

    private val _search =  MutableLiveData(true)
    val searchViewVisibility: LiveData<Boolean> = _search

    fun showSearchView(){
        _search.postValue(true)
    }

    fun hideSearchView(){
        _search.postValue(false)
    }


    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    val onActionExpandListener = object: MenuItem.OnActionExpandListener{
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true
        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            _query.postValue("")
            return true
        }

    }

    val onQueryTextListener = object: SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            if(query != null){
                _query.postValue(query.lowercase())
            }
            return false
        }
        override fun onQueryTextChange(newText: String?): Boolean = false
    }
}