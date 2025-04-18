package ma.zyn.app.unit.dao.facade.core.profil;

import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.dao.facade.core.profil.NiveauLangueDao;

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
public class NiveauLangueDaoTest {

@Autowired
    private NiveauLangueDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        NiveauLangue entity = new NiveauLangue();
        entity.setCode(code);
        underTest.save(entity);
        NiveauLangue loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        NiveauLangue loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        NiveauLangue entity = new NiveauLangue();
        entity.setId(id);
        underTest.save(entity);
        NiveauLangue loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        NiveauLangue entity = new NiveauLangue();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        NiveauLangue loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<NiveauLangue> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<NiveauLangue> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        NiveauLangue given = constructSample(1);
        NiveauLangue saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private NiveauLangue constructSample(int i) {
		NiveauLangue given = new NiveauLangue();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
