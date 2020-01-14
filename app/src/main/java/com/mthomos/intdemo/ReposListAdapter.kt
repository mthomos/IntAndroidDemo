package com.mthomos.intdemo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mthomos.intdemo.Data.GitHubRepo
import kotlinx.android.synthetic.main.repo_row_layout.view.*

class ReposListAdapter (private val repoList : List<GitHubRepo>, private val listener : Listener) : RecyclerView.Adapter<ReposListAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(repo : GitHubRepo)
    }

    private val colors : Array<String> = arrayOf("#7E57C2", "#42A5F5", "#26C6DA", "#66BB6A", "#FFEE58", "#FF7043" , "#EC407A" , "#d32f2f")

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(repoList[position], listener, colors, position)
    }

    override fun getItemCount(): Int = repoList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_row_layout, parent, false)
        return ViewHolder(view)

    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        fun bind(repo: GitHubRepo, listener: Listener, colors : Array<String>, position: Int)
        {
            itemView.setOnClickListener{ listener.onItemClick(repo) }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.repo_name.text = repo.repoName
            itemView.repo_description.text = repo.repoDescription
            itemView.repo_url.text = repo.repoURL
        }
    }
}