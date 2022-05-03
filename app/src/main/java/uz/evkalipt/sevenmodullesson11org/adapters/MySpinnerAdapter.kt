package uz.evkalipt.sevenmodullesson11org.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import uz.evkalipt.sevenmodullesson11org.R
import uz.evkalipt.sevenmodullesson11org.databinding.ItemSpinnerBinding
import uz.evkalipt.sevenmodullesson11org.model.Valyut1

class MySpinnerAdapter(var list: List<Valyut1>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vh:Vh

        if (convertView==null){
            val inflate =
                ItemSpinnerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            vh = Vh(inflate)
        }else{
            vh = Vh(ItemSpinnerBinding.bind(convertView))
        }
        if (list[position].valyut?.code=="UZS"){
            vh.itemSpinnerBinding.imageItemSpinner.setImageResource(R.drawable.money2)
        }else{
            Picasso.get().load(list[position].imageUrl.get()).into(vh.itemSpinnerBinding.imageItemSpinner)
        }

        vh.itemSpinnerBinding.tvSpinner.text = list[position].valyut?.code

        return vh.itemView
    }

    inner class Vh{
        var itemView:View
        var itemSpinnerBinding: ItemSpinnerBinding

        constructor(itemSpinnerBinding: ItemSpinnerBinding){
            itemView = itemSpinnerBinding.root
            this.itemSpinnerBinding = itemSpinnerBinding
        }
    }
}