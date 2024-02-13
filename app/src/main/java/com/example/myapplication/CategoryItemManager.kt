package com.example.myapplication

class CategoryItemManager {
    companion object{
        fun getItem(): List<CtItem> = listOf(
            CtItem.CategoryItem(0, R.drawable.icon_dog, "강아지"),
            CtItem.CategoryItem(1, R.drawable.icon_cat, "고양이"),
            CtItem.CategoryItem(2, R.drawable.icon_parrot, "앵무새"),
            CtItem.CategoryItem(3, R.drawable.icon_elephant, "코끼리"),
            CtItem.CategoryItem(4, R.drawable.icon_giraffe, "기린"),
            CtItem.CategoryItem(5, R.drawable.icon_panda, "판다"),
            CtItem.CategoryItem(6, R.drawable.icon_crocodile, "악어"),
            CtItem.CategoryItem(7, R.drawable.icon_hamster, "햄스터"),
            CtItem.CategoryItem(8, R.drawable.icon_kangaroo, "캥거루"),
            CtItem.CategoryItem(9, R.drawable.icon_camel, "낙타"),
            CtItem.CategoryItem(10, R.drawable.icon_sheep, "양"),
            CtItem.CategoryItem(11, R.drawable.icon_whale, "고래"),
            CtItem.CategoryItem(12, R.drawable.icon_wolf, "늑대"),
            CtItem.CategoryItem(13, R.drawable.icon_monkey, "원숭이"),
            CtItem.CategoryItem(14, R.drawable.icon_bear, "곰"),
            CtItem.CategoryItem(15, R.drawable.icon_rabbit, "토끼"),
            CtItem.CategoryItem(16, R.drawable.icon_penguin, "펭귄"),
            CtItem.CategoryPlus(17, R.drawable.icon_plus, "추가"))

    }
}