package gloit.hiperionida.helios.util.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericDTO<E> extends JpaRepository<E, Long> {
    Optional<E> findById(long id);
    Optional<E> findByIdAndEliminadaIsNull(long id);
    List<E> findAllByEliminadaIsNull();
    Long countAllByEliminadaIsNull();

    Slice<E> findAllBy(PageRequest pageRequest);
    Slice<E> findAllByEliminadaIsNull(PageRequest pageRequest);
}
