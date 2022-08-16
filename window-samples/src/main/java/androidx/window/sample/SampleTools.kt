package androidx.window.sample

import android.graphics.Rect
import android.view.View
import android.widget.FrameLayout
import androidx.window.layout.DisplayFeature

/**
 * Gets the bounds of the display feature translated to the View's coordinate space and current
 * position in the window. This will also include view padding in the calculations.
 */
fun getFeaturePositionInViewRect(
        displayFeature: DisplayFeature,
        view: View,
        includePadding: Boolean = true
): Rect? {
    // The the location of the view in window to be in the same coordinate space as the feature.
    val viewLocationInWindow = IntArray(2)
    view.getLocationInWindow(viewLocationInWindow)

    // Intersect the feature rectangle in window with view rectangle to clip the bounds.
    val viewRect = Rect(
            viewLocationInWindow[0], viewLocationInWindow[1],
            viewLocationInWindow[0] + view.width, viewLocationInWindow[1] + view.height
    )

    // Include padding if needed
    if (includePadding) {
        viewRect.left += view.paddingLeft
        viewRect.top += view.paddingTop
        viewRect.right -= view.paddingRight
        viewRect.bottom -= view.paddingBottom
    }

    val featureRectInView = Rect(displayFeature.bounds)
    val intersects = featureRectInView.intersect(viewRect)
    if ((featureRectInView.width() == 0 && featureRectInView.height() == 0) ||
            !intersects
    ) {
        return null
    }

    // Offset the feature coordinates to view coordinate space start point
    featureRectInView.offset(-viewLocationInWindow[0], -viewLocationInWindow[1])

    return featureRectInView
}

/**
 * Gets the layout params for placing a rectangle indicating a display feature inside a
 * [FrameLayout].
 */
fun getLayoutParamsForFeatureInFrameLayout(displayFeature: DisplayFeature, view: FrameLayout):
        FrameLayout.LayoutParams? {
    val featureRectInView = getFeaturePositionInViewRect(displayFeature, view) ?: return null

    val lp = FrameLayout.LayoutParams(featureRectInView.width(), featureRectInView.height())
    lp.leftMargin = featureRectInView.left
    lp.topMargin = featureRectInView.top

    return lp
}