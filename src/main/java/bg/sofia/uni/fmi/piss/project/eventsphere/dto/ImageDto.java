package bg.sofia.uni.fmi.piss.project.eventsphere.dto;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageDto {

    private String encodedImage;

    public ImageDto(String location) throws IOException {
        fromByteArrayToBase64String(location);
    }

    private void fromByteArrayToBase64String(String location) throws IOException {
        try (FileInputStream in = new FileInputStream(location)) {
            byte[] fileBytes = IOUtils.toByteArray(in);
            this.encodedImage = Base64.getEncoder().encodeToString(fileBytes);
        }
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}
