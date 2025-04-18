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

import ma.zyn.app.bean.core.profil.Langue;
import ma.zyn.app.dao.criteria.core.profil.LangueCriteria;
import ma.zyn.app.service.facade.collaborateur.profil.LangueCollaborateurService;
import ma.zyn.app.ws.converter.profil.LangueConverter;
import ma.zyn.app.ws.dto.profil.LangueDto;
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
@RequestMapping("/api/collaborateur/langue/")
public class LangueRestCollaborateur {




    @Operation(summary = "Finds a list of all langues")
    @GetMapping("")
    public ResponseEntity<List<LangueDto>> findAll() throws Exception {
        ResponseEntity<List<LangueDto>> res = null;
        List<Langue> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LangueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all langues")
    @GetMapping("optimized")
    public ResponseEntity<List<LangueDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<LangueDto>> res = null;
        List<Langue> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LangueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a langue by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LangueDto> findById(@PathVariable Long id) {
        Langue t = service.findById(id);
        if (t != null) {
            LangueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a langue by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<LangueDto> findByLibelle(@PathVariable String libelle) {
	    Langue t = service.findByReferenceEntity(new Langue(libelle));
        if (t != null) {
            LangueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  langue")
    @PostMapping("")
    public ResponseEntity<LangueDto> save(@RequestBody LangueDto dto) throws Exception {
        if(dto!=null){
            Langue myT = converter.toItem(dto);
            Langue t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                LangueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  langue")
    @PutMapping("")
    public ResponseEntity<LangueDto> update(@RequestBody LangueDto dto) throws Exception {
        ResponseEntity<LangueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Langue t = service.findById(dto.getId());
            converter.copy(dto,t);
            Langue updated = service.update(t);
            LangueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of langue")
    @PostMapping("multiple")
    public ResponseEntity<List<LangueDto>> delete(@RequestBody List<LangueDto> dtos) throws Exception {
        ResponseEntity<List<LangueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Langue> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified langue")
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


    @Operation(summary = "Finds a langue and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LangueDto> findWithAssociatedLists(@PathVariable Long id) {
        Langue loaded =  service.findWithAssociatedLists(id);
        LangueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds langues by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<LangueDto>> findByCriteria(@RequestBody LangueCriteria criteria) throws Exception {
        ResponseEntity<List<LangueDto>> res = null;
        List<Langue> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LangueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated langues by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody LangueCriteria criteria) throws Exception {
        List<Langue> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<LangueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets langue data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody LangueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<LangueDto> findDtos(List<Langue> list){
        List<LangueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<LangueDto> getDtoResponseEntity(LangueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public LangueRestCollaborateur(LangueCollaborateurService service, LangueConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final LangueCollaborateurService service;
    private final LangueConverter converter;





}
