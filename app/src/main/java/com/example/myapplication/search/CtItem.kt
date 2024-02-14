package com.example.myapplication.search

sealed class CtItem{
    data class CategoryItem(var Id: Int, var animalIcon: Int, var animalName: String ): CtItem()
    data class CategoryPlus(var Id: Int, val PlusIcon: Int, val PlusName: String ): CtItem()
}
