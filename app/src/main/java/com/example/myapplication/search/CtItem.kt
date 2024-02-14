package com.example.myapplication.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CtItem : Parcelable{
    data class CategoryItem(var Id: Int, var animalIcon: Int, var animalName: String ): CtItem()
    data class CategoryPlus(var Id: Int, val PlusIcon: Int, val PlusName: String ): CtItem()
}
