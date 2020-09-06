package uploadDownloadDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uploadDownloadDB.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile,String> {
}
