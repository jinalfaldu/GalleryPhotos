package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi;

import java.io.Serializable;
import java.util.List;


public class PatternsResponse extends BaseResponse<PatternsResponse.PatternsCategory> {


    public static class PatternsCategory implements Serializable {
        String categoryName;
        List<PatternsPack> patternPack;

        public String getCategoryName() {
            return this.categoryName;
        }

        public List<PatternsPack> getPatternPack() {
            return this.patternPack;
        }
    }


    public static class PatternsPack implements Serializable {
        String identifier;
        boolean isWhitelisted;
        String licenseAgreementWebsite;
        String name;
        List<Pattern> patterns;
        String privacyPolicyWebsite;
        String publisher;
        String publisherEmail;
        String publisherWebsite;
        String trayImageFile;
        String trayImageUri;

        public String getTrayImageUri() {
            return this.trayImageUri;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public String getName() {
            return this.name;
        }

        public String getPublisher() {
            return this.publisher;
        }

        public String getTrayImageFile() {
            return this.trayImageFile;
        }

        public String getPublisherEmail() {
            return this.publisherEmail;
        }

        public String getPublisherWebsite() {
            return this.publisherWebsite;
        }

        public String getPrivacyPolicyWebsite() {
            return this.privacyPolicyWebsite;
        }

        public String getLicenseAgreementWebsite() {
            return this.licenseAgreementWebsite;
        }

        public boolean isWhitelisted() {
            return this.isWhitelisted;
        }

        public List<Pattern> getPatterns() {
            return this.patterns;
        }
    }


    public static class Pattern implements Serializable {
        String imageFileName;
        String uri;

        public String getImageFileName() {
            return this.imageFileName;
        }

        public String getUri() {
            return this.uri;
        }
    }
}
