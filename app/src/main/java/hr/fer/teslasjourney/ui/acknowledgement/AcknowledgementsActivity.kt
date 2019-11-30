package hr.fer.teslasjourney.ui.acknowledgement

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.di.ViewModelInjection
import hr.fer.teslasjourney.ui.BaseActivity
import hr.fer.teslasjourney.ui.BaseViewModel
import kotlinx.android.synthetic.main.activity_acknowledgement.*
import kotlinx.android.synthetic.main.view_acknowledgment.view.*
import javax.inject.Inject

class AcknowledgementsActivity : BaseActivity() {

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: AcknowledgementViewModel

    override fun provideCommonViewModel(): BaseViewModel<*>? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acknowledgement)

        viewModel.viewStateRender().observe(this, Observer { state ->
            when (state) {
                //no -op
            }
        })

        initUI()
    }

    fun initUI() {
        backButton.setOnClickListener {
            onBackPressed()
        }

        with(acknowledgementFER) {
            name.text = getString(R.string.fer)
            logo.setImageDrawable(ContextCompat.getDrawable(this@AcknowledgementsActivity, R.drawable.fer_logo))
            descriptionLabel.text = getString(R.string.mobile_development)
        }

        with(acknowledgementDesign) {
            name.text = getString(R.string.design_study)
            logo.setImageDrawable(ContextCompat.getDrawable(this@AcknowledgementsActivity, R.drawable.logo_design))
            descriptionLabel.text = getString(R.string.app_design)
        }
        with(acknowledgementICENT) {
            name.text = getString(R.string.icent)
            logo.setImageDrawable(ContextCompat.getDrawable(this@AcknowledgementsActivity, R.drawable.logo_icent))
            descriptionLabel.text = getString(R.string.content)
        }
        with(acknowledgementTeslaAssociation) {
            name.text = getString(R.string.tesla_association)
            logo.setImageDrawable(ContextCompat.getDrawable(this@AcknowledgementsActivity, R.drawable.logo_unt))
            descriptionLabel.text = getString(R.string.content)
        }
    }
}