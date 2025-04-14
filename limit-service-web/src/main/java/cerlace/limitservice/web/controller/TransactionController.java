package cerlace.limitservice.web.controller;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(value ="/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> saveTransaction(@RequestBody TransactionCreateRequest request) {
        TransactionResponse savedTransaction = transactionService.saveTransaction(request);
        return ResponseEntity.ok(savedTransaction);
    }
}
