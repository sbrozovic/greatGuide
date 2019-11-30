package hr.fer.teslasjourney.ui.city_map.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.data.models.CityAndLocationId
import hr.fer.teslasjourney.data.models.LocationData
import hr.fer.teslasjourney.ui.city_location.CityLocationActivityBuilder
import hr.fer.teslasjourney.ui.city_map.CityMapActivity

class LocationsAdapter constructor(
    val context: Context,
    val list: List<LocationData>,
    val cityId: String
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = list[position]
        val view = LocationsView(context).apply {
            setLocationName(item.name)
            setAddress(item.address)
            setType(item.type)
            setPerson(context.getString(R.string.tesla))
            setCity(item.city)
            setOnClickListener {
                context.startActivity(
                    CityLocationActivityBuilder(CityAndLocationId(cityId, item.id)).intent(
                        context
                    )
                )
            }
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = list.size

}