package com.pab.booking_lapang_fkom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pab.booking_lapang_fkom.databinding.FragmentRiwayatBinding

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BookingAdapter
    private val bookings = mutableListOf<BookingModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelableArrayList<BookingModel>("bookings")?.let {
            bookings.clear()
            bookings.addAll(it)
        }

        adapter = BookingAdapter(bookings)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(bookings: ArrayList<BookingModel>) = RiwayatFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("bookings", bookings)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}