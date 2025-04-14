package cerlace.limitservice.web.controller;

import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping(value ="/set_limit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpendLimitResponse> setSpendLimit(@RequestBody SpendLimitCreateRequest request) {
        SpendLimitResponse createdLimit = clientService.setSpendLimit(request);
        return ResponseEntity.ok(createdLimit);
    }

    @GetMapping(value = "/get_limits", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpendLimitResponse> getAllSpendLimits() {
        return clientService.getAllSpendLimits();
    }

    @GetMapping(value = "/get_transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionResponse> getLimitExceededTransactions() {
        return clientService.getLimitExceededTransactions();
    }
}
