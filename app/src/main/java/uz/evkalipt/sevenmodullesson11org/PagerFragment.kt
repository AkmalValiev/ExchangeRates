package uz.evkalipt.sevenmodullesson11org

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import uz.evkalipt.sevenmodullesson11org.databinding.FragmentPagerBinding
import uz.evkalipt.sevenmodullesson11org.model.Valyut
import uz.evkalipt.sevenmodullesson11org.model.Valyut1

private const val ARG_PARAM1 = "param1"

class PagerFragment : Fragment() {

    private var param1: Valyut1? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Valyut1

        }
    }
    lateinit var binding: FragmentPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(layoutInflater)
        val imageUrl = param1?.imageUrl
        Picasso.get().load(imageUrl?.get()).into(binding.imv)
        if (param1?.valyut?.nbu_cell_price!=""){
            binding.cell.text = param1?.valyut?.nbu_cell_price
            binding.buy.text = param1?.valyut?.nbu_buy_price
        }else{
            binding.cell.text = "${param1?.valyut?.cb_price}"
            binding.buy.text = "${param1?.valyut?.cb_price}"
        }
        var date = param1?.valyut?.date
        binding.date.text = date?.substring(0, 10)
        return binding.root

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1:Valyut1) =
            PagerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}