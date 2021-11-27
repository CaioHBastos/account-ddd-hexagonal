#ACCOUNT-DDD-HEXAGONAL

Aplicação com foco em estudo com o DDD (Domain Driven Design) e arquitetura hexagonal.

## CORE
A camada de core é o núcleo da aplicação, onde está localizado as regras de negócio com relação a conta.

### PORT
Dentro do CORE estão localizados as portas de entrada e saída. As portas de entrada é onde a aplicação
entra dentro da camada de negócio e a camada de saída é a forma que núcleo se comunica com a camada de dados.

## APPLICATION
A camada de aplicação é onde ocorre as regras e os mapeamentos de dados quando se quer transformar um objeto de dominio
para um objeto de apresentação.

## ADAPTER
Onde pode se conectar diversas pontas, como as controllers e as de persistencia.

### ADAPTER OUT
Os adaptadores de saída é onde estão os plugins que irá se conectar com o banco de dados na aplicação de ACCOUNT,
(parte que é adaptador de saída da aplicação)

### ADAPTER IN
Os adaptadores de entrada é onde estão os plugins que irá se conectar com a conoller por exemplo na aplicação de ACCOUNT,
(parte que é adaptador de entrada da aplicação)

## JAEGER
Quando se trata de comunicação de microserviços, é importante ter uma forta de rastreiar a comunição dos serviços, uma vez que pode
ser complexo o debug. Com isso implementamos o Jaeger para a monitoria dos tracing.
Para executar basta seguir o passo a passo abaixo:

1. Imagem docker:
```bash
docker run -d --name jaeger -p 16686:16686 -p 6831:6831/udp jaegertracing/all-in-one:1.22
```

2. Abrir no seu Browser
```bash
http://localhost:16686/search
```

2. Executar a aplicação
```bash
localhost:8081/api/v1/accounts/1/balance - GET
localhost:8081/api/v1/accounts/1/debit - POST

Realizar o reload no Jaeger e com isso irá ser identificado a aplicação 
```