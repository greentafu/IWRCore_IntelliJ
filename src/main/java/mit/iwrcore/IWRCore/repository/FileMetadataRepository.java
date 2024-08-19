package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {
}
