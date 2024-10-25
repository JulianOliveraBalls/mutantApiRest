package org.demo.parcialBackend.controllers;
import org.demo.parcialBackend.dto.DnaRequest;
import org.demo.parcialBackend.dto.DnaResponse;
import org.demo.parcialBackend.services.DnaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/mutant")
public class DnaController {

    private final DnaService dnaService;

    public DnaController(DnaService dnaService) {
        this.dnaService = dnaService;
    }

    @PostMapping
    public ResponseEntity<DnaResponse> checkMutant(@RequestBody DnaRequest dnaRequest) {


        boolean esMutante = dnaService.analizarAdn(dnaRequest.getDna());
        DnaResponse dnaResponse = new DnaResponse(esMutante);
        if (esMutante) {
            return ResponseEntity.ok(dnaResponse);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);
        }
        }
    }


