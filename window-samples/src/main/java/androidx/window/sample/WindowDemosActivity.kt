package androidx.window.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Main activity that launches WindowManager demos.
 */
class WindowDemosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_demos)
    }

    fun showDisplayFeatures(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(this, DisplayFeaturesActivity::class.java)
        startActivity(intent)
    }

    fun showSplitLayout(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(this, SplitLayoutActivity::class.java)
        startActivity(intent)
    }

    fun showPresentation(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(this, PresentationActivity::class.java)
        startActivity(intent)
    }
}