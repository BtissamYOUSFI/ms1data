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
import ma.zyn.app.ws.converter.profil.LangueConverter;
import ma.zyn.app.bean.core.profil.Langue;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.profil.ReferentielMetier;
import ma.zyn.app.ws.dto.profil.ReferentielMetierDto;

@Component
public class ReferentielMetierConverter {

    @Autowired
    private MetierConverter metierConverter ;
    @Autowired
    private NiveauLangueConverter niveauLangueConverter ;
    @Autowired
    private LangueConverter langueConverter ;
    private boolean metier;
    private boolean langue;
    private boolean niveauLangue;

    public  ReferentielMetierConverter() {
        initObject(true);
    }

    public ReferentielMetier toItem(ReferentielMetierDto dto) {
        if (dto == null) {
            return null;
        } else {
        ReferentielMetier item = new ReferentielMetier();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getNombreHeuresExperienceMin()))
                item.setNombreHeuresExperienceMin(dto.getNombreHeuresExperienceMin());
            if(dto.getScelleRouge() != null)
                item.setScelleRouge(dto.getScelleRouge());
            if(this.metier && dto.getMetier()!=null)
                item.setMetier(metierConverter.toItem(dto.getMetier())) ;

            if(this.langue && dto.getLangue()!=null)
                item.setLangue(langueConverter.toItem(dto.getLangue())) ;

            if(this.niveauLangue && dto.getNiveauLangue()!=null)
                item.setNiveauLangue(niveauLangueConverter.toItem(dto.getNiveauLangue())) ;




        return item;
        }
    }


    public ReferentielMetierDto toDto(ReferentielMetier item) {
        if (item == null) {
            return null;
        } else {
            ReferentielMetierDto dto = new ReferentielMetierDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getNombreHeuresExperienceMin()))
                dto.setNombreHeuresExperienceMin(item.getNombreHeuresExperienceMin());
                dto.setScelleRouge(item.getScelleRouge());
            if(this.metier && item.getMetier()!=null) {
                dto.setMetier(metierConverter.toDto(item.getMetier())) ;

            }
            if(this.langue && item.getLangue()!=null) {
                dto.setLangue(langueConverter.toDto(item.getLangue())) ;

            }
            if(this.niveauLangue && item.getNiveauLangue()!=null) {
                dto.setNiveauLangue(niveauLangueConverter.toDto(item.getNiveauLangue())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.metier = value;
        this.langue = value;
        this.niveauLangue = value;
    }
	
    public List<ReferentielMetier> toItem(List<ReferentielMetierDto> dtos) {
        List<ReferentielMetier> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ReferentielMetierDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ReferentielMetierDto> toDto(List<ReferentielMetier> items) {
        List<ReferentielMetierDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ReferentielMetier item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ReferentielMetierDto dto, ReferentielMetier t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getMetier() == null  && dto.getMetier() != null){
            t.setMetier(new Metier());
        }else if (t.getMetier() != null  && dto.getMetier() != null){
            t.setMetier(null);
            t.setMetier(new Metier());
        }
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
        if (dto.getMetier() != null)
        metierConverter.copy(dto.getMetier(), t.getMetier());
        if (dto.getLangue() != null)
        langueConverter.copy(dto.getLangue(), t.getLangue());
        if (dto.getNiveauLangue() != null)
        niveauLangueConverter.copy(dto.getNiveauLangue(), t.getNiveauLangue());
    }

    public List<ReferentielMetier> copy(List<ReferentielMetierDto> dtos) {
        List<ReferentielMetier> result = new ArrayList<>();
        if (dtos != null) {
            for (ReferentielMetierDto dto : dtos) {
                ReferentielMetier instance = new ReferentielMetier();
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
    public LangueConverter getLangueConverter(){
        return this.langueConverter;
    }
    public void setLangueConverter(LangueConverter langueConverter ){
        this.langueConverter = langueConverter;
    }
    public boolean  isMetier(){
        return this.metier;
    }
    public void  setMetier(boolean metier){
        this.metier = metier;
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
}
