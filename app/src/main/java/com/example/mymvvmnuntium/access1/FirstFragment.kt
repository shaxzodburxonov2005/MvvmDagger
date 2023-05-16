package com.example.mymvvmnuntium.access1

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.databinding.FragmentFirstBinding
import com.example.mymvvmnuntium.viewpageradapter.MoviePagerAdapter
import com.example.mymvvmnuntium.viewpagerlist.PagerData

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    lateinit var pagerDataList: ArrayList<PagerData>
    lateinit var moviePagerAdapter: MoviePagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_first, container, false)
        binding= FragmentFirstBinding.bind(view)

        loadPagers()
        moviePagerAdapter = MoviePagerAdapter(requireActivity(), pagerDataList)

        binding.viewPager.adapter = moviePagerAdapter
        binding.tabLayout.attachTo(binding.viewPager)

        binding.Next.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        setupCarousel()

        return view
    }
    private fun loadPagers() {
        pagerDataList = ArrayList()
        pagerDataList.add(PagerData(R.drawable.img_2))
        pagerDataList.add(PagerData(R.drawable.viewpagerimg2))
        pagerDataList.add(PagerData(R.drawable.img_3))

    }
    private fun setupCarousel() {

        binding.viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        binding.viewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)

    }

    class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
        RecyclerView.ItemDecoration() {
        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMarginInDp).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }
    }

}