package hr.fer.teslasjourney.ui

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dagger.android.AndroidInjection
import hr.fer.teslasjourney.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity : AppCompatActivity(){
    open var commonViewModel: BaseViewModel<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            AndroidInjection.inject(this)
            commonViewModel = provideCommonViewModel()
            commonViewModel?.commonStateRender()?.observe(this, Observer {
                when (it) {
                    is LoadingState -> { //no-op
                        }
                    is ErrorState -> handleError(it)
                    is Idle -> {
                        hideProgress()
                    }
                }
            })
        } catch (e: IllegalArgumentException) { }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun setToolbarWithBackNavigation(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    abstract fun provideCommonViewModel(): BaseViewModel<*>?

    open fun handleError(errorState: ErrorState) {
        when (errorState) {
            is ErrorMessage -> showError(errorState.message)
        }
    }

    open fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                commonViewModel?.clearCommonState()
            }
            .show()
    }

    open fun showProgress() {
        // no-op
    }

    open fun hideProgress() {
        // no-op
    }

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
    }
}