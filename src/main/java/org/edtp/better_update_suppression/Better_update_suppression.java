package org.edtp.better_update_suppression;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;

public class Better_update_suppression implements ModInitializer {
    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("better_update_suppression");
        PolymerResourcePackUtils.markAsRequired();
        UpdateSuppressionBlockFinal.init();
    }
}
