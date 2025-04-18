package  ma.zyn.app.ws.converter.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.transaction.MoyenPaiementConverter;
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.ws.converter.transaction.StatusPaiementConverter;
import ma.zyn.app.bean.core.transaction.StatusPaiement;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.ws.dto.transaction.PaiementDto;

@Component
public class PaiementConverter {

    @Autowired
    private MoyenPaiementConverter moyenPaiementConverter ;
    @Autowired
    private StatusPaiementConverter statusPaiementConverter ;
    private boolean moyenPaiement;
    private boolean statusPaiement;

    public  PaiementConverter() {
        initObject(true);
    }

    public Paiement toItem(PaiementDto dto) {
        if (dto == null) {
            return null;
        } else {
        Paiement item = new Paiement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getMontant()))
                item.setMontant(dto.getMontant());
            if(StringUtil.isNotEmpty(dto.getDatePaiement()))
                item.setDatePaiement(DateUtil.stringEnToDate(dto.getDatePaiement()));
            if(this.moyenPaiement && dto.getMoyenPaiement()!=null)
                item.setMoyenPaiement(moyenPaiementConverter.toItem(dto.getMoyenPaiement())) ;

            if(this.statusPaiement && dto.getStatusPaiement()!=null)
                item.setStatusPaiement(statusPaiementConverter.toItem(dto.getStatusPaiement())) ;




        return item;
        }
    }


    public PaiementDto toDto(Paiement item) {
        if (item == null) {
            return null;
        } else {
            PaiementDto dto = new PaiementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getMontant()))
                dto.setMontant(item.getMontant());
            if(item.getDatePaiement()!=null)
                dto.setDatePaiement(DateUtil.dateTimeToString(item.getDatePaiement()));
            if(this.moyenPaiement && item.getMoyenPaiement()!=null) {
                dto.setMoyenPaiement(moyenPaiementConverter.toDto(item.getMoyenPaiement())) ;

            }
            if(this.statusPaiement && item.getStatusPaiement()!=null) {
                dto.setStatusPaiement(statusPaiementConverter.toDto(item.getStatusPaiement())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.moyenPaiement = value;
        this.statusPaiement = value;
    }
	
    public List<Paiement> toItem(List<PaiementDto> dtos) {
        List<Paiement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaiementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaiementDto> toDto(List<Paiement> items) {
        List<PaiementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Paiement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaiementDto dto, Paiement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getMoyenPaiement() == null  && dto.getMoyenPaiement() != null){
            t.setMoyenPaiement(new MoyenPaiement());
        }else if (t.getMoyenPaiement() != null  && dto.getMoyenPaiement() != null){
            t.setMoyenPaiement(null);
            t.setMoyenPaiement(new MoyenPaiement());
        }
        if(t.getStatusPaiement() == null  && dto.getStatusPaiement() != null){
            t.setStatusPaiement(new StatusPaiement());
        }else if (t.getStatusPaiement() != null  && dto.getStatusPaiement() != null){
            t.setStatusPaiement(null);
            t.setStatusPaiement(new StatusPaiement());
        }
        if (dto.getMoyenPaiement() != null)
        moyenPaiementConverter.copy(dto.getMoyenPaiement(), t.getMoyenPaiement());
        if (dto.getStatusPaiement() != null)
        statusPaiementConverter.copy(dto.getStatusPaiement(), t.getStatusPaiement());
    }

    public List<Paiement> copy(List<PaiementDto> dtos) {
        List<Paiement> result = new ArrayList<>();
        if (dtos != null) {
            for (PaiementDto dto : dtos) {
                Paiement instance = new Paiement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public MoyenPaiementConverter getMoyenPaiementConverter(){
        return this.moyenPaiementConverter;
    }
    public void setMoyenPaiementConverter(MoyenPaiementConverter moyenPaiementConverter ){
        this.moyenPaiementConverter = moyenPaiementConverter;
    }
    public StatusPaiementConverter getStatusPaiementConverter(){
        return this.statusPaiementConverter;
    }
    public void setStatusPaiementConverter(StatusPaiementConverter statusPaiementConverter ){
        this.statusPaiementConverter = statusPaiementConverter;
    }
    public boolean  isMoyenPaiement(){
        return this.moyenPaiement;
    }
    public void  setMoyenPaiement(boolean moyenPaiement){
        this.moyenPaiement = moyenPaiement;
    }
    public boolean  isStatusPaiement(){
        return this.statusPaiement;
    }
    public void  setStatusPaiement(boolean statusPaiement){
        this.statusPaiement = statusPaiement;
    }
}
