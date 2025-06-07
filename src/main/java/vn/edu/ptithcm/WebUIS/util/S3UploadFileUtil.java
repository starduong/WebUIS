package vn.edu.ptithcm.WebUIS.util;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3UploadFileUtil {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.cloudfront.domain}")
    private String cloudFrontDomain;

    private final S3Client s3Client;

    public S3UploadFileUtil(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file, String folder) throws IOException {
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();

        // Đảm bảo không có dấu / dư thừa
        String normalizedFolder = folder.endsWith("/") ? folder : folder + "/";
        String key = normalizedFolder + filename;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                // .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        return cloudFrontDomain + "/" + key;
    }

    public boolean isValidFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }

}