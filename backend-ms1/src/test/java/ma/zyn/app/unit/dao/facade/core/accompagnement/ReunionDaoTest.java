package ma.zyn.app.unit.dao.facade.core.accompagnement;

import ma.zyn.app.bean.core.accompagnement.Reunion;
import ma.zyn.app.dao.facade.core.accompagnement.ReunionDao;

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

import ma.zyn.app.bean.core.accompagnement.EtatReunion ;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReunionDaoTest {

@Autowired
    private ReunionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Reunion entity = new Reunion();
        entity.setCode(code);
        underTest.save(entity);
        Reunion loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Reunion loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Reunion entity = new Reunion();
        entity.setId(id);
        underTest.save(entity);
        Reunion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Reunion entity = new Reunion();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Reunion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Reunion> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Reunion> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Reunion given = constructSample(1);
        Reunion saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Reunion constructSample(int i) {
		Reunion given = new Reunion();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setCollaborateur(new Collaborateur(1L));
        given.setEtatReunion(new EtatReunion(1L));
        return given;
    }

}
