package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi;

import java.io.Serializable;
import java.util.List;


public class EmojisResponse extends BaseResponse<EmojisResponse.EmojiCategory> {


    public static class EmojiCategory implements Serializable {
        String categoryName;
        List<EmojisPack> emojiPack;

        public String getCategoryName() {
            return this.categoryName;
        }

        public List<EmojisPack> getEmojiPack() {
            return this.emojiPack;
        }
    }


    public static class EmojisPack implements Serializable {
        List<Emoji> emojis;
        String identifier;
        boolean isWhitelisted;
        String licenseAgreementWebsite;
        String name;
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

        public List<Emoji> getEmojis() {
            return this.emojis;
        }

        public boolean isWhitelisted() {
            return this.isWhitelisted;
        }
    }


    public static class Emoji implements Serializable {
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
