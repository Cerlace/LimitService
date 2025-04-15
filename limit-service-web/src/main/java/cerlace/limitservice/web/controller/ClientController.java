package cerlace.limitservice.web.controller;

import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Клиентский модуль",
        description = """
                API для управлением лимитами на расходные операции
                и получения транзакций, превысивших лимиты.""")
@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Operation(
            summary = "Установить расходный лимит",
            description = """
                    Обрабатывает запрос на установку нового лимита
                    по заданной категории, и сохраняет его в базу данных."""
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Лимит успешно установлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка входных данных"),
            @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера")
    })
    @PostMapping(value = "/set_limit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpendLimitResponse> setSpendLimit(@RequestBody SpendLimitCreateRequest request) {
        SpendLimitResponse createdLimit = clientService.setSpendLimit(request);
        return ResponseEntity.ok(createdLimit);
    }

    @Operation(
            summary = "Получить все лимиты",
            description = "Получает все существующие расходные лимиты"
    )
    @GetMapping(value = "/get_limits", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpendLimitResponse> getAllSpendLimits() {
        return clientService.getAllSpendLimits();
    }

    @Operation(
            summary = "Получить все лимитные транзакции",
            description = "Получает все транзакции, которые превысили установленный расходный лимит"
    )
    @GetMapping(value = "/get_transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionResponse> getLimitExceededTransactions() {
        return clientService.getLimitExceededTransactions();
    }
}
