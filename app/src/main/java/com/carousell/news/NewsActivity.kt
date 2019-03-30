package com.carousell.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.carousell.base.applySchedulers
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent

class NewsActivity : AppCompatActivity(), KoinComponent {

    private val compositeDisposable = CompositeDisposable()

    private val viewModel by inject<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initNewsList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_recent,
            R.id.action_popular -> {
                Snackbar.make(window.decorView, "${item.itemId}", Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initNewsList() {
        newsList.run {
            adapter = NewsAdapter(this@NewsActivity).apply {
                viewModel.fetchNews()
                    .applySchedulers()
                    .subscribe { news ->
                        setData(news)
                    }.apply {
                        compositeDisposable.add(this)
                    }
            }
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
