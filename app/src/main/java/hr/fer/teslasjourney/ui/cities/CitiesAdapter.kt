package hr.fer.teslasjourney.ui.cities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.fer.teslasjourney.data.models.CitiesModel

class CitiesAdapter constructor(
    private val fragmentManager: FragmentManager,
    private val citiesList: List<CitiesModel>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return CitiesFragment.newInstance(citiesList[position], position)
    }

    override fun getCount(): Int = citiesList.size
}