package com.carlos.events.presentation.fragments.events

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.carlos.events.util.StateResponse
import com.carlos.events.R
import com.carlos.events.databinding.FragmentEventsBinding
import com.carlos.events.presentation.adapter.EventsAdapter
import com.carlos.events.presentation.viewmodels.EventsViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment() {

    private lateinit var binding : FragmentEventsBinding
    private val viewModel: EventsViewModel by viewModel()

    private lateinit var events: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentEventsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEvents()

        lifecycleScope.launchWhenCreated {
            viewModel.screenState.collect { state ->
                when (state) {
                    is StateResponse.StateLoading -> binding.flipperEvents.displayedChild = FLIPPERS_CHILD_LOADING
                    is StateResponse.StateSuccess -> binding.flipperEvents.displayedChild = FLIPPERS_CHILD_EVENTS
                    is StateResponse.StateError -> binding.flipperEvents.displayedChild = FLIPPERS_CHILD_ERROR
                }
            }
        }

        viewModel.viewEvents.observe(viewLifecycleOwner, { eventsViewObject ->
            events = view.findViewById(R.id.events_recycler_view)
            events.adapter = EventsAdapter(eventsViewObject) { events ->
                val action = EventsFragmentDirections.actionEventsFragmentToDetailsFragment(
                    events.title, events.description,
                    events.price, events.image, events.id
                )
                findNavController().navigate(action)
            }
        })

        binding.aboutButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventsFragment_to_aboutFragment)
        }

        binding.btnTryAgain.setOnClickListener {
            viewModel.getEvents()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() { activity?.finish()}}
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)}

    companion object {
        private const val FLIPPERS_CHILD_LOADING = 0
        private const val FLIPPERS_CHILD_EVENTS = 1
        private const val FLIPPERS_CHILD_ERROR = 2
    }
}