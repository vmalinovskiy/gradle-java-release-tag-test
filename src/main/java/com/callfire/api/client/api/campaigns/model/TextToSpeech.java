package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TTS representation
 */
public class TextToSpeech extends CallfireModel {
    private Voice voice;
    private String message;

    /**
     * Get TTS voice
     *
     * @return voice type
     */
    public Voice getVoice() {
        return voice;
    }

    /**
     * Set TTS voice
     *
     * @param voice voice type
     */
    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    /**
     * Get TTS text message
     *
     * @return text message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set TTS text message
     *
     * @param message text message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("voice", voice)
            .append("message", message)
            .toString();
    }
}
