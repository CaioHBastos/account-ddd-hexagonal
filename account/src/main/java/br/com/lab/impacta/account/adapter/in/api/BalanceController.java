package br.com.lab.impacta.account.adapter.in.api;

import br.com.lab.impacta.account.application.AccountApplicationApi;
import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@Api(value = "Balance Account")
public class BalanceController {

    @Autowired
    private AccountApplicationApi accountApplication;

    @Autowired
    private Tracer tracer;

    @ApiOperation(value = "Get Ballance Account")
    @GetMapping(value = "/{accountId}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountBalanceResponse> balance(@PathVariable long accountId) {
        Span span = tracer.buildSpan("Iniciando a busca do saldo da conta do cliente").start();
        AccountBalanceResponse accountBalanceResponse = accountApplication.balance(accountId);

        span.setTag("http.status_code", 200);
        span.finish();
        return ResponseEntity.ok(accountBalanceResponse);
    }
}
