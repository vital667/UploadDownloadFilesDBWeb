package uploadDownloadDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uploadDownloadDB.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class UploadDownloadDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadDownloadDbApplication.class, args);

	}

}
