package com.darwin.photolandhk.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.darwin.photolandhk.databinding.FragmentOverviewReportBinding
import com.darwin.photolandhk.ui.ReportOverviewAdapter

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
//        viewModel.navigateToSelectedProperty.observe(this, Observer {
//            if (it != null) {
//                activity?.findNavController(R.id.nav_graph)?.navigate(ReportFragmentDirections.actionShowPost(it))
//                viewModel.displayPostContentComplete()
//            }
//        })
        return binding.root
    }

}