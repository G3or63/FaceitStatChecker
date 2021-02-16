package com.example.faceitstatchecker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.faceitstatchecker.BarChart.BarChart
import com.example.faceitstatchecker.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class MainFragment : Fragment() {
	companion object {
		fun newInstance() = MainFragment()
	}
	
	private val viewModel: MainViewModel by viewModels()
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.main_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.testSingleton()
		val list = mutableListOf(BarChart.DummyData(150f, "ten"), BarChart.DummyData(240f, "twenty"), BarChart.DummyData(330f, "thirty"), BarChart.DummyData(450f, "fourty"), BarChart.DummyData(510f, "fifty"),
			BarChart.DummyData(660f, "sixty"), BarChart.DummyData(702f, "seventy"))
		testChart.setGraphData(list)
		lifecycleScope.launchWhenResumed {
			var vert = true
			while(true) {
				if (vert) {
					testChart.setOrientation(BarChart.Orientation.HORIZONTAL)
					vert = false
				} else {
					testChart.setOrientation(BarChart.Orientation.VERTICAL)
					vert = true
				}
				delay(2000)
			}
		}
	}
}