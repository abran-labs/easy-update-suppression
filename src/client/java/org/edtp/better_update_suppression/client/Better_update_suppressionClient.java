package org.edtp.better_update_suppression.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.edtp.better_update_suppression.Better_update_suppression;

public class Better_update_suppressionClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(Better_update_suppression.ModCheckPayload.ID, (payload, context) -> {
            // No-op handler - just needs to exist so the server can detect this client has the mod
        });
    }
}
