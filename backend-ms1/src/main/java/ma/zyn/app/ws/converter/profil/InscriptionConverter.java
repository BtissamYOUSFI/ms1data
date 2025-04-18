package  ma.zyn.app.ws.converter.profil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.profil.MetierConverter;
import ma.zyn.app.bean.core.profil.Metier;
import ma.zyn.app.ws.converter.profil.NiveauLangueConverter;
import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.ws.converter.profil.EtatInscriptionConverter;
import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.ws.converter.profil.LangueConverter;
import ma.zyn.app.bean.core.profil.Langue;
import ma.zyn.app.ws.converter.utilisateurs.CollaborateurConverter;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.ws.dto.profil.InscriptionDto;

@Component
public class InscriptionConverter {

    @Autowired
    private MetierConverter metierConverter ;
    @Autowired
    private NiveauLangueConverter niveauLangueConverter ;
    @Autowired
    private EtatInscriptionConverter etatInscriptionConverter ;
    @Autowired
    private LangueConverter langueConverter ;
    @Autowired
    private CollaborateurConverter collaborateurConverter ;
    private boolean langue;
    private boolean niveauLangue;
    private boolean metier;
    private boolean etatInscription;
    private boolean collaborateur;

    public  InscriptionConverter() {
        initObject(true);
    }

