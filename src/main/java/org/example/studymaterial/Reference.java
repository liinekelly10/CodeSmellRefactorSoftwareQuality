package org.example.studymaterial;

public abstract class Reference {
        private String title;
        private String description;
        private String link;
        private String accessRights;
        private String license;
        private boolean isDownloadable;
        private int rating;
        private String language;
        private int viewCount;
        private int downloadCount;
        private int shareCount;

        // Getters
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getLink() { return link; }
        public String getAccessRights() { return accessRights; }
        public String getLicense() { return license; }
        public boolean getIsDownloadable() { return isDownloadable; }
        public int getRating() { return rating; }
        public String getLanguage() { return language; }
        public int getViewCount() { return viewCount; }
        public int getDownloadCount() { return downloadCount; }
        public int getShareCount() { return shareCount; }

        // Setters
        public void setTitle(String title) { this.title = title; }
        public void setDescription(String description) { this.description = description; }
        public void setLink(String link) { this.link = link; }
        public void setAccessRights(String accessRights) { this.accessRights = accessRights; }
        public void setLicense(String license) { this.license = license; }
        public void setDownloadable(boolean isDownloadable) { this.isDownloadable = isDownloadable; }
        public void setRating(int rating) { this.rating = rating; }
        public void setLanguage(String language) { this.language = language; }
        public void setViewCount(int viewCount) { this.viewCount = viewCount; }
        public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }
        public void setShareCount(int shareCount) { this.shareCount = shareCount; }

        // Métodos de negócio existentes
        public void updateMetadata(String title, String description, String link) {
                this.title = title;
                this.description = description;
                this.link = link;
        }

        public void updateAccess(String accessRights, String license, boolean downloadable) {
                this.accessRights = accessRights;
                this.license = license;
                this.isDownloadable = downloadable;
        }

        public void updateStats(int rating, String language, int views, int downloads, int shares) {
                this.rating = rating;
                this.language = language;
                this.viewCount = views;
                this.downloadCount = downloads;
                this.shareCount = shares;
        }

        public void incrementViewCount() { this.viewCount++; }
        public void incrementDownloadCount() { this.downloadCount++; }
        public void incrementShareCount() { this.shareCount++; }

        public boolean isPopular() { return viewCount > 1000 || downloadCount > 500; }

        // 🔥 Novos métodos para dar comportamento real à classe:
        public boolean isReadyForPublishing() {
                return title != null && !title.isEmpty()
                        && description != null && !description.isEmpty()
                        && link != null && !link.isEmpty()
                        && language != null && !language.isEmpty()
                        && accessRights != null && !accessRights.isEmpty()
                        && license != null && !license.isEmpty();
        }

        public int getEngagementScore() {
                return viewCount * 1 + downloadCount * 3 + shareCount * 5;
        }

        public boolean isOpenLicense() {
                return license != null && (license.equalsIgnoreCase("CC-BY") || license.equalsIgnoreCase("Public Domain"));
        }
}
