package ma.zyn.app.ws.converter.transaction;

import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.bean.core.transaction.PaiementManager;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.bean.core.utilisateurs.Manager;
import ma.zyn.app.ws.converter.utilisateurs.ManagerConverter;
import ma.zyn.app.ws.dto.transaction.PaiementManagerDto;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaiementManagerConverter {

    @Autowired
    private MoyenPaiementConverter moyenPaiementConverter ;
    @Autowired
    private StatusPaiementConverter statusPaiementConverter ;
    @Autowired
    private ManagerConverter managerConverter ;
    private boolean moyenPaiement;
    private boolean statusPaiement;
    private boolean manager;

    public PaiementManagerConverter() {
        initObject(true);
    }

    public PaiementManager toItem(PaiementManagerDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaiementManager item = new PaiementManager();
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

            if(this.manager && dto.getManager()!=null)
                item.setManager(managerConverter.toItem(dto.getManager())) ;




        return item;
        }
    }


    public PaiementManagerDto toDto(PaiementManager item) {
        if (item == null) {
            return null;
        } else {
            PaiementManagerDto dto = new PaiementManagerDto();
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
        this.moyenPaiement = value;
        this.statusPaiement = value;
        this.manager = value;
    }
	
    public List<PaiementManager> toItem(List<PaiementManagerDto> dtos) {
        List<PaiementManager> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaiementManagerDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaiementManagerDto> toDto(List<PaiementManager> items) {
        List<PaiementManagerDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaiementManager item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaiementManagerDto dto, PaiementManager t) {
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
        if(t.getManager() == null  && dto.getManager() != null){
            t.setManager(new Manager());
        }else if (t.getManager() != null  && dto.getManager() != null){
            t.setManager(null);
            t.setManager(new Manager());
        }
        if (dto.getMoyenPaiement() != null)
        moyenPaiementConverter.copy(dto.getMoyenPaiement(), t.getMoyenPaiement());
        if (dto.getStatusPaiement() != null)
        statusPaiementConverter.copy(dto.getStatusPaiement(), t.getStatusPaiement());
        if (dto.getManager() != null)
        managerConverter.copy(dto.getManager(), t.getManager());
    }

    public List<PaiementManager> copy(List<PaiementManagerDto> dtos) {
        List<PaiementManager> result = new ArrayList<>();
        if (dtos != null) {
            for (PaiementManagerDto dto : dtos) {
                PaiementManager instance = new PaiementManager();
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
    public ManagerConverter getManagerConverter(){
        return this.managerConverter;
    }
    public void setManagerConverter(ManagerConverter managerConverter ){
        this.managerConverter = managerConverter;
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
    public boolean  isManager(){
        return this.manager;
    }
    public void  setManager(boolean manager){
        this.manager = manager;
    }
}
