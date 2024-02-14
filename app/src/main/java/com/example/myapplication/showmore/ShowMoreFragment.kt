package com.example.myapplication.showmore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.YoutubeVideo
import com.example.myapplication.databinding.FragmentShowMoreBinding
import com.example.myapplication.like.LikedUtils


class ShowMoreFragment : Fragment() {
    private lateinit var binding: FragmentShowMoreBinding
    private lateinit var adapter: ShowMoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 프래그먼트의 바인딩 초기화
        binding = FragmentShowMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 어댑터 초기화
        adapter = ShowMoreAdapter(requireContext())

        // 리사이클러뷰에 레이아웃 매니저 설정
        binding.reMoreListRemain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListDog.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListCat.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListParrot.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListElephant.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListGiraffe.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListPanda.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListCrocodile.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListHamster.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListKangaroo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListCamel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListSheep.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListWhale.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListWolf.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListMonkey.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListBear.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListRabbit.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListPenguin.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reMoreListOthers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // 리사이클러뷰에 어댑터 설정
        binding.reMoreListRemain.adapter = adapter
        binding.reMoreListDog.adapter = adapter
        binding.reMoreListCat.adapter = adapter
        binding.reMoreListParrot.adapter = adapter
        binding.reMoreListElephant.adapter = adapter
        binding.reMoreListGiraffe.adapter = adapter
        binding.reMoreListPanda.adapter = adapter
        binding.reMoreListCrocodile.adapter = adapter
        binding.reMoreListHamster.adapter = adapter
        binding.reMoreListKangaroo.adapter = adapter
        binding.reMoreListCamel.adapter = adapter
        binding.reMoreListSheep.adapter = adapter
        binding.reMoreListWhale.adapter = adapter
        binding.reMoreListWolf.adapter = adapter
        binding.reMoreListMonkey.adapter = adapter
        binding.reMoreListBear.adapter = adapter
        binding.reMoreListRabbit.adapter = adapter
        binding.reMoreListPenguin.adapter = adapter
        binding.reMoreListOthers.adapter = adapter

        // 아이템 리스트 가져오기
        val remainItemList = getRemainItemList()
        val dogItemList = getDogItemList()
        val catItemList = getCatItemList()
        val parrotItemList = getParrotItemList()
        val elephantItemList = getElephantItemList()
        val giraffeItemList = getGireffeItemList()
        val pandaItemList = getPandaItemList()
        val crocodileItemList = getCrocodileItemList()
        val hamsterItemList = getHamsterItemList()
        val kangarooItemList = getKangarooItemList()
        val camelItemList = getCamelItemList()
        val sheepItemList = getSheepItemList()
        val whaleItemList = getWhaleItemList()
        val wolfItemList = getWolfItemList()
        val monkeyItemList = getMonkeyItemList()
        val bearItemList = getBearItemList()
        val rabbitItemList = getRabbitItemList()
        val penguinItemList = getPenguinItemList()
        val othersItemList = getOthersItemList()
        // 좋아요된 비디오 목록 가져오기
        val likedVideos = LikedUtils.getLikedVideos(requireContext())

        // 어댑터에 아이템 리스트 설정
        adapter.updateItems(remainItemList)
        adapter.updateItems(dogItemList)
        adapter.updateItems(catItemList)
        adapter.updateItems(parrotItemList)
        adapter.updateItems(elephantItemList)
        adapter.updateItems(giraffeItemList)
        adapter.updateItems(pandaItemList)
        adapter.updateItems(crocodileItemList)
        adapter.updateItems(hamsterItemList)
        adapter.updateItems(kangarooItemList)
        adapter.updateItems(camelItemList)
        adapter.updateItems(sheepItemList)
        adapter.updateItems(whaleItemList)
        adapter.updateItems(wolfItemList)
        adapter.updateItems(monkeyItemList)
        adapter.updateItems(bearItemList)
        adapter.updateItems(rabbitItemList)
        adapter.updateItems(penguinItemList)
        adapter.updateItems(othersItemList)
        // 어댑터에 좋아요된 비디오 목록 설정
        adapter.updateItems(likedVideos)

        binding.ivBack.setOnClickListener {
            // 뒤로가기 버튼 클릭 시 동작
            parentFragmentManager.popBackStack()
        }
    }

    private fun getRemainItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getDogItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getCatItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getParrotItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getElephantItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getGireffeItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getPandaItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getCrocodileItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getHamsterItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getKangarooItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getCamelItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getSheepItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getWhaleItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getWolfItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getMonkeyItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getBearItemList(): List<YoutubeVideo> {
        // RemainList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getRabbitItemList(): List<YoutubeVideo> {
        // DogList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getPenguinItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }

    private fun getOthersItemList(): List<YoutubeVideo> {
        // CatList에 표시할 아이템 리스트를 반환하는 함수를 구현
        return listOf()
    }
//    private fun getLikedVideos(): List<YoutubeVideo> {
//        // 좋아요된 비디오 목록을 가져와서 반환하는 함수를 구현
//        // SharedPreferences 또는 데이터베이스에서 가져와야 합니다.
//        // 여기에 좋아요된 비디오 목록을 가져오는 로직을 추가해주세요.
//        return emptyList() // 임시로 빈 목록 반환
//    }
}