package hr.fer.teslasjourney.ui.city_location

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.di.ViewModelInjection
import hr.fer.teslasjourney.ui.BaseActivity
import hr.fer.teslasjourney.ui.BaseViewModel
import kotlinx.android.synthetic.main.activity_city_location.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import hr.fer.teslasjourney.data.models.Chapter
import hr.fer.teslasjourney.data.models.LocationData
import hr.fer.teslasjourney.ui.city_location.view.DescriptionView
import hr.fer.teslasjourney.ui.secret.SecretActivityBuilder
import hr.fer.teslasjourney.ui.shared.loadImage

class CityLocationActivity : BaseActivity() {

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: CityLocationViewModel

    lateinit var locationLongitude: String
    lateinit var locationLatitude: String
    lateinit var locationTitle: String

    private var numberOfTimesClicked: Int = 0

    private val bundleArguments: CityLocationActivityBuilder by lazy {
        CityLocationActivityBuilder.deserializeFrom(intent)
    }


    override fun provideCommonViewModel(): BaseViewModel<*>? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_location)

        setSupportActionBar(cityLocationToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        closeButton.setOnClickListener {
            onBackPressed()
        }

        viewModel.viewStateRender().observe(this, Observer { state ->
            when (state) {
                is UpdateUI -> initUI(state.location, state.chapters)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.start(bundleArguments)
        numberOfTimesClicked = 0
    }

    fun initUI(location: LocationData, chapters: List<Chapter>) {
        with(locationName) {
            text = location.name
            setOnClickListener {
                numberOfTimesClicked++
                if (numberOfTimesClicked >= 5) {
                    startActivity(SecretActivityBuilder().intent(this@CityLocationActivity))
                }
            }
        }
        locationType.text = location.type
        personsLocation.text = getString(R.string.tesla)
        city.text = location.city
        locationAddress.text = location.address
        locationImage.loadImage(location.mainImage)

        locationLongitude = location.longitude
        locationLatitude = location.latitude
        locationTitle = location.name

        for (chapter in chapters.sortedBy { it.position.toInt() }) {
            val descriptionView = DescriptionView(this)
            descriptionView.setTitle(chapter.title)
            descriptionView.setDescription(chapter.description)
            chapter.individualImage?.let { descriptionView.setIndividualImage(it) }
            chapter.imageList?.let { descriptionView.setImages(it) }
            chapter.videoList?.let{descriptionView.setVideos(it)}

            descriptionLayout.addView(descriptionView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_city_location, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.directions) {
            // Creates an Intent that will load a map
            var intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$locationLatitude,$locationLongitude ($locationTitle)"))
            startActivity(intent)
            return true
        } else if (id == R.id.share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"

            val shareBody = "Check out this location! http://maps.google.com/?ll=$locationLatitude,$locationLongitude."
            sharingIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                "Check out this location!"
            )
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}