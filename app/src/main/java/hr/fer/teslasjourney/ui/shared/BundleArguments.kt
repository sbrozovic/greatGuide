package hr.fer.teslasjourney.ui.shared

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.jetbrains.anko.newTask

interface BundleArguments

interface ActivityBundleArguments : BundleArguments {

    /**
     * returns intent that will be used to launch this activity
     */
    fun intent(context: Context): Intent

    /**
     * Launches the activity with a given context.
     */
    fun launch(context: Context) = context.startActivity(intent(context))

    /**
     * Launches the activity as a new task with a given context.
     */
    fun newTask(context: Context) = context.startActivity(intent(context).newTask())

    /**
     * Launches the activity with a given [Activity] for results
     */
    fun launchForResult(activity: Activity, requestCode: Int) =
        activity.startActivityForResult(intent(activity), requestCode)

    /**
     * Launches the activity with a given [Activity] for results
     */
    fun launchForResult(fragment: Fragment, requestCode: Int) =
        fragment.startActivityForResult(intent(fragment.requireContext()), requestCode)
}

interface FragmentBundleArguments : BundleArguments {

    /**
     * returns fragment that will be used to fill container view
     */
    fun create(): Fragment

    /**
     *
     * This method uses fragmentManager.add()
     */
    fun add(fragmentManager: FragmentManager, containerId: Int) {
        fragmentManager.beginTransaction().add(containerId, create()).commit()
    }

    /**
     * This method uses fragmentManager.replace()
     */
    fun replace(fragmentManager: FragmentManager, containerId: Int) {
        fragmentManager.beginTransaction().replace(containerId, create()).commit()
    }
}
