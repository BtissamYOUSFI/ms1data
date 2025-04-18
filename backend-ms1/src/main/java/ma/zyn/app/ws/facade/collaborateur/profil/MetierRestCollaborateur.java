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

import ma.zyn.app.bean.core.profil.Metier;
import ma.zyn.app.dao.criteria.core.profil.MetierCriteria;
import ma.zyn.app.service.facade.collaborateur.profil.MetierCollaborateurService;
import ma.zyn.app.ws.converter.profil.MetierConverter;
import ma.zyn.app.ws.dto.profil.MetierDto;
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
@RequestMapping("/api/collaborateur/metier/")
public class MetierRestCollaborateur {




    @Operation(summary = "Finds a list of all metiers")
    @GetMapping("")
    public ResponseEntity<List<MetierDto>> findAll() throws Exception {
        ResponseEntity<List<MetierDto>> res = null;
        List<Metier> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MetierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all metiers")
    @GetMapping("optimized")
    public ResponseEntity<List<MetierDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MetierDto>> res = null;
        List<Metier> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MetierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a metier by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MetierDto> findById(@PathVariable Long id) {
        Metier t = service.findById(id);
        if (t != null) {
            MetierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a metier by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<MetierDto> findByLibelle(@PathVariable String libelle) {
	    Metier t = service.findByReferenceEntity(new Metier(libelle));
        if (t != null) {
            MetierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  metier")
    @PostMapping("")
    public ResponseEntity<MetierDto> save(@RequestBody MetierDto dto) throws Exception {
        if(dto!=null){
            Metier myT = converter.toItem(dto);
            Metier t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MetierDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  metier")
    @PutMapping("")
    public ResponseEntity<MetierDto> update(@RequestBody MetierDto dto) throws Exception {
        ResponseEntity<MetierDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Metier t = service.findById(dto.getId());
            converter.copy(dto,t);
            Metier updated = service.update(t);
            MetierDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of metier")
    @PostMapping("multiple")
    public ResponseEntity<List<MetierDto>> delete(@RequestBody List<MetierDto> dtos) throws Exception {
        ResponseEntity<List<MetierDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Metier> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified metier")
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


    @Operation(summary = "Finds a metier and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MetierDto> findWithAssociatedLists(@PathVariable Long id) {
        Metier loaded =  service.findWithAssociatedLists(id);
        MetierDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds metiers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MetierDto>> findByCriteria(@RequestBody MetierCriteria criteria) throws Exception {
        ResponseEntity<List<MetierDto>> res = null;
        List<Metier> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MetierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated metiers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MetierCriteria criteria) throws Exception {
        List<Metier> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<MetierDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets metier data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MetierCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MetierDto> findDtos(List<Metier> list){
        List<MetierDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MetierDto> getDtoResponseEntity(MetierDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public MetierRestCollaborateur(MetierCollaborateurService service, MetierConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final MetierCollaborateurService service;
    private final MetierConverter converter;





}
