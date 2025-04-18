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

import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.dao.criteria.core.profil.EtatInscriptionCriteria;
import ma.zyn.app.service.facade.collaborateur.profil.EtatInscriptionCollaborateurService;
import ma.zyn.app.ws.converter.profil.EtatInscriptionConverter;
import ma.zyn.app.ws.dto.profil.EtatInscriptionDto;
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
@RequestMapping("/api/collaborateur/etatInscription/")
public class EtatInscriptionRestCollaborateur {




    @Operation(summary = "Finds a list of all etatInscriptions")
    @GetMapping("")
    public ResponseEntity<List<EtatInscriptionDto>> findAll() throws Exception {
        ResponseEntity<List<EtatInscriptionDto>> res = null;
        List<EtatInscription> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatInscriptionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all etatInscriptions")
    @GetMapping("optimized")
    public ResponseEntity<List<EtatInscriptionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EtatInscriptionDto>> res = null;
        List<EtatInscription> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatInscriptionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a etatInscription by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatInscriptionDto> findById(@PathVariable Long id) {
        EtatInscription t = service.findById(id);
        if (t != null) {
            EtatInscriptionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a etatInscription by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EtatInscriptionDto> findByLibelle(@PathVariable String libelle) {
	    EtatInscription t = service.findByReferenceEntity(new EtatInscription(libelle));
        if (t != null) {
            EtatInscriptionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  etatInscription")
    @PostMapping("")
    public ResponseEntity<EtatInscriptionDto> save(@RequestBody EtatInscriptionDto dto) throws Exception {
        if(dto!=null){
            EtatInscription myT = converter.toItem(dto);
            EtatInscription t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EtatInscriptionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  etatInscription")
    @PutMapping("")
    public ResponseEntity<EtatInscriptionDto> update(@RequestBody EtatInscriptionDto dto) throws Exception {
        ResponseEntity<EtatInscriptionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatInscription t = service.findById(dto.getId());
            converter.copy(dto,t);
            EtatInscription updated = service.update(t);
            EtatInscriptionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatInscription")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatInscriptionDto>> delete(@RequestBody List<EtatInscriptionDto> dtos) throws Exception {
        ResponseEntity<List<EtatInscriptionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EtatInscription> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatInscription")
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


    @Operation(summary = "Finds a etatInscription and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatInscriptionDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatInscription loaded =  service.findWithAssociatedLists(id);
        EtatInscriptionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds etatInscriptions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EtatInscriptionDto>> findByCriteria(@RequestBody EtatInscriptionCriteria criteria) throws Exception {
        ResponseEntity<List<EtatInscriptionDto>> res = null;
        List<EtatInscription> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatInscriptionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated etatInscriptions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EtatInscriptionCriteria criteria) throws Exception {
        List<EtatInscription> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EtatInscriptionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets etatInscription data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EtatInscriptionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EtatInscriptionDto> findDtos(List<EtatInscription> list){
        List<EtatInscriptionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatInscriptionDto> getDtoResponseEntity(EtatInscriptionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EtatInscriptionRestCollaborateur(EtatInscriptionCollaborateurService service, EtatInscriptionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EtatInscriptionCollaborateurService service;
    private final EtatInscriptionConverter converter;





}
