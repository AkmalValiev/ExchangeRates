package uz.evkalipt.sevenmodullesson11org.model

import androidx.databinding.ObservableField
import java.io.Serializable

class Valyut1 : Serializable {
    var imageUrl:ObservableField<String> = ObservableField()
    var valyut:Valyut? = null



    constructor()
    constructor(imageUrl: ObservableField<String>, valyut: Valyut?) {
        this.imageUrl = imageUrl
        this.valyut = valyut
    }

}