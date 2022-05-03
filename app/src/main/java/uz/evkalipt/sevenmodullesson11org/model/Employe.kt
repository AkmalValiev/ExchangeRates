package uz.evkalipt.sevenmodullesson11org.model

import androidx.databinding.ObservableField

class Employe {

    var name:ObservableField<String> = ObservableField()

    constructor(name: ObservableField<String>) {
        this.name = name
    }

    constructor()


}