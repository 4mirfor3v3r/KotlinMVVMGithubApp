package com.amier.kotlinmvvmgithubapp.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amier.kotlinmvvmgithubapp.R
import com.amier.kotlinmvvmgithubapp.data.RepoData
import com.amier.kotlinmvvmgithubapp.databinding.ItemRepoBinding

class RepoAdapter(
    private var repoData: MutableList<RepoData>,
    private var repoViewModel: RepoViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val repoItemBinding: ItemRepoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_repo, parent, false
        )
        return RepoHolder(repoItemBinding)
    }

    override fun getItemCount(): Int {
        return repoData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val datas = repoData[position]
        val actionListener = object : ReposItemActionListener {
            override fun onRepoClicked() {
                repoViewModel.openRepo.value = datas.url
            }
        }
        (holder as RepoHolder).bindRows(datas, actionListener)
    }

    fun replaceData(repoD: MutableList<RepoData>) {
        setList(repoD)
    }

    private fun setList(repoD: MutableList<RepoData>) {
        this.repoData = repoD
        notifyDataSetChanged()
    }

    class RepoHolder(binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        val repoItemBinding = binding
        fun bindRows(repoData: RepoData, userActionListener: ReposItemActionListener) {
            repoItemBinding.datas = repoData
            repoItemBinding.action = userActionListener
            repoItemBinding.executePendingBindings()
        }
    }
}