package com.plotsquared.plothider;

import com.plotsquared.core.configuration.caption.TranslatableCaption;
import com.plotsquared.core.plot.flag.types.BooleanFlag;
import org.jetbrains.annotations.NotNull;

public class HideFlag extends BooleanFlag<HideFlag> {

    public static final HideFlag HIDE_FLAG_TRUE = new HideFlag(true);
    public static final HideFlag HIDE_FLAG_FALSE = new HideFlag(false);

    private HideFlag(boolean value) {
        super(value, TranslatableCaption.of(PlotHiderPlugin.PLOT_HIDER_NAMESPACE, "flags.flag_description_hide"));
    }

    @Override
    protected HideFlag flagOf(@NotNull Boolean value) {
        return value ? HIDE_FLAG_TRUE : HIDE_FLAG_FALSE;
    }

}
