package hr.fer.teslasjourney.ui.secret

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.di.ViewModelInjection
import hr.fer.teslasjourney.ui.BaseActivity
import hr.fer.teslasjourney.ui.BaseViewModel
import kotlinx.android.synthetic.main.activity_secret.*
import javax.inject.Inject
import android.widget.EditText

class SecretActivity : BaseActivity() {
    @Inject
    @ViewModelInjection
    lateinit var viewModel: SecretViewModel

    override fun provideCommonViewModel(): BaseViewModel<*>? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)

        viewModel.viewStateRender().observe(this, Observer { state ->
            when (state) {
                is UpdateUI -> initUI(state.hasSecret)
                is ShowSecret -> showSecret(state.secret)
                is ShowAlert -> showAlert()
                is ShowPasswordSaveSecretAlert -> showPasswordSaveAlert(state.message)
                is ShowPasswordGetSecretAlert -> showPasswordGetSecretAlert()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.start(null)
    }

    fun initUI(hasSecret: Boolean) {
        if (hasSecret) {
            with(oldSecretButton) {
                visibility = View.VISIBLE
                setOnClickListener {
                    viewModel.onViewSecretClicked()
                }
            }
        }
        newSecretButton.setOnClickListener {
            viewModel.onSaveClicked(secret.text.toString())
        }
    }

    fun showSecret(secret: String) {
        with(oldSecret) {
            text = secret
            visibility = View.VISIBLE
        }
    }

    fun showAlert() {
        AlertDialog.Builder(this).apply {
            title = getString(R.string.save_alert_title)
            setMessage(getString(R.string.save_alert_description))
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { dialog, which ->
                viewModel.onYesClicked(secret.text.toString(), passwordMessage())
                dialog.dismiss()
            }
            setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                dialog.dismiss()
            }
            show()
        }
    }

    private fun passwordMessage(): String = if (oldSecretButton.visibility == View.VISIBLE) {
        getString(R.string.message_password_default)
    } else {
        getString(R.string.message_password_first_time)
    }

    fun showPasswordSaveAlert(message: String) {
        AlertDialog.Builder(this).apply {
            val passwordText = EditText(this@SecretActivity)
            setCancelable(false)
            setTitle(getString(R.string.enter_password))
            setMessage(message)

            setView(passwordText)

            setPositiveButton(getString(R.string.done)) { dialog, whichButton ->
                viewModel.saveSecret(secret.text.toString(), passwordText.text.toString())
                oldSecret.visibility = View.GONE

                dialog.dismiss()
            }

            setNegativeButton(getString(R.string.cancel)) { dialog, whichButton ->
                dialog.dismiss()
            }

            show()
        }
    }

    fun showPasswordGetSecretAlert(){
        AlertDialog.Builder(this).apply {
            val passwordText = EditText(this@SecretActivity)
            setCancelable(false)
            setTitle(getString(R.string.enter_password))
            setMessage(getString(R.string.message_password_default))

            setView(passwordText)

            setPositiveButton(getString(R.string.done)) { dialog, whichButton ->
                viewModel.getSecret(passwordText.text.toString())
                dialog.dismiss()
            }

            setNegativeButton(getString(R.string.cancel)) { dialog, whichButton ->
                dialog.dismiss()
            }
            show()
        }
    }
}