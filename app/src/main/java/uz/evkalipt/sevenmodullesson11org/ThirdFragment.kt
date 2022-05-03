package uz.evkalipt.sevenmodullesson11org

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_third.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.evkalipt.sevenmodullesson11org.adapters.MySpinnerAdapter
import uz.evkalipt.sevenmodullesson11org.databinding.FragmentThirdBinding
import uz.evkalipt.sevenmodullesson11org.model.Valyut
import uz.evkalipt.sevenmodullesson11org.model.Valyut1
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ThirdFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentThirdBinding
    lateinit var mySpinnerAdapter: MySpinnerAdapter
    lateinit var listValyut1:ArrayList<Valyut1>
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_third, container, false)
        binding = FragmentThirdBinding.bind(root)
        listValyut1 = ArrayList()

        ApiClient.getRetrofit().create(ApiService::class.java).getValyut().enqueue(object : Callback<List<Valyut>>{
            override fun onResponse(call: Call<List<Valyut>>, response: Response<List<Valyut>>) {
                response.body()?.forEach {
                    var str = ObservableField<String>("https://nbu.uz/local/templates/nbu/images/flags/${it.code}.png")
                    listValyut1.add(Valyut1(str, it))
                }
                var urlImage = ObservableField<String>("https://cdn-icons.flaticon.com/png/128/5975/premium/5975795.png?token=exp=1645691553~hmac=47918e4e06d8b25fb2d52a3ccfafb600")

                var valyut1 = Valyut1(urlImage, Valyut("1", "UZS","", "", "", "Uzbekistan"))
                listValyut1.add(valyut1)
                mySpinnerAdapter = MySpinnerAdapter(listValyut1)
                binding.spinnerTop.adapter = mySpinnerAdapter
                binding.spinnerBottom.adapter = mySpinnerAdapter
                binding.spinnerBottom.setSelection(24)
                val selectedItem = binding.spinnerTop.selectedItem as Valyut1
                if (arguments?.get("code")!=""){
                    val code = arguments?.get("code")
                    for (i in 0 until listValyut1.size) {
                        if (code == listValyut1[i].valyut?.code){
                            binding.spinnerTop.setSelection(i)
                            break
                        }
                    }
                    binding.editText.setText("1")
                    val toDouble = binding.editText.text.toString().toDouble()
                    val valyutTop = binding.spinnerTop.selectedItem as Valyut1
                    val topDouble = valyutTop.valyut?.cb_price.toString().toDouble()

                    val valyutBottom = binding.spinnerBottom.selectedItem as Valyut1
                    val bottomDouble = valyutBottom.valyut?.cb_price.toString().toDouble()

                    var sum = (toDouble*topDouble)/bottomDouble
                    binding.payment.text = "$sum"
                    binding.code.text = valyutBottom.valyut?.code

                }


                val nbuCellPrice = selectedItem.valyut?.nbu_cell_price
                val nbuBuyPrice = selectedItem.valyut?.nbu_buy_price
                if (!nbuBuyPrice.toString().trim().equals("")){
                    binding.celCal.text = nbuCellPrice
                    binding.buyCal.text = nbuBuyPrice
                }else{
                    binding.celCal.text = selectedItem.valyut?.cb_price
                    binding.buyCal.text = selectedItem.valyut?.cb_price
                }

                binding.valyut1 = selectedItem
                binding.invalidateAll()
            }

            override fun onFailure(call: Call<List<Valyut>>, t: Throwable) {

            }

        })

        binding.spinnerTop.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = binding.spinnerTop.selectedItem as Valyut1
                val nbuCellPrice = selectedItem.valyut?.nbu_cell_price
                val nbuBuyPrice = selectedItem.valyut?.nbu_buy_price
                if (!nbuBuyPrice.toString().trim().equals("")){
                    binding.celCal.text = nbuCellPrice
                    binding.buyCal.text = nbuBuyPrice
                }else{
                    binding.celCal.text = selectedItem.valyut?.cb_price
                    binding.buyCal.text = selectedItem.valyut?.cb_price
                }

                binding.valyut1 = selectedItem
                binding.invalidateAll()

                if (!binding.editText.text.toString().trim().equals("")){
                    val toDouble = binding.editText.text.toString().toDouble()
                    val valyutTop = binding.spinnerTop.selectedItem as Valyut1
                    val topDouble = valyutTop.valyut?.cb_price.toString().toDouble()

                    val valyutBottom = binding.spinnerBottom.selectedItem as Valyut1
                    val bottomDouble = valyutBottom.valyut?.cb_price.toString().toDouble()

                    var sum = (toDouble*topDouble)/bottomDouble
                    binding.payment.text = "$sum"
                    binding.code.text = valyutBottom.valyut?.code
                }else{
                    binding.editText.setText("1")
                    val toDouble = binding.editText.text.toString().toDouble()
                    val valyutTop = binding.spinnerTop.selectedItem as Valyut1
                    val topDouble = valyutTop.valyut?.cb_price.toString().toDouble()

                    val valyutBottom = binding.spinnerBottom.selectedItem as Valyut1
                    val bottomDouble = valyutBottom.valyut?.cb_price.toString().toDouble()

                    var sum = (toDouble*topDouble)/bottomDouble
                    binding.payment.text = "$sum"
                    binding.code.text = valyutBottom.valyut?.code
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.spinnerBottom.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (!binding.editText.text.toString().trim().equals("")){
                    val toDouble = binding.editText.text.toString().toDouble()
                    val valyutTop = binding.spinnerTop.selectedItem as Valyut1
                    val topDouble = valyutTop.valyut?.cb_price.toString().toDouble()

                    val valyutBottom = binding.spinnerBottom.selectedItem as Valyut1
                    val bottomDouble = valyutBottom.valyut?.cb_price.toString().toDouble()

                    var sum = (toDouble*topDouble)/bottomDouble
                    binding.payment.text = "$sum"
                    binding.code.text = valyutBottom.valyut?.code
                }else{
                    binding.editText.setText("1")
                    val toDouble = binding.editText.text.toString().toDouble()
                    val valyutTop = binding.spinnerTop.selectedItem as Valyut1
                    val topDouble = valyutTop.valyut?.cb_price.toString().toDouble()

                    val valyutBottom = binding.spinnerBottom.selectedItem as Valyut1
                    val bottomDouble = valyutBottom.valyut?.cb_price.toString().toDouble()

                    var sum = (toDouble*topDouble)/bottomDouble
                    binding.payment.text = "$sum"
                    binding.code.text = valyutBottom.valyut?.code
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.ellips.setOnClickListener {
            val valyut1Top = binding.spinnerTop.selectedItem as Valyut1
            val valyut1Bottom = binding.spinnerBottom.selectedItem as Valyut1

            var indexTop = -1
            var indexBottom = -1
            for (i in 0 until listValyut1.size){
                if (listValyut1[i].valyut?.code == valyut1Top.valyut?.code){
                    indexTop = i
                }
                if (listValyut1[i].valyut?.code == valyut1Bottom.valyut?.code){
                    indexBottom = i
                }
            }
            binding.spinnerTop.setSelection(indexBottom)
            binding.spinnerBottom.setSelection(indexTop)

        }

        binding.editText.addTextChangedListener {
            if (!binding.editText.text.toString().trim().equals("")){
                val toDouble = binding.editText.text.toString().toDouble()
                val valyutTop = binding.spinnerTop.selectedItem as Valyut1
                val topDouble = valyutTop.valyut?.cb_price.toString().toDouble()

                val valyutBottom = binding.spinnerBottom.selectedItem as Valyut1
                val bottomDouble = valyutBottom.valyut?.cb_price.toString().toDouble()

                var sum = (toDouble*topDouble)/bottomDouble
                binding.payment.text = "$sum"
                binding.code.text = valyutBottom.valyut?.code
            }

//            else{
//                val toDouble = binding.editText.text.toString().toDouble()
//                val valyutTop = binding.spinnerTop.selectedItem as Valyut1
//                val topDouble = valyutTop.valyut?.cb_price.toString().toDouble()
//
//                val valyutBottom = binding.spinnerBottom.selectedItem as Valyut1
//                val bottomDouble = valyutBottom.valyut?.cb_price.toString().toDouble()
//
//                var sum = (toDouble*topDouble)/bottomDouble
//                binding.payment.text = "$sum"
//                binding.code.text = valyutBottom.valyut?.code
//            }

        }

        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}