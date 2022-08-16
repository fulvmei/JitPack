package androidx.window.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.window.layout.WindowInfoRepository
import androidx.window.layout.WindowInfoRepository.Companion.windowInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/** Demo of [SplitLayout]. */
class SplitLayoutActivity : AppCompatActivity() {

    private lateinit var mWindowInfoRepository: WindowInfoRepository
    private lateinit var splitLayout: SplitLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split_layout)
        mWindowInfoRepository = windowInfoRepository()
        splitLayout = findViewById(R.id.split_layout)

        // Create a new coroutine since repeatOnLifecycle is a suspend function
        lifecycleScope.launch(Dispatchers.Main) {
            // The block passed to repeatOnLifecycle is executed when the lifecycle
            // is at least STARTED and is cancelled when the lifecycle is STOPPED.
            // It automatically restarts the block when the lifecycle is STARTED again.
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Safely collect from windowInfoRepo when the lifecycle is STARTED
                // and stops collection when the lifecycle is STOPPED
                mWindowInfoRepository.windowLayoutInfo
                        .collect { newLayoutInfo ->
                            splitLayout.updateWindowLayout(newLayoutInfo)
                        }
            }
        }
    }
}