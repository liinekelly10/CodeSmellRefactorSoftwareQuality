package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    /**
     * Método editAudio que recebe o parameter object com builder.
     */
    public void editAudio(AudioEditParams params) {
        editBasic(params.title, params.description, params.link);
        this.setAccessRights(params.accessRights);
        this.setLicense(params.license);
        this.setAudioQuality(params.audioQuality);
        editVideoAttributes(
                params.rating,
                params.language,
                params.viewCount,
                params.shareCount,
                params.isDownloadable
        );
    }

    /**
     * Adapter que monta AudioEditParams a partir de listas e chama editAudio.
     */
    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        AudioEditParams params = AudioEditParams.builder()
                .audioQuality(audioQuality)
                .isDownloadable(isDownloadable)
                .title(properties.get(0))
                .description(properties.get(1))
                .link(properties.get(2))
                .accessRights(properties.get(3))
                .license(properties.get(4))
                .language(properties.get(5))
                .rating(intProperties.get(0))
                .viewCount(intProperties.get(1))
                .shareCount(intProperties.get(2))
                .build();
        this.editAudio(params);
    }

    private void editVideoAttributes(int rating, String language, int viewCount, int shareCount, boolean isDownloadable) {
        this.setRating(rating);
        this.setShareCount(shareCount);
        this.setViewCount(viewCount);
        this.setDownloadable(isDownloadable);
        this.setLanguage(language);
    }

    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

    /**
     * Parameter Object para o método editAudio com Builder Pattern.
     */
    public static class AudioEditParams {
        private AudioQuality audioQuality;
        private boolean isDownloadable;
        private String title;
        private String description;
        private String link;
        private String accessRights;
        private String license;
        private String language;
        private int rating;
        private int viewCount;
        private int shareCount;

        private AudioEditParams() {
            // Construtor privado para forçar uso do builder
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private final AudioEditParams params = new AudioEditParams();

            public Builder audioQuality(AudioQuality audioQuality) {
                params.audioQuality = audioQuality;
                return this;
            }

            public Builder isDownloadable(boolean isDownloadable) {
                params.isDownloadable = isDownloadable;
                return this;
            }

            public Builder title(String title) {
                params.title = title;
                return this;
            }

            public Builder description(String description) {
                params.description = description;
                return this;
            }

            public Builder link(String link) {
                params.link = link;
                return this;
            }

            public Builder accessRights(String accessRights) {
                params.accessRights = accessRights;
                return this;
            }

            public Builder license(String license) {
                params.license = license;
                return this;
            }

            public Builder language(String language) {
                params.language = language;
                return this;
            }

            public Builder rating(int rating) {
                params.rating = rating;
                return this;
            }

            public Builder viewCount(int viewCount) {
                params.viewCount = viewCount;
                return this;
            }

            public Builder shareCount(int shareCount) {
                params.shareCount = shareCount;
                return this;
            }

            public AudioEditParams build() {
                return params;
            }
        }
    }
}
