package br.com.alura.literalura.Repository;

import br.com.alura.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual
            (Integer ano1, Integer ano2);
}