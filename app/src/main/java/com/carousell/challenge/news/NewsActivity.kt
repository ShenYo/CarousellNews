package com.carousell.challenge.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.carousell.challenge.R
import com.carousell.challenge.base.applySchedulers
import com.carousell.challenge.dataSource.model.NewsModel
import com.carousell.challenge.util.BaseRecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent

class NewsActivity : AppCompatActivity(), KoinComponent {

    private val compositeDisposable = CompositeDisposable()
    private val viewModel by viewModel<NewsViewModel>()
    private lateinit var adapter: BaseRecyclerViewAdapter<NewsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        setContentView(R.layout.activity_news)
        setSupportActionBar(toolbar)
        observeLoadingState()
        initNewsList()
        initSwipeRefresh()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_recent -> {
                adapter.run {
                    viewModel.getNewsOrderByTime()
                        .subscribe { news -> setData(news) }
                        .apply { compositeDisposable.add(this) }

                }
                Snackbar.make(rootContainer, "Sort By Created Time Now", Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.action_popular -> {
                adapter.run {
                    viewModel.getNewsOrderByRank()
                        .subscribe { news -> setData(news) }
                        .apply { compositeDisposable.add(this) }

                }
                Snackbar.make(rootContainer, "Sort By Rank Now", Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeLoadingState() {
        viewModel.publishSubject
            .applySchedulers()
            .subscribe { state ->
                when (state) {
                    LoadingState.LOADING -> showProgress()
                    LoadingState.LOADED -> hideProgress()
                    else -> showErrorHint()
                }
            }
            .apply { compositeDisposable.add(this) }
    }

    private fun showProgress() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun hideProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun showErrorHint() {
        Snackbar.make(rootContainer, "news list loading fail", Snackbar.LENGTH_SHORT).show()
    }

    private fun initNewsList() {
        newsList.run {
            adapter = NewsAdapter(this@NewsActivity)
                .apply {
                    this@NewsActivity.adapter = this
                    initData()
                }
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }

    }

    private fun initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            initData()
            Snackbar.make(rootContainer, "Sort By Created Time Now", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initData() {
        viewModel.fetchNews()
            .andThen {
                viewModel.getNewsOrderByTime().subscribe { news ->
                    adapter.setData(news)
                    it.onComplete()
                }
            }
            .subscribe(
                { },
                { Snackbar.make(rootContainer, it.localizedMessage, Snackbar.LENGTH_SHORT).show() }
            )
            .apply { compositeDisposable.add(this) }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
