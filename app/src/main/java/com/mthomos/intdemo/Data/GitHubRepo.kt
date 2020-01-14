package com.mthomos.intdemo.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GitHubRepo
{
    @SerializedName("id")
    var repoID: Int = -1
    @SerializedName("description")
    var repoDescription : String ? = null
    @SerializedName("full_name")
    var repoName : String = "-1"
    @SerializedName("html_url")
    var repoURL: String = "-1"

    public fun printRepo() : String
    {
        var rs = if (repoDescription!= null) repoDescription else "null"
        return repoID.toString() + "_ " + rs + "_ " +repoName.toString() + "_ " +repoURL.toString()
    }

}