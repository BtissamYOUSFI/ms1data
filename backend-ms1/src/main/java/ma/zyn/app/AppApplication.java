package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;


import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.service.facade.admin.utilisateurs.CollaborateurAdminService;
import ma.zyn.app.zynerator.security.bean.*;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.*;

import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import ma.zyn.app.service.facade.admin.accompagnement.EtatReunionAdminService;
import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.service.facade.admin.profil.EtatInscriptionAdminService;
import ma.zyn.app.bean.core.profil.Langue;
import ma.zyn.app.service.facade.admin.profil.LangueAdminService;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.service.facade.admin.transaction.StatusPaiementAdminService;
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.service.facade.admin.transaction.MoyenPaiementAdminService;
import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.service.facade.admin.profil.NiveauLangueAdminService;
import ma.zyn.app.bean.core.profil.Metier;
import ma.zyn.app.service.facade.admin.profil.MetierAdminService;

import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class AppApplication {
    public static ConfigurableApplicationContext ctx;


    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx = SpringApplication.run(AppApplication.class, args);
    }


    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService, CollaborateurAdminService collaborateurService) {
        return (args) -> {
            if (true) {

                createEtatReunion();
                createEtatInscription();
                createLangue();
                createStatusPaiement();
                createMoyenPaiement();
                createNiveauLangue();
                createMetier();

        /*
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));
        */

                // User Admin
                User userForAdmin = new User("admin");
                userForAdmin.setPassword("123");
                // Role Admin
                Role roleForAdmin = new Role();
                roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
                roleForAdmin.setCreatedAt(LocalDateTime.now());
                Role roleForAdminSaved = roleService.create(roleForAdmin);
                RoleUser roleUserForAdmin = new RoleUser();
                roleUserForAdmin.setRole(roleForAdminSaved);
                if (userForAdmin.getRoleUsers() == null)
                    userForAdmin.setRoleUsers(new ArrayList<>());

                userForAdmin.getRoleUsers().add(roleUserForAdmin);


                userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

                userService.create(userForAdmin);

                // User Collaborateur
                Collaborateur userForCollaborateur = new Collaborateur("collaborateur");
                userForCollaborateur.setPassword("123");
                // Role Collaborateur
                Role roleForCollaborateur = new Role();
                roleForCollaborateur.setAuthority(AuthoritiesConstants.COLLABORATEUR);
                roleForCollaborateur.setCreatedAt(LocalDateTime.now());
                Role roleForCollaborateurSaved = roleService.create(roleForCollaborateur);
                RoleUser roleUserForCollaborateur = new RoleUser();
                roleUserForCollaborateur.setRole(roleForCollaborateurSaved);
                if (userForCollaborateur.getRoleUsers() == null)
                    userForCollaborateur.setRoleUsers(new ArrayList<>());

                userForCollaborateur.getRoleUsers().add(roleUserForCollaborateur);


                userForCollaborateur.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

                collaborateurService.create(userForCollaborateur);


                // User Admin
//                User userForManager = new User("manager");
//                userForManager.setPassword("123");
//                // Role Admin
//                Role roleForManager = new Role();
//                roleForManager.setAuthority(AuthoritiesConstants.MANAGER);
//                roleForManager.setCreatedAt(LocalDateTime.now());
//                Role roleForManagerSaved = roleService.create(roleForManager);
//                RoleUser roleUserForManager = new RoleUser();
//                roleUserForManager.setRole(roleForManagerSaved);
//                if (userForManager.getRoleUsers() == null)
//                    userForManager.setRoleUsers(new ArrayList<>());
//
//                userForManager.getRoleUsers().add(roleUserForManager);
//
//
//                userForManager.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());
//
//                userService.create(userForManager);
            }
        };
    }


    private void createEtatReunion() {
        EtatReunion itemPrimary = new EtatReunion();
        itemPrimary.setStyle("primary");
        itemPrimary.setLibelle("Terminee");
        itemPrimary.setCode("Terminee");
        etatReunionService.create(itemPrimary);
        EtatReunion itemSuccess = new EtatReunion();
        itemSuccess.setStyle("success");
        itemSuccess.setLibelle("Confirmee");
        itemSuccess.setCode("Confirmee");
        etatReunionService.create(itemSuccess);
        EtatReunion itemWarning = new EtatReunion();
        itemWarning.setStyle("warning");
        itemWarning.setLibelle("APlanifier");
        itemWarning.setCode("APlanifier");
        etatReunionService.create(itemWarning);

    }

    private void createEtatInscription() {
        EtatInscription itemInfo = new EtatInscription();
        itemInfo.setStyle("info");
        itemInfo.setLibelle("New");
        itemInfo.setCode("New");
        etatInscriptionService.create(itemInfo);
        EtatInscription itemPrimary = new EtatInscription();
        itemPrimary.setStyle("primary");
        itemPrimary.setLibelle("Validated");
        itemPrimary.setCode("Validated");
        etatInscriptionService.create(itemPrimary);
        EtatInscription itemDanger = new EtatInscription();
        itemDanger.setStyle("danger");
        itemDanger.setLibelle("Refused");
        itemDanger.setCode("Refused");
        etatInscriptionService.create(itemDanger);
        EtatInscription itemSuccess = new EtatInscription();
        itemSuccess.setStyle("success");
        itemSuccess.setLibelle("Pending");
        itemSuccess.setCode("Pending");
        etatInscriptionService.create(itemSuccess);

    }

    private void createLangue() {
        Langue itemSuccess = new Langue();
        itemSuccess.setStyle("success");
        itemSuccess.setLibelle("Anglais");
        itemSuccess.setCode("Anglais");
        langueService.create(itemSuccess);
        Langue itemInfo = new Langue();
        itemInfo.setStyle("info");
        itemInfo.setLibelle("Francais");
        itemInfo.setCode("Francais");
        langueService.create(itemInfo);
        Langue itemPrimary = new Langue();
        itemPrimary.setStyle("primary");
        itemPrimary.setLibelle("Bilingue");
        itemPrimary.setCode("Bilingue");
        langueService.create(itemPrimary);

    }

    private void createStatusPaiement() {
        StatusPaiement itemDanger = new StatusPaiement();
        itemDanger.setStyle("danger");
        itemDanger.setLibelle("Annule");
        itemDanger.setCode("Annule");
        statusPaiementService.create(itemDanger);
        StatusPaiement itemWarning = new StatusPaiement();
        itemWarning.setStyle("warning");
        itemWarning.setLibelle("EnCours");
        itemWarning.setCode("EnCours");
        statusPaiementService.create(itemWarning);
        StatusPaiement itemSuccess = new StatusPaiement();
        itemSuccess.setStyle("success");
        itemSuccess.setLibelle("Effectue");
        itemSuccess.setCode("Effectue");
        statusPaiementService.create(itemSuccess);

    }

    private void createMoyenPaiement() {
        MoyenPaiement itemSuccess = new MoyenPaiement();
        itemSuccess.setStyle("success");
        itemSuccess.setLibelle("Carte");
        itemSuccess.setCode("Carte");
        moyenPaiementService.create(itemSuccess);
        MoyenPaiement itemInfo = new MoyenPaiement();
        itemInfo.setStyle("info");
        itemInfo.setLibelle("Especes");
        itemInfo.setCode("Especes");
        moyenPaiementService.create(itemInfo);
        MoyenPaiement itemPrimary = new MoyenPaiement();
        itemPrimary.setStyle("primary");
        itemPrimary.setLibelle("EnLigne");
        itemPrimary.setCode("EnLigne");
        moyenPaiementService.create(itemPrimary);

    }

    private void createNiveauLangue() {
        NiveauLangue itemInfo = new NiveauLangue();
        itemInfo.setStyle("info");
        itemInfo.setLibelle("Debutant");
        itemInfo.setCode("Debutant");
        niveauLangueService.create(itemInfo);
        NiveauLangue itemWarning = new NiveauLangue();
        itemWarning.setStyle("warning");
        itemWarning.setLibelle("Intermediaire");
        itemWarning.setCode("Intermediaire");
        niveauLangueService.create(itemWarning);
        NiveauLangue itemSuccess = new NiveauLangue();
        itemSuccess.setStyle("success");
        itemSuccess.setLibelle("Avance");
        itemSuccess.setCode("Avance");
        niveauLangueService.create(itemSuccess);

    }

    private void createMetier() {
        Metier itemSuccess = new Metier();
        itemSuccess.setStyle("success");
        itemSuccess.setLibelle("Electricien");
        itemSuccess.setCode("Electricien");
        metierService.create(itemSuccess);
        Metier itemDanger = new Metier();
        itemDanger.setStyle("danger");
        itemDanger.setLibelle("Manœuvre");
        itemDanger.setCode("Manœuvre");
        metierService.create(itemDanger);
        Metier itemSecondary = new Metier();
        itemSecondary.setStyle("secondary");
        itemSecondary.setLibelle("Menuisier");
        itemSecondary.setCode("Menuisier");
        metierService.create(itemSecondary);
        Metier itemPrimary = new Metier();
        itemPrimary.setStyle("primary");
        itemPrimary.setLibelle("Carreleur");
        itemPrimary.setCode("Carreleur");
        metierService.create(itemPrimary);
        Metier itemInfo = new Metier();
        itemInfo.setStyle("info");
        itemInfo.setLibelle("Plombier");
        itemInfo.setCode("Plombier");
        metierService.create(itemInfo);
        Metier koko = new Metier();
        koko.setStyle("success");
        koko.setLibelle("Soudeur");
        koko.setCode("Soudeur");
        metierService.create(koko);
        Metier itemWarning = new Metier();
        itemWarning.setStyle("warning");
        itemWarning.setLibelle("Peintre");
        itemWarning.setCode("Peintre");
        metierService.create(itemWarning);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return 10L * i;
    }

    private static Integer fakeInteger(String attributeName, int i) {
        return 10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return BigDecimal.valueOf(i * 1L * 10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }

    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("EtatReunion"));
        modelPermissions.add(new ModelPermission("Reunion"));
        modelPermissions.add(new ModelPermission("EtatInscription"));
        modelPermissions.add(new ModelPermission("Langue"));
        modelPermissions.add(new ModelPermission("StatusPaiement"));
        modelPermissions.add(new ModelPermission("ReferentielMetier"));
        modelPermissions.add(new ModelPermission("Inscription"));
        modelPermissions.add(new ModelPermission("Paiement"));
        modelPermissions.add(new ModelPermission("MoyenPaiement"));
        modelPermissions.add(new ModelPermission("Collaborateur"));
        modelPermissions.add(new ModelPermission("NiveauLangue"));
        modelPermissions.add(new ModelPermission("Metier"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    EtatReunionAdminService etatReunionService;
    @Autowired
    EtatInscriptionAdminService etatInscriptionService;
    @Autowired
    LangueAdminService langueService;
    @Autowired
    StatusPaiementAdminService statusPaiementService;
    @Autowired
    MoyenPaiementAdminService moyenPaiementService;
    @Autowired
    NiveauLangueAdminService niveauLangueService;
    @Autowired
    MetierAdminService metierService;
}


