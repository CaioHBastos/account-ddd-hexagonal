package br.com.lab.impacta.account.adapter.in.api;

import br.com.lab.impacta.account.application.AccountApplicationApi;
import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest;
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class DebitController {

    @Autowired
    private AccountApplicationApi accountApplication;

    @Autowired
    private Tracer tracer;

    @PostMapping("/{accountId}/debit")
    public ResponseEntity<DebitAccountResponse> debit(
            @PathVariable long accountId,
            @RequestBody DebitAccountRequest debitAccountRequest) {

        Span span = tracer.buildSpan("Iniciando o debito do valor da conta do cliente").start();
        DebitAccountResponse debitAccountResponse =
                accountApplication.debit(accountId, debitAccountRequest);

        span.setTag("http.status_code", 200);
        span.finish();
        return ResponseEntity.ok(debitAccountResponse);
    }
}
