package  ma.zyn.app.ws.converter.accompagnement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.accompagnement.EtatReunionConverter;
import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import ma.zyn.app.ws.converter.utilisateurs.CollaborateurConverter;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.ws.converter.utilisateurs.ManagerConverter;
import ma.zyn.app.bean.core.utilisateurs.Manager;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.accompagnement.Reunion;
import ma.zyn.app.ws.dto.accompagnement.ReunionDto;

@Component
public class ReunionConverter {

    @Autowired
    private EtatReunionConverter etatReunionConverter ;
    @Autowired
    private CollaborateurConverter collaborateurConverter ;
    @Autowired
    private ManagerConverter managerConverter ;
    private boolean collaborateur;
    private boolean etatReunion;
    private boolean manager;

    public  ReunionConverter() {
        initObject(true);
    }

    public Reunion toItem(ReunionDto dto) {
        if (dto == null) {
            return null;
        } else {
            Reunion item = new Reunion();
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
            if(this.collaborateur && dto.getCollaborateur()!=null)
                item.setCollaborateur(collaborateurConverter.toItem(dto.getCollaborateur())) ;

            if(this.etatReunion && dto.getEtatReunion()!=null)
                item.setEtatReunion(etatReunionConverter.toItem(dto.getEtatReunion())) ;

            if(this.manager && dto.getManager()!=null)
                item.setManager(managerConverter.toItem(dto.getManager())) ;


            return item;
        }
    }


