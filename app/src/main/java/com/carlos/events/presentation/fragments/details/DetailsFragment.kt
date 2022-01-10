package com.carlos.events.presentation.fragments.details

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.carlos.events.presentation.viewmodels.EventsViewModel
import com.carlos.events.R
import com.carlos.events.data.response.CheckInResponse
import com.carlos.events.databinding.FragmentDetailsBinding
import com.carlos.events.util.StateResponse
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: EventsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()
    private var imageUrl = String()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailsFragment_to_eventsFragment)
        }

        binding.imageShareButton.setOnClickListener {
            sendInvite()
        }

        binding.btnCheckIn.setOnClickListener {
            openCheckInDialog()
        }

        binding.eventDetailsTitle.text = args.eventTitle
        binding.eventDetailsDescription.text = args.eventDescription

        Glide.with(view)
            .load(convertHttp(args.eventImage))
            .placeholder(R.drawable.placeholder_image)
            .centerCrop()
            .into(binding.eventDetailsImage)
    }

    private fun sendInvite() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${args.eventTitle} \n\n\n ${args.eventDescription} ")
            type = TYPE
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun openCheckInDialog() {
        val dialog = LayoutInflater.from(context).inflate(R.layout.checkin_layout_dialog, null)
        AlertDialog.Builder(context)
            .setView(dialog)
            .show()

        val animationView= dialog.findViewById<LottieAnimationView>(R.id.animationViewDialog)
        val dialogView = dialog.findViewById<LinearLayout>(R.id.dialogViewCheckIn)
        val dialogFailure = dialog.findViewById<TextView>(R.id.dialogTextFailure)
        val name = dialog.findViewById<EditText>(R.id.edtName).text
        val email = dialog.findViewById<EditText>(R.id.edtEmail).text

        dialogView.visibility = View.VISIBLE
        dialogFailure.visibility = View.GONE

        dialog.findViewById<Button>(R.id.dialogButton).setOnClickListener {

            if(email.isEmpty() || name.isEmpty()) setToastUserMessage()
            else eventChekIn(name.toString(),  email.toString())

            lifecycleScope.launchWhenCreated {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is StateResponse.StateLoading -> {}

                        is StateResponse.StateSuccess -> setStateSuccess(dialogView, animationView)

                        is StateResponse.StateError -> setStateError(dialogView, animationView, dialogFailure)
                    }
                }
            }
        }
    }

    private fun eventChekIn(name: String, email: String) {
        viewModel.eventChekIn(CheckInResponse(eventId = args.eventId, name = name, email = email))
    }

    private fun setToastUserMessage() {
        Toast.makeText(context, R.string.dialog_alert_user, Toast.LENGTH_SHORT).show()
    }

    private fun setStateSuccess(dialogView: View, animationView: LottieAnimationView) {
        dialogView.visibility = View.GONE
        setAnimationDialog(animationView, R.raw.loading_success)
        Toast.makeText(context, R.string.dialog_text_success, Toast.LENGTH_SHORT).show()
    }

    private fun setStateError(dialogView: View, animationView: LottieAnimationView, dialogFailure: TextView) {
        dialogView.visibility = View.GONE
        setAnimationDialog(animationView, R.raw.loading_failed)
        dialogFailure.visibility = View.VISIBLE
        Toast.makeText(context, R.string.dialog_text_failure, Toast.LENGTH_SHORT).show()
    }

    private fun setAnimationDialog(animationViewDialog: LottieAnimationView, animation: Int) {
        animationViewDialog.visibility = View.VISIBLE
        animationViewDialog.setAnimation(animation)
        animationViewDialog.playAnimation()
    }

    private fun convertHttp(http: String) = imageUrl.run {
        if (http.contains(HTTPS)) http else args.eventImage.replace(HTTP, HTTPS)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_detailsFragment_to_eventsFragment)}}
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)}

    companion object {
        private const val HTTP = "http"
        private const val HTTPS = "https"
        private const val TYPE = "text/plain"
    }
}