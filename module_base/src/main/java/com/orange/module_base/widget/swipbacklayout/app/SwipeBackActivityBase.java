package com.orange.module_base.widget.swipbacklayout.app;

import com.orange.module_base.widget.swipbacklayout.SwipeBackLayout;

/**
 * @author Yrom
 */
public interface SwipeBackActivityBase {
    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}
