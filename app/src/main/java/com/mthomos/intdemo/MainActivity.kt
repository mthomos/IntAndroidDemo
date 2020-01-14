package com.mthomos.intdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mthomos.intdemo.Data.GitHubRepo
import com.mthomos.intdemo.Data.GithubClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("CheckResult")
class MainActivity : AppCompatActivity(), ReposListAdapter.Listener {

    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        progressBar!!.setIndeterminate(true)
        progressBar!!.setVisibility(View.VISIBLE)
        setupRecycleList()
        loadSquareRepos()
    }

    private fun setupRecycleList()
    {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        square_repo_list.layoutManager = layoutManager
    }

    @SuppressLint("CheckResult")
    private fun loadSquareRepos()
    {
        val githubClient = GithubClient()
        val gitSquareReposObservable = githubClient.getSquareRepos()
        gitSquareReposObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(list : List<GitHubRepo>)
    {
        if (list.isNotEmpty())
        {
            Log.d("test" , "size is " + list.size.toString())
            var newList = mutableListOf<GitHubRepo>()
            var i =0
            for (r in list)
            {
                i++
                r.repoName = i.toString() + ": " + r.repoName
                if (r.repoDescription == null || r.repoDescription == "")
                    r.repoDescription = "No Description"
                else
                    r.repoDescription  = "Description: " + r.repoDescription
                newList.add(r)
            }
            val eposListAdapter = ReposListAdapter(newList, this)
            square_repo_list.adapter = eposListAdapter
            progressBar!!.setVisibility(View.GONE)
        }
        else
            Log.d("test", "ERROR")

    }

    private fun handleError(error: Throwable) {

        Log.d("test", error.message)
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> false
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(retroCrypto: GitHubRepo) {
        //
    }

}
