import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Arthur Kupriyanov on 06.02.2020
 */
public class ImageHash {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        BufferedImage image = ImageIO.read(new File("src/main/resources/test-image-400-400.jpg"));

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(bytesFromBufferedImage(image));

        String stringHash = bytesToHex(hash);

        System.out.println(stringHash); // 872db4194fc9bf44e470016996518dd31e263dc95148406e3b2f41824b69e04f
    }

    private static byte[] bytesFromBufferedImage(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", bos);
        return bos.toByteArray();
    }

    private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte hashInByte : hashInBytes) {
            sb.append(Integer.toString((hashInByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
}
