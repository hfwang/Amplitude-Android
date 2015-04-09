package com.amplitude.api;

import org.json.JSONObject;

import android.util.Log;

public class OfflineAmplitudeClient extends AmplitudeClient {

    public static final String TAG = "com.amplitude.api.OfflineAmplitudeClient";

    protected static final OfflineAmplitudeClient instance = new OfflineAmplitudeClient();

    public static OfflineAmplitudeClient getInstance() {
        return instance;
    }

    protected OfflineAmplitudeClient() {
        super();
    }

    @Override
    protected void asyncLogEvent(final String eventType, final JSONObject eventProperties,
            final JSONObject apiProperties, final long timestamp, final boolean checkSession) {

        // Run logEvent synchronously
        logEvent(eventType, eventProperties, apiProperties, timestamp, checkSession);
    }

    @Override
    protected void updateServer(boolean limit) { return; }

    /**
     * Manually force the client to send saved events to the server.
     */
    public void forceSend() {
        runOnLogThread(new Runnable() {
            @Override
            public void run() {
                OfflineAmplitudeClient.super.updateServer(true);
            }
        });
    }
}
