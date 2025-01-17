package com.ballboycorp.tingting.gift

import androidx.databinding.Bindable
import com.ballboycorp.tingting.BR
import com.ballboycorp.tingting.base.BaseObservableViewModel
import com.ballboycorp.tingting.gift.model.GiftItemViewModel
import com.ballboycorp.tingting.table.model.Table
import java.math.BigDecimal

/**
 * Created by musooff on 2019-04-24.
 */

class GiftViewModel: BaseObservableViewModel() {

    var table: Table? = null

    var selectedGifts: MutableList<GiftItemViewModel> = mutableListOf()

    var isBottomSheepOpen: Boolean = false
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.bottomSheepOpen)
        }

    var canSendGift: Boolean = false
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.canSendGift)
        }

    var totalPrice: String? = "0원"
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.totalPrice)
        }

    fun updateTotal() {
        totalPrice = selectedGifts
                .map { it.gift.price }
                .map { it?.replace(",", "") }
                .map { BigDecimal(it) }
                .fold(BigDecimal.ZERO, BigDecimal::add)
                .toString() + "원"
    }
}