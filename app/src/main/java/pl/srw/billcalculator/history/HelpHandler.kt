package pl.srw.billcalculator.history

import android.app.Activity
import android.content.res.Resources
import android.support.v7.widget.Toolbar
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import pl.srw.billcalculator.R
import pl.srw.billcalculator.type.ContentType
import pl.srw.billcalculator.wrapper.Analytics
import javax.inject.Inject

/**
 * Shows help screen for drawer activity
 */
class HelpHandler @Inject constructor(res: Resources) {

    private val fabDesc: String = res.getString(R.string.main_help_fab_desc)
    private val swipeDesc: String = res.getString(R.string.main_help_swipe_delete_desc)
    private val menuDesc: String = res.getString(R.string.main_help_menu_desc)
    private val optionsDesc: String = res.getString(R.string.main_help_options_desc)

    fun show(activity: Activity) {
        Analytics.logContent(ContentType.HELP, "screen", "DrawerActivity")

        val toolbar = activity.findViewById(R.id.toolbar) as Toolbar
        val fab = activity.findViewById(R.id.fab)
        val swipeIcon = activity.findViewById(R.id.swipe_delete_history_icon)

        TapTargetSequence(activity)
                .targets(
                        TapTarget.forView(fab, fabDesc).transparentTarget(true),
                        TapTarget.forView(swipeIcon, swipeDesc),
                        TapTarget.forToolbarNavigationIcon(toolbar, menuDesc),
                        TapTarget.forToolbarOverflow(toolbar, optionsDesc)
                )
                .continueOnCancel(true)
                .considerOuterCircleCanceled(true)
                .start()
    }
}
