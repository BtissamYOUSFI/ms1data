package  ma.zyn.app.ws.facade.collaborateur.accompagnement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import ma.zyn.app.dao.criteria.core.accompagnement.EtatReunionCriteria;
import ma.zyn.app.service.facade.collaborateur.accompagnement.EtatReunionCollaborateurService;
import ma.zyn.app.ws.converter.accompagnement.EtatReunionConverter;
import ma.zyn.app.ws.dto.accompagnement.EtatReunionDto;
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
@RequestMapping("/api/collaborateur/etatReunion/")
public class EtatReunionRestCollaborateur {




    @Operation(summary = "Finds a list of all etatReunions")
    @GetMapping("")
    public ResponseEntity<List<EtatReunionDto>> findAll() throws Exception {
        ResponseEntity<List<EtatReunionDto>> res = null;
        List<EtatReunion> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatReunionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all etatReunions")
    @GetMapping("optimized")
    public ResponseEntity<List<EtatReunionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EtatReunionDto>> res = null;
        List<EtatReunion> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatReunionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a etatReunion by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatReunionDto> findById(@PathVariable Long id) {
        EtatReunion t = service.findById(id);
        if (t != null) {
            EtatReunionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a etatReunion by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EtatReunionDto> findByLibelle(@PathVariable String libelle) {
	    EtatReunion t = service.findByReferenceEntity(new EtatReunion(libelle));
        if (t != null) {
            EtatReunionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  etatReunion")
    @PostMapping("")
    public ResponseEntity<EtatReunionDto> save(@RequestBody EtatReunionDto dto) throws Exception {
        if(dto!=null){
            EtatReunion myT = converter.toItem(dto);
            EtatReunion t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EtatReunionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  etatReunion")
    @PutMapping("")
    public ResponseEntity<EtatReunionDto> update(@RequestBody EtatReunionDto dto) throws Exception {
        ResponseEntity<EtatReunionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatReunion t = service.findById(dto.getId());
            converter.copy(dto,t);
            EtatReunion updated = service.update(t);
            EtatReunionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatReunion")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatReunionDto>> delete(@RequestBody List<EtatReunionDto> dtos) throws Exception {
        ResponseEntity<List<EtatReunionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EtatReunion> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatReunion")
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


    @Operation(summary = "Finds a etatReunion and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatReunionDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatReunion loaded =  service.findWithAssociatedLists(id);
        EtatReunionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds etatReunions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EtatReunionDto>> findByCriteria(@RequestBody EtatReunionCriteria criteria) throws Exception {
        ResponseEntity<List<EtatReunionDto>> res = null;
        List<EtatReunion> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatReunionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated etatReunions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EtatReunionCriteria criteria) throws Exception {
        List<EtatReunion> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EtatReunionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets etatReunion data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EtatReunionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EtatReunionDto> findDtos(List<EtatReunion> list){
        List<EtatReunionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatReunionDto> getDtoResponseEntity(EtatReunionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EtatReunionRestCollaborateur(EtatReunionCollaborateurService service, EtatReunionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EtatReunionCollaborateurService service;
    private final EtatReunionConverter converter;





}
