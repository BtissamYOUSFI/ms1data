package ma.zyn.app.unit.dao.facade.core.profil;

import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.dao.facade.core.profil.InscriptionDao;

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
import ma.zyn.app.bean.core.profil.EtatInscription ;
import ma.zyn.app.bean.core.profil.Langue ;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class InscriptionDaoTest {

@Autowired
    private InscriptionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Inscription entity = new Inscription();
        entity.setCode(code);
        underTest.save(entity);
        Inscription loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Inscription loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Inscription entity = new Inscription();
        entity.setId(id);
        underTest.save(entity);
        Inscription loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Inscription entity = new Inscription();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Inscription loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Inscription> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Inscription> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Inscription given = constructSample(1);
        Inscription saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Inscription constructSample(int i) {
		Inscription given = new Inscription();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setNom("nom-"+i);
        given.setPrenom("prenom-"+i);
        given.setEmail("email-"+i);
        given.setPassword("password-"+i);
        given.setLangue(new Langue(1L));
        given.setNiveauLangue(new NiveauLangue(1L));
        given.setMetier(new Metier(1L));
        given.setNombreHeureExperience(i);
        given.setEtatInscription(new EtatInscription(1L));
        given.setCollaborateur(new Collaborateur(1L));
        return given;
    }

}
