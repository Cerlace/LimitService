package cerlace.limitservice.web.controller;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.core.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Модуль транзакций",
        description = "API для обработки входящих транзакций")
@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(
            summary = "Сохранить транзакцию",
            description = """
                    Обрабатывает запрос со входящей транзакцией:
                    проверяет транзакцию на превышение существующего лимита по категории,
                    и сохраняет ее в базу данных."""
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Транзакция успешно сохранена"),
            @ApiResponse(responseCode = "400", description = "Ошибка входных данных"),
            @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера")
    })
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> saveTransaction(@RequestBody TransactionCreateRequest request) {
        TransactionResponse savedTransaction = transactionService.saveTransaction(request);
        return ResponseEntity.ok(savedTransaction);
    }
}
