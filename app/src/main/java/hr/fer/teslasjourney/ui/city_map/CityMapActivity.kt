package hr.fer.teslasjourney.ui.city_map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.data.models.LocationData
import hr.fer.teslasjourney.di.ViewModelInjection
import hr.fer.teslasjourney.ui.BaseActivity
import hr.fer.teslasjourney.ui.BaseViewModel
import hr.fer.teslasjourney.ui.city_map.view.LocationsAdapter
import kotlinx.android.synthetic.main.activity_city_map.*
import javax.inject.Inject
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.Marker
import hr.fer.teslasjourney.data.models.CityAndLocationId
import hr.fer.teslasjourney.ui.city_location.CityLocationActivity
import hr.fer.teslasjourney.ui.city_location.CityLocationActivityBuilder
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class CityMapActivity : BaseActivity(), OnMapReadyCallback {

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: CityMapViewModel

    private val bundleArguments: CityMapActivityBuilder by lazy {
        CityMapActivityBuilder.deserializeFrom(intent)
    }

    override fun provideCommonViewModel(): BaseViewModel<*>? = viewModel

    private val LOCATION_PERMISSION_REQUEST_CODE = 69

    private lateinit var mMap: GoogleMap
    private lateinit var list: List<LocationData>
    private var listOfMarkers: MutableList<Marker> = mutableListOf()
    lateinit var locationLongitude: String
    lateinit var locationLatitude: String
    lateinit var locationName: String
    lateinit var locationId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_map)
        setSupportActionBar(cityMapToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        closeButton.setOnClickListener {
            onBackPressed()
        }

        viewModel.viewStateRender().observe(this, Observer { state ->
            when (state) {
                is LocationsInit -> init(state.locations)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.start(bundleArguments)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (::list.isInitialized) {
            setGoogleMapsMarkers()
            setupViewPager()
        }
    }

    fun init(list: List<LocationData>) {
        this.list = list
        if (::mMap.isInitialized) {
            setGoogleMapsMarkers()
            setupViewPager()
        }
    }

    fun setGoogleMapsMarkers() {
        for (location in list) {
            var marker = mMap.addMarker(
                MarkerOptions().position(
                    LatLng(
                        location.latitude.toDouble(),
                        location.longitude.toDouble()
                    )
                ).title(location.name)
            )
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(getBitmap(R.drawable.unselected_location)))
            listOfMarkers.add(marker)
        }

        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    list[0].latitude.toDouble(),
                    list[0].longitude.toDouble()
                ), 13f
            )
        )
        listOfMarkers[0].setIcon(BitmapDescriptorFactory.fromBitmap(getBitmap(R.drawable.current_selected_location)))

        setLocationData(list[0].latitude, list[0].longitude, list[0].name, list[0].id)

        requestLocationPermission()
        setMarkerListener()
        initToolbar()
    }

    private fun setLocationData(latitude: String, longitude: String, name: String, id: String) {
        locationLatitude = latitude
        locationLongitude = longitude
        locationName = name
        locationId = id
    }

    fun initToolbar() {
        toolbarTitle.text = list[0].city
    }

    fun setMarkerListener() {
        mMap.setOnMarkerClickListener { selectedLocation ->
            locationsViewpager.currentItem = listOfMarkers.indexOf(selectedLocation)
            setLocationData(
                selectedLocation.position.latitude.toString(),
                selectedLocation.position.longitude.toString(),
                selectedLocation.title,
                selectedLocation.id
            )
            true
        }
    }

    fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            mMap.isMyLocationEnabled = true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        } else {
            if (ActivityCompat
                    .shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
            ) {
                alert(getString(R.string.permission_location), getString(R.string.permission_title)) {
                    yesButton {
                        ActivityCompat.requestPermissions(
                            this@CityMapActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
                        )
                    }
                    onCancelled {
                        ActivityCompat.requestPermissions(
                            this@CityMapActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
                        )
                    }
                }.show()
            }
        }
    }

    private fun setupViewPager() {
        val adapter = LocationsAdapter(this, list, bundleArguments.cityId)
        locationsViewpager.adapter = adapter
        adapter.notifyDataSetChanged()
        locationsViewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            list[position].latitude.toDouble(),
                            list[position].longitude.toDouble()
                        ), 13f
                    )
                )

                setLocationData(list[position].latitude, list[position].longitude, list[position].name, list[position].id)

                listOfMarkers[position].setIcon(BitmapDescriptorFactory.fromBitmap(getBitmap(R.drawable.current_selected_location)))

                for (item in listOfMarkers) {
                    if (listOfMarkers.indexOf(item) != position) {
                        item.setIcon(BitmapDescriptorFactory.fromBitmap(getBitmap(R.drawable.unselected_location)))
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_city_map, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.directions -> {
                // Creates an Intent that will load a map
                var intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=$locationLatitude,$locationLongitude ($locationName)")
                )
                startActivity(intent)
                return true
            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"

                val shareBody =
                    "Check out this location! http://maps.google.com/?ll=$locationLatitude,$locationLongitude."
                sharingIntent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Check out this location!"
                )
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
                return true
            }
            R.id.info -> startActivity(CityLocationActivityBuilder(CityAndLocationId(bundleArguments.cityId, locationId)).intent(this))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getBitmap(drawableRes: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(this, drawableRes)
        val canvas = Canvas()
        drawable?.let {
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            canvas.setBitmap(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(canvas)
            return bitmap
        }
        return null
    }
}
