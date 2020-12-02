package com.darwin.photolandhk.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.darwin.photolandhk.MainActivity
import com.darwin.photolandhk.R
import com.darwin.photolandhk.databinding.FragmentOverviewReportBinding

class ReportFragment : Fragment() {

    private val viewModel: ReportViewModel by lazy {
        ViewModelProvider(this).get(ReportViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewReportBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.reportRecyclerview.adapter = ReportOverviewAdapter(ReportOverviewAdapter.OnClickListener {
            viewModel.displayPostContent(it)
        })

        val swipeLayout = binding.swipeContainerReport
        swipeLayout.setOnRefreshListener {
            viewModel.getReportOverview()
            swipeLayout.isRefreshing = false
        }

        viewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                (activity as MainActivity).updateFragment(R.id.post,it)
            }
        })
        return binding.root
    }

}