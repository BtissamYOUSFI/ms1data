package ma.zyn.app.unit.dao.facade.core.transaction;

import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.dao.facade.core.transaction.MoyenPaiementDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MoyenPaiementDaoTest {

@Autowired
    private MoyenPaiementDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        MoyenPaiement entity = new MoyenPaiement();
        entity.setCode(code);
        underTest.save(entity);
        MoyenPaiement loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        MoyenPaiement loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        MoyenPaiement entity = new MoyenPaiement();
        entity.setId(id);
        underTest.save(entity);
        MoyenPaiement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        MoyenPaiement entity = new MoyenPaiement();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        MoyenPaiement loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<MoyenPaiement> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<MoyenPaiement> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        MoyenPaiement given = constructSample(1);
        MoyenPaiement saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private MoyenPaiement constructSample(int i) {
		MoyenPaiement given = new MoyenPaiement();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
