package uz.evkalipt.sevenmodullesson11org.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.evkalipt.sevenmodullesson11org.databinding.ItemForRvAllValyutBinding
import uz.evkalipt.sevenmodullesson11org.model.Valyut

class AllValyutAdapterRv(var list: List<Valyut>, var onMyClick: OnMyClick):RecyclerView.Adapter<AllValyutAdapterRv.Vh>() {

    inner class Vh(var itemForRvAllValyutBinding: ItemForRvAllValyutBinding): RecyclerView.ViewHolder(itemForRvAllValyutBinding.root){
        fun onBind(valyut: Valyut){
            var imageUrl = "https://nbu.uz/local/templates/nbu/images/flags/${valyut.code}.png"
            Picasso.get().load(imageUrl).into(itemForRvAllValyutBinding.imageAll)
            itemForRvAllValyutBinding.codeAll.text = valyut.code
            if (valyut.nbu_cell_price!="") {
                itemForRvAllValyutBinding.buyAll.text = valyut.nbu_buy_price
                itemForRvAllValyutBinding.cellAll.text = valyut.nbu_cell_price
            }else{
                itemForRvAllValyutBinding.buyAll.text = valyut.cb_price
                itemForRvAllValyutBinding.cellAll.text = valyut.cb_price
            }

            itemForRvAllValyutBinding.imageCalculator.setOnClickListener {
                onMyClick.onClick(valyut)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemForRvAllValyutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyClick{
        fun onClick(valyut: Valyut)
    }

}