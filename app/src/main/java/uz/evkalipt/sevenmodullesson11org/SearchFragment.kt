package uz.evkalipt.sevenmodullesson11org

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.evkalipt.sevenmodullesson11org.adapters.AllValyutAdapterRv
import uz.evkalipt.sevenmodullesson11org.databinding.FragmentSearchBinding
import uz.evkalipt.sevenmodullesson11org.model.Valyut
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding: FragmentSearchBinding
    lateinit var myViewModel: MyViewModel
    lateinit var allValyutAdapterRv: AllValyutAdapterRv
    lateinit var tempList:ArrayList<Valyut>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        tempList = ArrayList()
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getValyut().observe(this, {
            it.forEach {
                tempList.add(it.valyut!!)
            }
            allValyutAdapterRv = AllValyutAdapterRv(tempList, object :AllValyutAdapterRv.OnMyClick{
                override fun onClick(valyut: Valyut) {
                    var bundle = Bundle()
                    bundle.putString("code", valyut.code)
                    findNavController().navigate(R.id.thirdFragment)
                }

            })
            binding.rvSearch.adapter = allValyutAdapterRv

            binding.searchValyut.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    tempList.clear()
                    val searchText = newText?.lowercase(Locale.getDefault())
                    if (searchText!!.isNotEmpty()){
                        it.forEach {
                            if (it.valyut?.code?.lowercase(Locale.getDefault())!!.contains(searchText)){
                                tempList.add(it.valyut!!)
                            }
                        }
                        allValyutAdapterRv.notifyDataSetChanged()
                    }else{
                        tempList.clear()
                        allValyutAdapterRv.notifyDataSetChanged()
                    }
                    return false
                }

            })

        })

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}