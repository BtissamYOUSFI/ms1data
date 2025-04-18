package  ma.zyn.app.ws.dto.profil;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReferentielMetierDto  extends AuditBaseDto {

    private String libelle  ;
    private String code  ;
    private String description  ;
    private Integer nombreHeuresExperienceMin  = 0 ;
    private Boolean scelleRouge  ;

    private MetierDto metier ;
    private LangueDto langue ;
    private NiveauLangueDto niveauLangue ;



    public ReferentielMetierDto(){
        super();
    }




    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public Integer getNombreHeuresExperienceMin(){
        return this.nombreHeuresExperienceMin;
    }
    public void setNombreHeuresExperienceMin(Integer nombreHeuresExperienceMin){
        this.nombreHeuresExperienceMin = nombreHeuresExperienceMin;
    }


    public Boolean getScelleRouge(){
        return this.scelleRouge;
    }
    public void setScelleRouge(Boolean scelleRouge){
        this.scelleRouge = scelleRouge;
    }


    public MetierDto getMetier(){
        return this.metier;
    }

    public void setMetier(MetierDto metier){
        this.metier = metier;
    }
    public LangueDto getLangue(){
        return this.langue;
    }

    public void setLangue(LangueDto langue){
        this.langue = langue;
    }
    public NiveauLangueDto getNiveauLangue(){
        return this.niveauLangue;
    }

    public void setNiveauLangue(NiveauLangueDto niveauLangue){
        this.niveauLangue = niveauLangue;
    }






}
