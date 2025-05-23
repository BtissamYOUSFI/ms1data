package  ma.zyn.app.ws.dto.profil;
import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NiveauLangueDto  extends AuditBaseDto {

    private String libelle  ;
    private String code  ;
    private String style  ;
    private String description  ;
    private Integer valeur;

    public NiveauLangueDto(){
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

    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Integer getValeur() {return valeur;}
    public void setValeur(Integer valeur) {this.valeur = valeur;}
}