    public Inscription toItem(InscriptionDto dto) {
        if (dto == null) {
            return null;
        } else {
        Inscription item = new Inscription();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getPrenom()))
                item.setPrenom(dto.getPrenom());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getNombreHeureExperience()))
                item.setNombreHeureExperience(dto.getNombreHeureExperience());
            if(this.langue && dto.getLangue()!=null)
                item.setLangue(langueConverter.toItem(dto.getLangue())) ;

            if(this.niveauLangue && dto.getNiveauLangue()!=null)
                item.setNiveauLangue(niveauLangueConverter.toItem(dto.getNiveauLangue())) ;

            if(this.metier && dto.getMetier()!=null)
                item.setMetier(metierConverter.toItem(dto.getMetier())) ;

            if(this.etatInscription && dto.getEtatInscription()!=null)
                item.setEtatInscription(etatInscriptionConverter.toItem(dto.getEtatInscription())) ;

            if(this.collaborateur && dto.getCollaborateur()!=null)
                item.setCollaborateur(collaborateurConverter.toItem(dto.getCollaborateur())) ;




        return item;
        }
    }


    public InscriptionDto toDto(Inscription item) {
        if (item == null) {
            return null;
        } else {
            InscriptionDto dto = new InscriptionDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getPrenom()))
                dto.setPrenom(item.getPrenom());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getPassword()))
                dto.setPassword(item.getPassword());
            if(StringUtil.isNotEmpty(item.getNombreHeureExperience()))
                dto.setNombreHeureExperience(item.getNombreHeureExperience());
            if(this.langue && item.getLangue()!=null) {
                dto.setLangue(langueConverter.toDto(item.getLangue())) ;

            }
            if(this.niveauLangue && item.getNiveauLangue()!=null) {
                dto.setNiveauLangue(niveauLangueConverter.toDto(item.getNiveauLangue())) ;

            }
            if(this.metier && item.getMetier()!=null) {
                dto.setMetier(metierConverter.toDto(item.getMetier())) ;

            }
            if(this.etatInscription && item.getEtatInscription()!=null) {
                dto.setEtatInscription(etatInscriptionConverter.toDto(item.getEtatInscription())) ;

            }
            if(this.collaborateur && item.getCollaborateur()!=null) {
                dto.setCollaborateur(collaborateurConverter.toDto(item.getCollaborateur())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.langue = value;
        this.niveauLangue = value;
        this.metier = value;
        this.etatInscription = value;
        this.collaborateur = value;
    }
	
    public List<Inscription> toItem(List<InscriptionDto> dtos) {
        List<Inscription> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionDto> toDto(List<Inscription> items) {
        List<InscriptionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Inscription item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionDto dto, Inscription t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getLangue() == null  && dto.getLangue() != null){
            t.setLangue(new Langue());
        }else if (t.getLangue() != null  && dto.getLangue() != null){
            t.setLangue(null);
            t.setLangue(new Langue());
        }
        if(t.getNiveauLangue() == null  && dto.getNiveauLangue() != null){
            t.setNiveauLangue(new NiveauLangue());
        }else if (t.getNiveauLangue() != null  && dto.getNiveauLangue() != null){
            t.setNiveauLangue(null);
            t.setNiveauLangue(new NiveauLangue());
        }
        if(t.getMetier() == null  && dto.getMetier() != null){
            t.setMetier(new Metier());
        }else if (t.getMetier() != null  && dto.getMetier() != null){
            t.setMetier(null);
            t.setMetier(new Metier());
        }
        if(t.getEtatInscription() == null  && dto.getEtatInscription() != null){
            t.setEtatInscription(new EtatInscription());
        }else if (t.getEtatInscription() != null  && dto.getEtatInscription() != null){
            t.setEtatInscription(null);
            t.setEtatInscription(new EtatInscription());
        }
        if(t.getCollaborateur() == null  && dto.getCollaborateur() != null){
            t.setCollaborateur(new Collaborateur());
        }else if (t.getCollaborateur() != null  && dto.getCollaborateur() != null){
            t.setCollaborateur(null);
            t.setCollaborateur(new Collaborateur());
        }
        if (dto.getLangue() != null)
        langueConverter.copy(dto.getLangue(), t.getLangue());
        if (dto.getNiveauLangue() != null)
        niveauLangueConverter.copy(dto.getNiveauLangue(), t.getNiveauLangue());
        if (dto.getMetier() != null)
        metierConverter.copy(dto.getMetier(), t.getMetier());
        if (dto.getEtatInscription() != null)
        etatInscriptionConverter.copy(dto.getEtatInscription(), t.getEtatInscription());
        if (dto.getCollaborateur() != null)
        collaborateurConverter.copy(dto.getCollaborateur(), t.getCollaborateur());
    }

    public List<Inscription> copy(List<InscriptionDto> dtos) {
        List<Inscription> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionDto dto : dtos) {
                Inscription instance = new Inscription();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public MetierConverter getMetierConverter(){
        return this.metierConverter;
    }
    public void setMetierConverter(MetierConverter metierConverter ){
        this.metierConverter = metierConverter;
    }
    public NiveauLangueConverter getNiveauLangueConverter(){
        return this.niveauLangueConverter;
    }
    public void setNiveauLangueConverter(NiveauLangueConverter niveauLangueConverter ){
        this.niveauLangueConverter = niveauLangueConverter;
    }
    public EtatInscriptionConverter getEtatInscriptionConverter(){
        return this.etatInscriptionConverter;
    }
    public void setEtatInscriptionConverter(EtatInscriptionConverter etatInscriptionConverter ){
        this.etatInscriptionConverter = etatInscriptionConverter;
    }
    public LangueConverter getLangueConverter(){
        return this.langueConverter;
    }
    public void setLangueConverter(LangueConverter langueConverter ){
        this.langueConverter = langueConverter;
    }
    public CollaborateurConverter getCollaborateurConverter(){
        return this.collaborateurConverter;
    }
    public void setCollaborateurConverter(CollaborateurConverter collaborateurConverter ){
        this.collaborateurConverter = collaborateurConverter;
    }
    public boolean  isLangue(){
        return this.langue;
    }
    public void  setLangue(boolean langue){
        this.langue = langue;
    }
    public boolean  isNiveauLangue(){
        return this.niveauLangue;
    }
    public void  setNiveauLangue(boolean niveauLangue){
        this.niveauLangue = niveauLangue;
    }
    public boolean  isMetier(){
        return this.metier;
    }
    public void  setMetier(boolean metier){
        this.metier = metier;
    }
    public boolean  isEtatInscription(){
        return this.etatInscription;
    }
    public void  setEtatInscription(boolean etatInscription){
        this.etatInscription = etatInscription;
    }
    public boolean  isCollaborateur(){
        return this.collaborateur;
    }
    public void  setCollaborateur(boolean collaborateur){
        this.collaborateur = collaborateur;
    }
}
