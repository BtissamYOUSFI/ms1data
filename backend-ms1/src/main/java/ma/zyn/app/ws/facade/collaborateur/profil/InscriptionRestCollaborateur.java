package  ma.zyn.app.ws.facade.collaborateur.profil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.dao.criteria.core.profil.InscriptionCriteria;
import ma.zyn.app.service.facade.collaborateur.profil.InscriptionCollaborateurService;
import ma.zyn.app.ws.converter.profil.InscriptionConverter;
import ma.zyn.app.ws.dto.profil.InscriptionDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/collaborateur/inscription/")
public class InscriptionRestCollaborateur {

    @Operation(summary = "Finds a list of all inscriptions")
    @GetMapping("")
    public ResponseEntity<List<InscriptionDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionDto>> res = null;
        List<Inscription> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<InscriptionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a inscription by email")
    @GetMapping("email/{email}")
    public ResponseEntity<InscriptionDto> findByEmail(@PathVariable String email){
        Inscription inscription = service.findByEmail(email);
        if (inscription != null) {
            converter.initObject(true);
            InscriptionDto dto = converter.toDto(inscription);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @Operation(summary = "Finds a inscription by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionDto> findById(@PathVariable Long id) {
        Inscription t = service.findById(id);
        if (t != null) {
            converter.init(true);
            InscriptionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }



    @Operation(summary = "Saves the specified  inscription")
    @PostMapping("")
    public ResponseEntity<InscriptionDto> save(@RequestBody InscriptionDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Inscription myT = converter.toItem(dto);
            Inscription t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscription")
    @PutMapping("")
    public ResponseEntity<InscriptionDto> update(@RequestBody InscriptionDto dto) throws Exception {
        ResponseEntity<InscriptionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Inscription t = service.findById(dto.getId());
            converter.copy(dto,t);
            Inscription updated = service.update(t);
            InscriptionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscription")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionDto>> delete(@RequestBody List<InscriptionDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Inscription> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscription")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by langue code")
    @GetMapping("langue/code/{code}")
    public List<InscriptionDto> findByLangueCode(@PathVariable String code){
        return findDtos(service.findByLangueCode(code));
    }
    @Operation(summary = "delete by langue code")
    @DeleteMapping("langue/code/{code}")
    public int deleteByLangueCode(@PathVariable String code){
        return service.deleteByLangueCode(code);
    }
    @Operation(summary = "find by etatInscription code")
    @GetMapping("etatInscription/code/{code}")
    public List<InscriptionDto> findByEtatInscriptionCode(@PathVariable String code){
        return findDtos(service.findByEtatInscriptionCode(code));
    }
    @Operation(summary = "delete by etatInscription code")
    @DeleteMapping("etatInscription/code/{code}")
    public int deleteByEtatInscriptionCode(@PathVariable String code){
        return service.deleteByEtatInscriptionCode(code);
    }

    @Operation(summary = "Finds a inscription and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionDto> findWithAssociatedLists(@PathVariable Long id) {
        Inscription loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        InscriptionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionDto>> findByCriteria(@RequestBody InscriptionCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionDto>> res = null;
        List<Inscription> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InscriptionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionCriteria criteria) throws Exception {
        List<Inscription> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<InscriptionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscription data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionDto> findDtos(List<Inscription> list){
        converter.initObject(true);
        List<InscriptionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionDto> getDtoResponseEntity(InscriptionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public InscriptionRestCollaborateur(InscriptionCollaborateurService service, InscriptionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final InscriptionCollaborateurService service;
    private final InscriptionConverter converter;





}
