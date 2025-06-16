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

import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.dao.criteria.core.transaction.PaiementCriteria;
import ma.zyn.app.service.facade.admin.transaction.PaiementAdminService;
import ma.zyn.app.ws.converter.transaction.PaiementConverter;
import ma.zyn.app.ws.dto.transaction.PaiementDto;
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
@RequestMapping("/api/admin/paiement/")
public class PaiementRestAdmin {




    @Operation(summary = "Finds a list of all paiements")
    @GetMapping("")
    public ResponseEntity<List<PaiementDto>> findAll() throws Exception {
        ResponseEntity<List<PaiementDto>> res = null;
        List<Paiement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paiements")
    @GetMapping("optimized")
    public ResponseEntity<List<PaiementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaiementDto>> res = null;
        List<Paiement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paiement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaiementDto> findById(@PathVariable Long id) {
        Paiement t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PaiementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paiement by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PaiementDto> findByLibelle(@PathVariable String libelle) {
        Paiement t = service.findByReferenceEntity(new Paiement(libelle));
        if (t != null) {
            converter.init(true);
            PaiementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paiement")
    @PostMapping("")
    public ResponseEntity<PaiementDto> save(@RequestBody PaiementDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Paiement myT = converter.toItem(dto);
            Paiement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaiementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paiement")
    @PutMapping("")
    public ResponseEntity<PaiementDto> update(@RequestBody PaiementDto dto) throws Exception {
        ResponseEntity<PaiementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Paiement t = service.findById(dto.getId());
            converter.copy(dto,t);
            Paiement updated = service.update(t);
            PaiementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paiement")
    @PostMapping("multiple")
    public ResponseEntity<List<PaiementDto>> delete(@RequestBody List<PaiementDto> dtos) throws Exception {
        ResponseEntity<List<PaiementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Paiement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paiement")
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

    @Operation(summary = "find by collaborateur id")
    @GetMapping("collaborateur/id/{id}")
    public List<PaiementDto> findByCollaborateurId(@PathVariable Long id){
        return findDtos(service.findByCollaborateurId(id));
    }
    @Operation(summary = "delete by collaborateur id")
    @DeleteMapping("collaborateur/id/{id}")
    public int deleteByCollaborateurId(@PathVariable Long id){
        return service.deleteByCollaborateurId(id);
    }

    @Operation(summary = "Finds a paiement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaiementDto> findWithAssociatedLists(@PathVariable Long id) {
        Paiement loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        PaiementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paiements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaiementDto>> findByCriteria(@RequestBody PaiementCriteria criteria) throws Exception {
        ResponseEntity<List<PaiementDto>> res = null;
        List<Paiement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaiementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paiements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaiementCriteria criteria) throws Exception {
        List<Paiement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PaiementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paiement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaiementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    public List<PaiementDto> findDtos(List<Paiement> list){
        converter.initObject(true);
        List<PaiementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaiementDto> getDtoResponseEntity(PaiementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    public PaiementRestAdmin(PaiementAdminService service, PaiementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final PaiementAdminService service;
    private final PaiementConverter converter;





}

//package  ma.zyn.app.ws.facade.admin.transaction;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.HttpStatus;
//import java.util.Arrays;
//import java.util.ArrayList;
//
//import ma.zyn.app.bean.core.transaction.Paiement;
//import ma.zyn.app.dao.criteria.core.transaction.PaiementCriteria;
//import ma.zyn.app.service.facade.admin.transaction.PaiementAdminService;
//import ma.zyn.app.ws.converter.transaction.PaiementConverter;
//import ma.zyn.app.ws.dto.transaction.PaiementDto;
//import ma.zyn.app.zynerator.controller.AbstractController;
//import ma.zyn.app.zynerator.dto.AuditEntityDto;
//import ma.zyn.app.zynerator.util.PaginatedList;
//
//
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import ma.zyn.app.zynerator.process.Result;
//
//
//import org.springframework.web.multipart.MultipartFile;
//import ma.zyn.app.zynerator.dto.FileTempDto;
//
//@RestController
//@RequestMapping("/api/admin/paiement/")
//public class PaiementRestAdmin {
//
//
//
//
//    @Operation(summary = "Finds a list of all paiements")
//    @GetMapping("")
//    public ResponseEntity<List<PaiementDto>> findAll() throws Exception {
//        ResponseEntity<List<PaiementDto>> res = null;
//        List<Paiement> list = service.findAll();
//        HttpStatus status = HttpStatus.NO_CONTENT;
//            converter.initObject(true);
//        List<PaiementDto> dtos  = converter.toDto(list);
//        if (dtos != null && !dtos.isEmpty())
//            status = HttpStatus.OK;
//        res = new ResponseEntity<>(dtos, status);
//        return res;
//    }
//
//    @Operation(summary = "Finds an optimized list of all paiements")
//    @GetMapping("optimized")
//    public ResponseEntity<List<PaiementDto>> findAllOptimized() throws Exception {
//        ResponseEntity<List<PaiementDto>> res = null;
//        List<Paiement> list = service.findAllOptimized();
//        HttpStatus status = HttpStatus.NO_CONTENT;
//        converter.initObject(true);
//        List<PaiementDto> dtos  = converter.toDto(list);
//        if (dtos != null && !dtos.isEmpty())
//            status = HttpStatus.OK;
//        res = new ResponseEntity<>(dtos, status);
//        return res;
//    }
//
//    @Operation(summary = "Finds a paiement by id")
//    @GetMapping("id/{id}")
//    public ResponseEntity<PaiementDto> findById(@PathVariable Long id) {
//        Paiement t = service.findById(id);
//        if (t != null) {
//            converter.init(true);
//            PaiementDto dto = converter.toDto(t);
//            return getDtoResponseEntity(dto);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//
//    @Operation(summary = "Finds a paiement by libelle")
//    @GetMapping("libelle/{libelle}")
//    public ResponseEntity<PaiementDto> findByLibelle(@PathVariable String libelle) {
//	    Paiement t = service.findByReferenceEntity(new Paiement(libelle));
//        if (t != null) {
//            converter.init(true);
//            PaiementDto dto = converter.toDto(t);
//            return getDtoResponseEntity(dto);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//
//    @Operation(summary = "Saves the specified  paiement")
//    @PostMapping("")
//    public ResponseEntity<PaiementDto> save(@RequestBody PaiementDto dto) throws Exception {
//        if(dto!=null){
//            converter.init(true);
//            Paiement myT = converter.toItem(dto);
//            Paiement t = service.create(myT);
//            if (t == null) {
//                return new ResponseEntity<>(null, HttpStatus.IM_USED);
//            }else{
//                PaiementDto myDto = converter.toDto(t);
//                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
//            }
//        }else {
//            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @Operation(summary = "Updates the specified  paiement")
//    @PutMapping("")
//    public ResponseEntity<PaiementDto> update(@RequestBody PaiementDto dto) throws Exception {
//        ResponseEntity<PaiementDto> res ;
//        if (dto.getId() == null || service.findById(dto.getId()) == null)
//            res = new ResponseEntity<>(HttpStatus.CONFLICT);
//        else {
//            Paiement t = service.findById(dto.getId());
//            converter.copy(dto,t);
//            Paiement updated = service.update(t);
//            PaiementDto myDto = converter.toDto(updated);
//            res = new ResponseEntity<>(myDto, HttpStatus.OK);
//        }
//        return res;
//    }
//
//    @Operation(summary = "Delete list of paiement")
//    @PostMapping("multiple")
//    public ResponseEntity<List<PaiementDto>> delete(@RequestBody List<PaiementDto> dtos) throws Exception {
//        ResponseEntity<List<PaiementDto>> res ;
//        HttpStatus status = HttpStatus.CONFLICT;
//        if (dtos != null && !dtos.isEmpty()) {
//            converter.init(false);
//            List<Paiement> ts = converter.toItem(dtos);
//            service.delete(ts);
//            status = HttpStatus.OK;
//        }
//        res = new ResponseEntity<>(dtos, status);
//        return res;
//    }
//
//    @Operation(summary = "Delete the specified paiement")
//    @DeleteMapping("id/{id}")
//    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
//        ResponseEntity<Long> res;
//        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
//        if (id != null) {
//            boolean resultDelete = service.deleteById(id);
//            if (resultDelete) {
//                status = HttpStatus.OK;
//            }
//        }
//        res = new ResponseEntity<>(id, status);
//        return res;
//    }
//
//    @Operation(summary = "find by statusPaiement code")
//    @GetMapping("statusPaiement/code/{code}")
//    public List<PaiementDto> findByStatusPaiementCode(@PathVariable String code){
//        return findDtos(service.findByStatusPaiementCode(code));
//    }
//    @Operation(summary = "delete by statusPaiement code")
//    @DeleteMapping("statusPaiement/code/{code}")
//    public int deleteByStatusPaiementCode(@PathVariable String code){
//        return service.deleteByStatusPaiementCode(code);
//    }
//
//    @Operation(summary = "Finds a paiement and associated list by id")
//    @GetMapping("detail/id/{id}")
//    public ResponseEntity<PaiementDto> findWithAssociatedLists(@PathVariable Long id) {
//        Paiement loaded =  service.findWithAssociatedLists(id);
//        converter.init(true);
//        PaiementDto dto = converter.toDto(loaded);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//    @Operation(summary = "Finds paiements by criteria")
//    @PostMapping("find-by-criteria")
//    public ResponseEntity<List<PaiementDto>> findByCriteria(@RequestBody PaiementCriteria criteria) throws Exception {
//        ResponseEntity<List<PaiementDto>> res = null;
//        List<Paiement> list = service.findByCriteria(criteria);
//        HttpStatus status = HttpStatus.NO_CONTENT;
//        converter.initObject(true);
//        List<PaiementDto> dtos  = converter.toDto(list);
//        if (dtos != null && !dtos.isEmpty())
//            status = HttpStatus.OK;
//
//        res = new ResponseEntity<>(dtos, status);
//        return res;
//    }
//
//    @Operation(summary = "Finds paginated paiements by criteria")
//    @PostMapping("find-paginated-by-criteria")
//    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaiementCriteria criteria) throws Exception {
//        List<Paiement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
//        converter.initObject(true);
//        List<PaiementDto> dtos = converter.toDto(list);
//        PaginatedList paginatedList = new PaginatedList();
//        paginatedList.setList(dtos);
//        if (dtos != null && !dtos.isEmpty()) {
//            int dateSize = service.getDataSize(criteria);
//            paginatedList.setDataSize(dateSize);
//        }
//        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
//    }
//
//    @Operation(summary = "Gets paiement data size by criteria")
//    @PostMapping("data-size-by-criteria")
//    public ResponseEntity<Integer> getDataSize(@RequestBody PaiementCriteria criteria) throws Exception {
//        int count = service.getDataSize(criteria);
//        return new ResponseEntity<Integer>(count, HttpStatus.OK);
//    }
//
//	public List<PaiementDto> findDtos(List<Paiement> list){
//        converter.initObject(true);
//        List<PaiementDto> dtos = converter.toDto(list);
//        return dtos;
//    }
//
//    private ResponseEntity<PaiementDto> getDtoResponseEntity(PaiementDto dto) {
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//
//
//
//   public PaiementRestAdmin(PaiementAdminService service, PaiementConverter converter){
//        this.service = service;
//        this.converter = converter;
//    }
//
//    private final PaiementAdminService service;
//    private final PaiementConverter converter;
//
//
//
//
//
//}
