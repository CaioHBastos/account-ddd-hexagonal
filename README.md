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

### ADAPTER INT
Os adaptadores de entrada é onde estão os plugins que irá se conectar com a controller por exemplo na aplicação de ACCOUNT,
(parte que é adaptador de entrada da aplicação)