package uz.evkalipt.sevenmodullesson11org.model

import java.io.Serializable

data class Valyut(
    val cb_price: String,
    val code: String,
    val date: String,
    val nbu_buy_price: String,
    val nbu_cell_price: String,
    val title: String
) : Serializable