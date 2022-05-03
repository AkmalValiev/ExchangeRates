package uz.evkalipt.sevenmodullesson11org.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.evkalipt.sevenmodullesson11org.databinding.ItemRvStoreBinding
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore2

class RvStoreAdapter(var list: List<ValyutApiStore2>):RecyclerView.Adapter<RvStoreAdapter.Vh>() {

    inner class Vh(var itemRvStoreBinding: ItemRvStoreBinding):RecyclerView.ViewHolder(itemRvStoreBinding.root){
        fun onBind(valyutApiStore2: ValyutApiStore2){
            var url = "https://nbu.uz/local/templates/nbu/images/flags/${valyutApiStore2.code}.png"
            Picasso.get().load(url).into(itemRvStoreBinding.imageStore)
            itemRvStoreBinding.title.text = "(${valyutApiStore2.code}) ${valyutApiStore2.title}"
            itemRvStoreBinding.date.text = "${valyutApiStore2.date?.substring(0,10)}"
            itemRvStoreBinding.time.text = "${valyutApiStore2.date?.substring(11, 16)}"
            itemRvStoreBinding.priceCell.text = "${valyutApiStore2.nbu_cell_price} UZS"
            itemRvStoreBinding.priceBuy.text = "${valyutApiStore2.nbu_buy_price} UZS"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}