package hr.fer.teslasjourney.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.data.models.CitiesModel
import hr.fer.teslasjourney.data.models.Country
import hr.fer.teslasjourney.ui.acknowledgement.AcknowledgmentActivityBuilder
import hr.fer.teslasjourney.ui.city_map.CityMapActivityBuilder
import kotlinx.android.synthetic.main.item_cities.view.*

class CitiesFragment : Fragment() {
    companion object {
        const val CITIES_FRAGMENT_KEY = "cities_item"
        const val CITIES_FRAGMENT_POSITION = "cities_position"

        fun newInstance(citiesModel: CitiesModel, position: Int): CitiesFragment {
            val args = Bundle()
            args.putSerializable(CITIES_FRAGMENT_KEY, citiesModel)
            args.putSerializable(CITIES_FRAGMENT_POSITION, position)
            return CitiesFragment().apply { arguments = args }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_cities, container, false)
        var position = arguments?.getSerializable(CITIES_FRAGMENT_POSITION)
        (arguments?.getSerializable(CITIES_FRAGMENT_KEY) as? CitiesModel)?.let { citiesItem ->
            view.apply {
                cityName.text = citiesItem.cityName
                cityDescription.text = citiesItem.cityDescription
                if (citiesItem.hasData) {
                    buttonVisit.visibility = View.VISIBLE
                    if (citiesItem.cityName == getString(R.string.go_to_acknowledgements)) {
                        buttonVisit.apply {
                            buttonText.text = getString(R.string.onboarding_go)
                            setOnClickListener {
                                activity?.let { it1 -> startActivity(AcknowledgmentActivityBuilder().intent(it1)) }
                            }
                        }
                    } else {
                        buttonVisit.setOnClickListener {
                            activity?.let { it1 -> startActivity(CityMapActivityBuilder(citiesItem.id).intent(it1)) }
                        }
                    }
                } else {
                    buttonVisit.visibility = View.GONE
                }

                when (position) {
                    0 -> {
                        //screen where is Tesla based on size and color of circle
                        if (citiesItem.hasData) {
                            cityBackgroundImage.setImageDrawable(
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_tesla_zuti_krug
                                )
                            )
                        } else {
                            cityBackgroundImage.setImageDrawable(
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_tesla_sivi_krug
                                )
                            )
                        }

                    }
                    else -> {
                        //screen based on size and color of circle
                        if (citiesItem.hasData) {
                            when (citiesItem.citySize) {
                                1 -> cityBackgroundImage.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.ic_zuti_mali_krug
                                    )
                                )
                                2 -> cityBackgroundImage.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.ic_zuti_srednji_krug
                                    )
                                )
                                3 -> cityBackgroundImage.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.ic_zuti_veliki_krug
                                    )
                                )
                            }
                        } else {
                            when (citiesItem.citySize) {
                                1 -> cityBackgroundImage.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.ic_sivi_mali_krug
                                    )
                                )
                                2 -> cityBackgroundImage.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.ic_sivi_srednji_krug
                                    )
                                )
                                3 -> cityBackgroundImage.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.ic_sivi_veliki_krug
                                    )
                                )
                            }
                        }
                    }
                }

                when (citiesItem.country) {
                    Country.CROATIA -> countryImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_croatia
                        )
                    )
                    Country.HUNGARY -> countryImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_hungary
                        )
                    )
                    Country.USA -> countryImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_usa
                        )
                    )
                    Country.AUSTRIA -> countryImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_austria
                        )
                    )
                    Country.CZECH -> countryImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_czech
                        )
                    )
                    Country.FRANCE -> countryImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_france
                        )
                    )
                    Country.SERBIA -> countryImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_serbia
                        )
                    )
                }
            }
        }
        return view
    }
}