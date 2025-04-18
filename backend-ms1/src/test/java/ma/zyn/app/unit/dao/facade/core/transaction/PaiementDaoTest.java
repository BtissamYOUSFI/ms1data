package ma.zyn.app.unit.dao.facade.core.transaction;

import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.dao.facade.core.transaction.PaiementDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.transaction.MoyenPaiement ;
import ma.zyn.app.bean.core.transaction.StatusPaiement ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PaiementDaoTest {

@Autowired
    private PaiementDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Paiement entity = new Paiement();
        entity.setCode(code);
        underTest.save(entity);
        Paiement loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Paiement loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Paiement entity = new Paiement();
        entity.setId(id);
        underTest.save(entity);
        Paiement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Paiement entity = new Paiement();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Paiement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Paiement> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Paiement> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Paiement given = constructSample(1);
        Paiement saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Paiement constructSample(int i) {
		Paiement given = new Paiement();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setDescription("description-"+i);
        given.setMontant(BigDecimal.TEN);
        given.setDatePaiement(LocalDateTime.now());
        given.setMoyenPaiement(new MoyenPaiement(1L));
        given.setStatusPaiement(new StatusPaiement(1L));
        return given;
    }

}
