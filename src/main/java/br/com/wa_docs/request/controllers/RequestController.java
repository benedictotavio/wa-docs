package br.com.wa_docs.request.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa_docs.request.domains.Request;
import br.com.wa_docs.request.dtos.CreateRequestDto;
import br.com.wa_docs.request.dtos.ResponseRequestDefaultDto;
import br.com.wa_docs.request.dtos.ResponseRequestDto;
import br.com.wa_docs.request.mappers.RequestMapper;
import br.com.wa_docs.request.services.IRequestService;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

    private final IRequestService requestService;
    private final RequestMapper requestMapper;

    public RequestController(IRequestService requestService, RequestMapper requestMappers) {
        this.requestService = requestService;
        this.requestMapper = requestMappers;
    }

    @GetMapping
    public ResponseEntity<List<ResponseRequestDto>> getByFolderId(
            @RequestParam(name = "folderId", required = false) Long id) {
        try {
            List<Request> requests = this.requestService.findByFolderId(id);
            return ResponseEntity.ok(requests.stream().map(requestMapper::toResponseRequestDto).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseRequestDefaultDto> create(@RequestBody CreateRequestDto request) {
        try {
            Long id = this.requestService.save(
                    this.requestMapper.toRequest(request));
            return ResponseEntity.ok(
                    new ResponseRequestDefaultDto(id, "Created request successfully", "OK"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseRequestDefaultDto> delete(@PathVariable Long id) {
        try {
            this.requestService.delete(id);
            return ResponseEntity.ok(
                    new ResponseRequestDefaultDto("Delete request successfully", "OK"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/bulk")
    public ResponseEntity<ResponseRequestDefaultDto> deleteMany(@RequestBody Long[] ids) {
        try {
            this.requestService.deleteMany(ids);
            return ResponseEntity.ok(
                    new ResponseRequestDefaultDto("Delete request successfully", "OK"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseRequestDto> getByProject(@PathVariable Long id) {
        try {
            Request request = this.requestService.findById(id);
            return ResponseEntity.ok(
                    this.requestMapper.toResponseRequestDto(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseRequestDefaultDto> update(@PathVariable Long id,
            @RequestBody CreateRequestDto request) {
        try {
            this.requestService.update(this.requestMapper.toRequest(request));
            return ResponseEntity.ok(
                    new ResponseRequestDefaultDto("Update request successfully", "OK"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
