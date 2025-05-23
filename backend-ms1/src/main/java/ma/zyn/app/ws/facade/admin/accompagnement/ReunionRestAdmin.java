package  ma.zyn.app.ws.facade.admin.accompagnement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ma.zyn.app.service.impl.admin.accompagnement.EmailService;
import ma.zyn.app.ws.dto.accompagnement.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.accompagnement.Reunion;
import ma.zyn.app.dao.criteria.core.accompagnement.ReunionCriteria;
import ma.zyn.app.service.facade.admin.accompagnement.ReunionAdminService;
import ma.zyn.app.ws.converter.accompagnement.ReunionConverter;
import ma.zyn.app.ws.dto.accompagnement.ReunionDto;
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
@RequestMapping("/api/admin/reunion/")
public class ReunionRestAdmin {

    @PostMapping("send-email/")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto email) {
        emailService.sendSimpleMessage(email.getTo(), email.getSubject(), email.getMessage());
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Finds a list of all reunions")
    @GetMapping("")
    public ResponseEntity<List<ReunionDto>> findAll() throws Exception {
        ResponseEntity<List<ReunionDto>> res = null;
        List<Reunion> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ReunionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all reunions")
    @GetMapping("optimized")
    public ResponseEntity<List<ReunionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ReunionDto>> res = null;
        List<Reunion> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ReunionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a reunion by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ReunionDto> findById(@PathVariable Long id) {
        Reunion t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ReunionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a reunion by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ReunionDto> findByLibelle(@PathVariable String libelle) {
	    Reunion t = service.findByReferenceEntity(new Reunion(libelle));
        if (t != null) {
            converter.init(true);
            ReunionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  reunion")
    @PostMapping("")
    public ResponseEntity<ReunionDto> save(@RequestBody ReunionDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Reunion myT = converter.toItem(dto);
            Reunion t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ReunionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  reunion")
    @PutMapping("")
    public ResponseEntity<ReunionDto> update(@RequestBody ReunionDto dto) throws Exception {
        ResponseEntity<ReunionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Reunion t = service.findById(dto.getId());
            converter.copy(dto,t);
            Reunion updated = service.update(t);
            ReunionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of reunion")
    @PostMapping("multiple")
    public ResponseEntity<List<ReunionDto>> delete(@RequestBody List<ReunionDto> dtos) throws Exception {
        ResponseEntity<List<ReunionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Reunion> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified reunion")
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

    @Operation(summary = "find by etatReunion code")
    @GetMapping("etatReunion/code/{code}")
    public List<ReunionDto> findByEtatReunionCode(@PathVariable String code){
        return findDtos(service.findByEtatReunionCode(code));
    }
    @Operation(summary = "delete by etatReunion code")
    @DeleteMapping("etatReunion/code/{code}")
    public int deleteByEtatReunionCode(@PathVariable String code){
        return service.deleteByEtatReunionCode(code);
    }

    @Operation(summary = "Finds a reunion and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ReunionDto> findWithAssociatedLists(@PathVariable Long id) {
        Reunion loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ReunionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds reunions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ReunionDto>> findByCriteria(@RequestBody ReunionCriteria criteria) throws Exception {
        ResponseEntity<List<ReunionDto>> res = null;
        List<Reunion> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ReunionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated reunions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ReunionCriteria criteria) throws Exception {
        List<Reunion> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ReunionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets reunion data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ReunionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ReunionDto> findDtos(List<Reunion> list){
        converter.initObject(true);
        List<ReunionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ReunionDto> getDtoResponseEntity(ReunionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ReunionRestAdmin(ReunionAdminService service, ReunionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ReunionAdminService service;
    private final ReunionConverter converter;

    @Autowired
    @Qualifier("customEmailService")
    private  EmailService emailService;



}
