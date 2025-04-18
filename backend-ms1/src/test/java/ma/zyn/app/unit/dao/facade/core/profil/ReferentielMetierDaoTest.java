package ma.zyn.app.unit.dao.facade.core.profil;

import ma.zyn.app.bean.core.profil.ReferentielMetier;
import ma.zyn.app.dao.facade.core.profil.ReferentielMetierDao;

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

import ma.zyn.app.bean.core.profil.Metier ;
import ma.zyn.app.bean.core.profil.NiveauLangue ;
import ma.zyn.app.bean.core.profil.Langue ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReferentielMetierDaoTest {

@Autowired
    private ReferentielMetierDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ReferentielMetier entity = new ReferentielMetier();
        entity.setCode(code);
        underTest.save(entity);
        ReferentielMetier loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ReferentielMetier loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ReferentielMetier entity = new ReferentielMetier();
        entity.setId(id);
        underTest.save(entity);
        ReferentielMetier loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ReferentielMetier entity = new ReferentielMetier();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ReferentielMetier loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ReferentielMetier> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ReferentielMetier> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ReferentielMetier given = constructSample(1);
        ReferentielMetier saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ReferentielMetier constructSample(int i) {
		ReferentielMetier given = new ReferentielMetier();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setDescription("description-"+i);
        given.setMetier(new Metier(1L));
        given.setNombreHeuresExperienceMin(i);
        given.setLangue(new Langue(1L));
        given.setNiveauLangue(new NiveauLangue(1L));
        given.setScelleRouge(false);
        return given;
    }

}
