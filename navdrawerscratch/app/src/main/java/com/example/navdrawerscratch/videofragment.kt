package com.example.navdrawerscratch

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView


class videofragment : Fragment() {
    private lateinit var videoView: VideoView
    private lateinit var videoUr: String
    private lateinit var textview: TextView
    private lateinit var description:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_videofragment, container, false)
        videoView = view.findViewById<VideoView>(R.id.videoView)
        textview=view.findViewById(R.id.videotopic)
        description=view.findViewById(R.id.description)
        description.setOnClickListener {
            toggleReadMore(description)
        }

        return view;
    } fun ok(videoUrl: String){
        videoUr=videoUrl
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playVideo(videoUr)


    }
    fun playVideo(videoUrl: String) {


        val args=this.arguments
        val inputData=args?.get("data")
        val descdata=args?.get("desc").toString()
        textview.text=inputData.toString()
         description.text=descdata



        videoView.setVideoURI(Uri.parse(videoUrl))


        val mediaController = MediaController(requireContext())


        mediaController.setAnchorView(videoView)


        videoView.setMediaController(mediaController)

        // Start playing video
        videoView.start()
    }
    private fun toggleReadMore(textView: TextView) {
        if (textView.maxLines == 3) {
            // Expand text
            textView.maxLines = Int.MAX_VALUE
            textView.ellipsize = null
        } else {
            // Collapse text
            textView.maxLines = 3
            textView.ellipsize = TextUtils.TruncateAt.END
        }
    }


}