    public ReunionDto toDto(Reunion item) {
        if (item == null) {
            return null;
        } else {
            ReunionDto dto = new ReunionDto();
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
            if(this.collaborateur && item.getCollaborateur()!=null) {
                dto.setCollaborateur(collaborateurConverter.toDto(item.getCollaborateur())) ;

            }
            if(this.etatReunion && item.getEtatReunion()!=null) {
                dto.setEtatReunion(etatReunionConverter.toDto(item.getEtatReunion())) ;

            }
            if(this.manager && item.getManager()!=null) {
                dto.setManager(managerConverter.toDto(item.getManager())) ;

            }


            return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.collaborateur = value;
        this.etatReunion = value;
        this.manager = value;
    }

    public List<Reunion> toItem(List<ReunionDto> dtos) {
        List<Reunion> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ReunionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ReunionDto> toDto(List<Reunion> items) {
        List<ReunionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Reunion item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ReunionDto dto, Reunion t) {
        BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCollaborateur() == null  && dto.getCollaborateur() != null){
            t.setCollaborateur(new Collaborateur());
        }else if (t.getCollaborateur() != null  && dto.getCollaborateur() != null){
            t.setCollaborateur(null);
            t.setCollaborateur(new Collaborateur());
        }
        if(t.getEtatReunion() == null  && dto.getEtatReunion() != null){
            t.setEtatReunion(new EtatReunion());
        }else if (t.getEtatReunion() != null  && dto.getEtatReunion() != null){
            t.setEtatReunion(null);
            t.setEtatReunion(new EtatReunion());
        }
        if(t.getManager() == null  && dto.getManager() != null){
            t.setManager(new Manager());
        }else if (t.getManager() != null  && dto.getManager() != null){
            t.setManager(null);
            t.setManager(new Manager());
        }
        if (dto.getCollaborateur() != null)
            collaborateurConverter.copy(dto.getCollaborateur(), t.getCollaborateur());
        if (dto.getEtatReunion() != null)
            etatReunionConverter.copy(dto.getEtatReunion(), t.getEtatReunion());
        if (dto.getManager() != null)
            managerConverter.copy(dto.getManager(), t.getManager());
    }

    public List<Reunion> copy(List<ReunionDto> dtos) {
        List<Reunion> result = new ArrayList<>();
        if (dtos != null) {
            for (ReunionDto dto : dtos) {
                Reunion instance = new Reunion();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EtatReunionConverter getEtatReunionConverter(){
        return this.etatReunionConverter;
    }
    public void setEtatReunionConverter(EtatReunionConverter etatReunionConverter ){
        this.etatReunionConverter = etatReunionConverter;
    }
    public CollaborateurConverter getCollaborateurConverter(){
        return this.collaborateurConverter;
    }
    public void setCollaborateurConverter(CollaborateurConverter collaborateurConverter ){
        this.collaborateurConverter = collaborateurConverter;
    }
    public ManagerConverter getManagerConverter(){
        return this.managerConverter;
    }
    public void setManagerConverter(ManagerConverter managerConverter ){
        this.managerConverter = managerConverter;
    }
    public boolean  isCollaborateur(){
        return this.collaborateur;
    }
    public void  setCollaborateur(boolean collaborateur){
        this.collaborateur = collaborateur;
    }
    public boolean  isEtatReunion(){
        return this.etatReunion;
    }
    public void  setEtatReunion(boolean etatReunion){
        this.etatReunion = etatReunion;
    }
    public boolean  isManager(){
        return this.manager;
    }
    public void  setManager(boolean manager){
        this.manager = manager;
    }
}

//package  ma.zyn.app.ws.converter.accompagnement;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.beans.BeanUtils;
//import ma.zyn.app.zynerator.converter.AbstractConverterHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ma.zyn.app.ws.converter.accompagnement.EtatReunionConverter;
//import ma.zyn.app.bean.core.accompagnement.EtatReunion;
//import ma.zyn.app.ws.converter.utilisateurs.CollaborateurConverter;
//import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
//
//
//
//import ma.zyn.app.zynerator.util.StringUtil;
//import ma.zyn.app.zynerator.converter.AbstractConverter;
//import ma.zyn.app.zynerator.util.DateUtil;
//import ma.zyn.app.bean.core.accompagnement.Reunion;
//import ma.zyn.app.ws.dto.accompagnement.ReunionDto;
//
//@Component
//public class ReunionConverter {
//
//    @Autowired
//    private EtatReunionConverter etatReunionConverter ;
//    @Autowired
//    private CollaborateurConverter collaborateurConverter ;
//    private boolean collaborateur;
//    private boolean etatReunion;
//
//    public  ReunionConverter() {
//        initObject(true);
//    }
//
//    public Reunion toItem(ReunionDto dto) {
//        if (dto == null) {
//            return null;
//        } else {
//        Reunion item = new Reunion();
//            if(StringUtil.isNotEmpty(dto.getId()))
//                item.setId(dto.getId());
//            if(StringUtil.isNotEmpty(dto.getLibelle()))
//                item.setLibelle(dto.getLibelle());
//            if(StringUtil.isNotEmpty(dto.getCode()))
//                item.setCode(dto.getCode());
//            if(StringUtil.isNotEmpty(dto.getStyle()))
//                item.setStyle(dto.getStyle());
//            if(StringUtil.isNotEmpty(dto.getDescription()))
//                item.setDescription(dto.getDescription());
//            if(this.collaborateur && dto.getCollaborateur()!=null)
//                item.setCollaborateur(collaborateurConverter.toItem(dto.getCollaborateur())) ;
//
//            if(this.etatReunion && dto.getEtatReunion()!=null)
//                item.setEtatReunion(etatReunionConverter.toItem(dto.getEtatReunion())) ;
//
//
//
//
//        return item;
//        }
//    }
//
//
//    public ReunionDto toDto(Reunion item) {
//        if (item == null) {
//            return null;
//        } else {
//            ReunionDto dto = new ReunionDto();
//            if(StringUtil.isNotEmpty(item.getId()))
//                dto.setId(item.getId());
//            if(StringUtil.isNotEmpty(item.getLibelle()))
//                dto.setLibelle(item.getLibelle());
//            if(StringUtil.isNotEmpty(item.getCode()))
//                dto.setCode(item.getCode());
//            if(StringUtil.isNotEmpty(item.getStyle()))
//                dto.setStyle(item.getStyle());
//            if(StringUtil.isNotEmpty(item.getDescription()))
//                dto.setDescription(item.getDescription());
//            if(this.collaborateur && item.getCollaborateur()!=null) {
//                dto.setCollaborateur(collaborateurConverter.toDto(item.getCollaborateur())) ;
//
//            }
//            if(this.etatReunion && item.getEtatReunion()!=null) {
//                dto.setEtatReunion(etatReunionConverter.toDto(item.getEtatReunion())) ;
//
//            }
//
//
//        return dto;
//        }
//    }
//
//    public void init(boolean value) {
//        initObject(value);
//    }
//
//    public void initObject(boolean value) {
//        this.collaborateur = value;
//        this.etatReunion = value;
//    }
//
//    public List<Reunion> toItem(List<ReunionDto> dtos) {
//        List<Reunion> items = new ArrayList<>();
//        if (dtos != null && !dtos.isEmpty()) {
//            for (ReunionDto dto : dtos) {
//                items.add(toItem(dto));
//            }
//        }
//        return items;
//    }
//
//
//    public List<ReunionDto> toDto(List<Reunion> items) {
//        List<ReunionDto> dtos = new ArrayList<>();
//        if (items != null && !items.isEmpty()) {
//            for (Reunion item : items) {
//                dtos.add(toDto(item));
//            }
//        }
//        return dtos;
//    }
//
//
//    public void copy(ReunionDto dto, Reunion t) {
//		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
//        if(t.getCollaborateur() == null  && dto.getCollaborateur() != null){
//            t.setCollaborateur(new Collaborateur());
//        }else if (t.getCollaborateur() != null  && dto.getCollaborateur() != null){
//            t.setCollaborateur(null);
//            t.setCollaborateur(new Collaborateur());
//        }
//        if(t.getEtatReunion() == null  && dto.getEtatReunion() != null){
//            t.setEtatReunion(new EtatReunion());
//        }else if (t.getEtatReunion() != null  && dto.getEtatReunion() != null){
//            t.setEtatReunion(null);
//            t.setEtatReunion(new EtatReunion());
//        }
//        if (dto.getCollaborateur() != null)
//        collaborateurConverter.copy(dto.getCollaborateur(), t.getCollaborateur());
//        if (dto.getEtatReunion() != null)
//        etatReunionConverter.copy(dto.getEtatReunion(), t.getEtatReunion());
//    }
//
//    public List<Reunion> copy(List<ReunionDto> dtos) {
//        List<Reunion> result = new ArrayList<>();
//        if (dtos != null) {
//            for (ReunionDto dto : dtos) {
//                Reunion instance = new Reunion();
//                copy(dto, instance);
//                result.add(instance);
//            }
//        }
//        return result.isEmpty() ? null : result;
//    }
//
//
//    public EtatReunionConverter getEtatReunionConverter(){
//        return this.etatReunionConverter;
//    }
//    public void setEtatReunionConverter(EtatReunionConverter etatReunionConverter ){
//        this.etatReunionConverter = etatReunionConverter;
//    }
//    public CollaborateurConverter getCollaborateurConverter(){
//        return this.collaborateurConverter;
//    }
//    public void setCollaborateurConverter(CollaborateurConverter collaborateurConverter ){
//        this.collaborateurConverter = collaborateurConverter;
//    }
//    public boolean  isCollaborateur(){
//        return this.collaborateur;
//    }
//    public void  setCollaborateur(boolean collaborateur){
//        this.collaborateur = collaborateur;
//    }
//    public boolean  isEtatReunion(){
//        return this.etatReunion;
//    }
//    public void  setEtatReunion(boolean etatReunion){
//        this.etatReunion = etatReunion;
//    }
//}
