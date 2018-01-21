package pl.srw.billcalculator.settings.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import pl.srw.billcalculator.R
import pl.srw.billcalculator.databinding.SettingsDetailsBinding
import pl.srw.billcalculator.di.Dependencies
import pl.srw.billcalculator.settings.details.restore.ConfirmRestoreSettingsDialogFragment
import pl.srw.billcalculator.settings.help.ProviderSettingsHelpActivity
import pl.srw.billcalculator.type.Provider
import timber.log.Timber
import javax.inject.Inject

private const val ARG_PROVIDER = "settings.provider.arg.provider"

class SettingsDetailsController(bundle: Bundle) : Controller(bundle) {

    @Inject lateinit var vmFactory: SettingsDetailsVMFactory
    @Inject lateinit var clickVisitor: SettingsDetailsItemClickVisitor

    private lateinit var binding: SettingsDetailsBinding
    private val provider by lazy { args.getSerializable(ARG_PROVIDER) as Provider }
    private val activity: FragmentActivity
        get() = super.getActivity() as FragmentActivity

    companion object {
        fun createFor(provider: Provider): SettingsDetailsController {
            val bundle = Bundle()
            bundle.putSerializable(ARG_PROVIDER, provider)
            return SettingsDetailsController(bundle)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        Dependencies.inject(this)
        binding = SettingsDetailsBinding.inflate(inflater, container, false).apply {
            vm = ViewModelProviders.of(activity, vmFactory).get(SettingsDetailsVM::class.java)
        }
        onViewBound()
        Dependencies.set(binding.vm!!)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings, menu)
    }

    @SuppressWarnings("ReturnCount")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_help) { // FIXME: move to presenter
            showHelp()
            return true
        } else if (item.itemId == R.id.action_default) {
            Timber.i("Restore prices clicked for %s", provider)
            ConfirmRestoreSettingsDialogFragment.newInstance(provider)
                    .show((getActivity() as AppCompatActivity).supportFragmentManager, null)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onViewBound() {
        val view = binding.settingsDetailsList
        binding.vm!!.updateItemsFor(provider)

        val layoutManager = LinearLayoutManager(activity)
        view.layoutManager = layoutManager
        view.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
        view.adapter = SettingsDetailsListAdapter(clickVisitor)
    }

    private fun showHelp() {
        val intent = ProviderSettingsHelpActivity.createIntent(getActivity(), provider)
        startActivity(intent)
    }

}
