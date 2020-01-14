package com.mthomos.intdemo.Data

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class GithubClient ()
{

    val apiGitHubUrl = "https://api.github.com"
    var gson = GsonBuilder()
        .create()
    val retrofit = Retrofit.Builder().baseUrl(apiGitHubUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //Create client service
    var githubClient = retrofit.create(GithubApiService::class.java)

    fun getSquareRepos(): Observable<List<GitHubRepo>>
    {
        var reposListCall = retrofit.create(GithubApiService::class.java).getSquareRepos()
        return reposListCall
    }
}

