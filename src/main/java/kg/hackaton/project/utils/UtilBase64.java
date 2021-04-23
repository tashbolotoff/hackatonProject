package kg.hackaton.project.utils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class UtilBase64 {
    public static String encoder(MultipartFile multipartFile) {
        try {
            return "data:"+multipartFile.getContentType()+";base64," + Base64.getEncoder().encodeToString(multipartFile.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
