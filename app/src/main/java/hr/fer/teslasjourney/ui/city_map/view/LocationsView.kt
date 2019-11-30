package hr.fer.teslasjourney.ui.city_map.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import hr.fer.teslasjourney.R
import kotlinx.android.synthetic.main.view_locations.view.*

class LocationsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_locations, this)
    }

    fun setLocationName(text: String) {
        locationName.text = text
    }

    fun setAddress(text: String) {
        locationAddress.text = text
    }

    fun setType(text: String) {
        locationType.text = text
    }

    fun setPerson(text: String){
        personsLocation.text = text
    }

    fun setCity(text: String){
        city.text = text
    }
}