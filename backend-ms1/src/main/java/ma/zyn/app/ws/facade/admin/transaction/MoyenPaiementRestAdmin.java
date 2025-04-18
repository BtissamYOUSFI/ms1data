package  ma.zyn.app.ws.facade.admin.transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.dao.criteria.core.transaction.MoyenPaiementCriteria;
import ma.zyn.app.service.facade.admin.transaction.MoyenPaiementAdminService;
import ma.zyn.app.ws.converter.transaction.MoyenPaiementConverter;
import ma.zyn.app.ws.dto.transaction.MoyenPaiementDto;
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
@RequestMapping("/api/admin/moyenPaiement/")
public class MoyenPaiementRestAdmin {




    @Operation(summary = "Finds a list of all moyenPaiements")
    @GetMapping("")
    public ResponseEntity<List<MoyenPaiementDto>> findAll() throws Exception {
        ResponseEntity<List<MoyenPaiementDto>> res = null;
        List<MoyenPaiement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MoyenPaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all moyenPaiements")
    @GetMapping("optimized")
    public ResponseEntity<List<MoyenPaiementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MoyenPaiementDto>> res = null;
        List<MoyenPaiement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MoyenPaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a moyenPaiement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MoyenPaiementDto> findById(@PathVariable Long id) {
        MoyenPaiement t = service.findById(id);
        if (t != null) {
            MoyenPaiementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a moyenPaiement by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<MoyenPaiementDto> findByLibelle(@PathVariable String libelle) {
	    MoyenPaiement t = service.findByReferenceEntity(new MoyenPaiement(libelle));
        if (t != null) {
            MoyenPaiementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  moyenPaiement")
    @PostMapping("")
    public ResponseEntity<MoyenPaiementDto> save(@RequestBody MoyenPaiementDto dto) throws Exception {
        if(dto!=null){
            MoyenPaiement myT = converter.toItem(dto);
            MoyenPaiement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MoyenPaiementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  moyenPaiement")
    @PutMapping("")
    public ResponseEntity<MoyenPaiementDto> update(@RequestBody MoyenPaiementDto dto) throws Exception {
        ResponseEntity<MoyenPaiementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            MoyenPaiement t = service.findById(dto.getId());
            converter.copy(dto,t);
            MoyenPaiement updated = service.update(t);
            MoyenPaiementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of moyenPaiement")
    @PostMapping("multiple")
    public ResponseEntity<List<MoyenPaiementDto>> delete(@RequestBody List<MoyenPaiementDto> dtos) throws Exception {
        ResponseEntity<List<MoyenPaiementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<MoyenPaiement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified moyenPaiement")
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


    @Operation(summary = "Finds a moyenPaiement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MoyenPaiementDto> findWithAssociatedLists(@PathVariable Long id) {
        MoyenPaiement loaded =  service.findWithAssociatedLists(id);
        MoyenPaiementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds moyenPaiements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MoyenPaiementDto>> findByCriteria(@RequestBody MoyenPaiementCriteria criteria) throws Exception {
        ResponseEntity<List<MoyenPaiementDto>> res = null;
        List<MoyenPaiement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MoyenPaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated moyenPaiements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MoyenPaiementCriteria criteria) throws Exception {
        List<MoyenPaiement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<MoyenPaiementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets moyenPaiement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MoyenPaiementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MoyenPaiementDto> findDtos(List<MoyenPaiement> list){
        List<MoyenPaiementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MoyenPaiementDto> getDtoResponseEntity(MoyenPaiementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public MoyenPaiementRestAdmin(MoyenPaiementAdminService service, MoyenPaiementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final MoyenPaiementAdminService service;
    private final MoyenPaiementConverter converter;





}
