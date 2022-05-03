package uz.evkalipt.sevenmodullesson11org

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.item_tab_bottom.view.*
import kotlinx.android.synthetic.main.item_tab_top.view.*
import uz.evkalipt.sevenmodullesson11org.adapters.PagerAdapter
import uz.evkalipt.sevenmodullesson11org.adapters.RvStoreAdapter
import uz.evkalipt.sevenmodullesson11org.databinding.FragmentFirstBinding
import uz.evkalipt.sevenmodullesson11org.databinding.ItemTabBottomBinding
import uz.evkalipt.sevenmodullesson11org.databinding.ItemTabTopBinding
import uz.evkalipt.sevenmodullesson11org.db.MyDb
import uz.evkalipt.sevenmodullesson11org.model.Valyut1
import java.util.concurrent.TimeUnit

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FirstFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentFirstBinding
    lateinit var myViewModel: MyViewModel
    lateinit var pagerAdapter: PagerAdapter
    lateinit var list: ArrayList<Valyut1>
    lateinit var myDb: MyDb
    lateinit var rvStoreAdapter: RvStoreAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        myDb = MyDb.getInstance(binding.root.context)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getValyut().observe(this, {
            list = ArrayList()
            list.addAll(it)
            pagerAdapter = PagerAdapter(list, childFragmentManager)
            binding.viewPager.adapter = pagerAdapter
            binding.tabLayoutTop.setupWithViewPager(binding.viewPager)
            binding.tabLayoutBottom.setupWithViewPager(binding.viewPager)

            var tabCount = binding.tabLayoutTop.tabCount
            for (i in 0 until tabCount) {
                var tabView = ItemTabTopBinding.inflate(layoutInflater)
                binding.tabLayoutTop.getTabAt(i)?.customView = tabView.root
                tabView.tv1.text = list[i].valyut?.code
                tabView.tv2.text = list[i].valyut?.code

                if (i == 0) {
                    tabView.cardGreen.visibility = View.VISIBLE
                } else {
                    tabView.cardGreen.visibility = View.INVISIBLE
                }
            }

            binding.tabLayoutTop.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    var tabView = tab?.customView
                    tabView?.card_green?.visibility = View.VISIBLE
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    var tabView = tab?.customView
                    tabView?.card_green?.visibility = View.INVISIBLE
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

            val tabCount1 = binding.tabLayoutBottom.tabCount
            for (i in 0 until tabCount1) {
                var tabView = ItemTabBottomBinding.inflate(layoutInflater)
                binding.tabLayoutBottom.getTabAt(i)?.customView = tabView.root

                if (i == 0) {
                    tabView.linear1.visibility = View.VISIBLE
                }
            }

            binding.tabLayoutBottom.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    var tabView = tab?.customView
                    tabView?.linear1?.visibility = View.VISIBLE

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    var tabView = tab?.customView
                    tabView?.linear1?.visibility = View.INVISIBLE
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

        })

        val workRequest: WorkRequest =
            PeriodicWorkRequestBuilder<MyWorkService>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(binding.root.context).enqueue(workRequest)

        rvStoreAdapter = RvStoreAdapter(myDb.valyutApiStore2Dao().getAll())
        binding.rvHome.adapter = rvStoreAdapter


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}