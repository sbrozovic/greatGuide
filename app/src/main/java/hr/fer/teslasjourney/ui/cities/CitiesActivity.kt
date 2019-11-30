package hr.fer.teslasjourney.ui.cities

import android.os.Bundle
import androidx.lifecycle.Observer
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.data.models.CitiesModel
import hr.fer.teslasjourney.data.models.Country
import hr.fer.teslasjourney.di.ViewModelInjection
import hr.fer.teslasjourney.ui.BaseActivity
import hr.fer.teslasjourney.ui.BaseViewModel
import kotlinx.android.synthetic.main.activity_cities.*
import javax.inject.Inject

class CitiesActivity : BaseActivity() {
    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: CitiesViewModel

    override fun provideCommonViewModel(): BaseViewModel<*>? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        viewModel.viewStateRender().observe(this, Observer { state ->
            when (state) {
                is CitiesInit -> initUI(state.list)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.start(null)
    }

    fun initUI(list: List<CitiesModel>) {
        val listWithAcknowledgement = mutableListOf<CitiesModel>()
        listWithAcknowledgement.apply {
            addAll(list)
            add(CitiesModel(getString(R.string.go_to_acknowledgements),
                "",
                2,
                true,
                "",
                (list.size - 1).toString(),
                Country.NONE)
            )
        }
        citiesViewPager.adapter = CitiesAdapter(supportFragmentManager, listWithAcknowledgement)
        citiesDotsIndicator.count = listWithAcknowledgement.size
    }
}