package com.mthomos.intdemo.Data

import io.reactivex.Observable
import retrofit2.http.GET

interface GithubApiService
{
    @GET("/orgs/square/repos")
    fun getSquareRepos(): Observable<List<GitHubRepo>>

}