package com.reproducerapp

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.facebook.react.ReactFragment

class StartStopFragment : Fragment() {

    private val handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_start_stop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val start = view.findViewById<Button>(R.id.start)


        start.setOnClickListener {
            println("Start button pressed")
            showReactFragment()
            handler.postDelayed({
                hideReactFragment()
            }, 3000)
        }

    }

    private fun hideReactFragment() {
        val fragment = parentFragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            return
        }

        parentFragmentManager.beginTransaction().remove(fragment).commit()
    }


    private fun showReactFragment() {
        val reactFragment = ReactFragment.Builder()
            .setComponentName("ReproducerApp")
            .build();

         parentFragmentManager.beginTransaction().add(R.id.fragment_container, reactFragment).commit()
    }
}
