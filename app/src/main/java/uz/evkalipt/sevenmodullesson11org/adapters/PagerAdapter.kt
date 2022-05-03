package uz.evkalipt.sevenmodullesson11org.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import uz.evkalipt.sevenmodullesson11org.PagerFragment
import uz.evkalipt.sevenmodullesson11org.model.Valyut
import uz.evkalipt.sevenmodullesson11org.model.Valyut1

@Suppress("DEPRECATION")
class PagerAdapter(var list: List<Valyut1>, manager: FragmentManager):FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return PagerFragment.newInstance(list[position])
    }


}