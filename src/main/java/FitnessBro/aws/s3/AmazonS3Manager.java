package FitnessBro.aws.s3;

import FitnessBro.config.AmazonConfig;
import FitnessBro.domain.common.Uuid;
import FitnessBro.respository.UuidRepository;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file){

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/png");
        metadata.setContentLength(file.getSize());

        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        }catch (IOException e){
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public void deleteFile(String keyName) {
        try{
            amazonS3.deleteObject(amazonConfig.getBucket(), keyName);
        } catch (AmazonServiceException e) {
            // Amazon S3 서비스 예외 처리
            log.error("Amazon S3 service exception occurred while deleting file: {}", e.getMessage());
            throw new RuntimeException("Failed to delete file from Amazon S3", e);
        } catch (SdkClientException e) {
            // 클라이언트 예외 처리
            log.error("Amazon S3 client exception occurred while deleting file: {}", e.getMessage());
            throw new RuntimeException("Failed to delete file from Amazon S3", e);
        }
    }

    public String generateProfileKeyName(Uuid uuid){
        return amazonConfig.getProfilePath() + '/' + uuid.getUuid();
    }

    public String generateReviewKeyName(Uuid uuid){
        return amazonConfig.getReviewPath() + '/' + uuid.getUuid();
    }

    public String generateAlbumKeyName(Uuid uuid) {
        return amazonConfig.getAlbumPath() + '/' + uuid.getUuid();
    }
}
