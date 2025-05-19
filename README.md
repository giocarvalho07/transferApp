# TransferApp 📲

Sistema para **agendamento de transferências financeiras** entre contas bancárias com **cálculo automático de taxas** baseado na data de execução da transferência.

Este projeto consiste em um sistema para agendamento de transferências financeiras, desenvolvido como parte de uma avaliação técnica. Ele é composto por duas partes principais:

-   **Backend:** Uma API RESTful construída com Spring Boot ```(https://github.com/giocarvalho07/transferApp)```
-   **Frontend:** Uma interface de usuário moderna e reativa construída com Vue.js ```(https://github.com/giocarvalho07/transferencias-app)```

## Tecnologias Utilizadas

- Java 11
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (em memória)
- Swagger OpenAPI
- Lombok


## Regras de Negócio (Cálculo de Taxas)

| Dias de Antecedência | Tipo de Taxa              | Valor da Taxa                          |
|----------------------|---------------------------|----------------------------------------|
| 0                    | Fixa + Percentual         | R$ 3,00 + 2,5% do valor                |
| 1 a 10               | Fixa                      | R$ 12,00                               |
| 11 a 20              | Percentual                | 8,2% do valor                          |
| 21 a 30              | Percentual                | 6,9% do valor                          |
| 31 a 40              | Percentual                | 4,7% do valor                          |
| 41 a 50              | Percentual                | 1,7% do valor                          |
| > 50 dias            | **Inválido**              | Rejeitado com erro                     |

---

## Como rodar o projeto localmente

```bash```
git clone https://github.com/giocarvalho07/transferApp.git
cd transferApp
./mvnw spring-boot:run

Acesse a documentação Swagger: http://localhost:8080/swagger-ui.html

---

## Endpoints da API

``` POST /api/transferencias ```
Agendar nova transferência

``` GET /api/transferencias  ```
Retorna todas as transferências agendadas.



## Casos de Teste Manuais </br>

Cenário	Entrada (dias)	Valor	Taxa Esperada	Status </br>
Transferência Hoje	0	1000	R$ 28.00	        ✅ Sucesso </br>
Transferência em 5 dias	6	2000	R$ 12.00	    ✅ Sucesso </br>
Transferência em 15 dias	16	3000	R$ 246.00	✅ Sucesso </br>
Transferência em 25 dias	26	1500	R$ 103.50	✅ Sucesso </br>
Transferência em 36 dias	37	1000	R$ 47.00	✅ Sucesso </br>
Transferência em 50 dias	50	1000	R$ 17.00	✅ Sucesso </br>
Transferência em 60 dias	60	1000	         	❌ Erro 400</br>

⚠️ Validações Importantes </br>
Conta destino com exatamente 10 dígitos </br>
Valor deve ser positivo </br>
Data da transferência deve ser hoje ou no futuro </br>
Transferência acima de 50 dias será rejeitada </br>



## POSTMAN COLLECTION (JSON)
Criei uma coleção com:

6 cenários de sucesso (taxas válidas)

1 cenário de erro (data inválida > 50 dias)

Organizada em uma coleção chamada TransferApp Tests





## Importar Testes no Postman

Você pode importar os testes manuais diretamente no [Postman](https://www.postman.com/) com o arquivo JSON disponível neste repositório:

📁 [`transferapp-postman-collection.json`](src/main/resources/static/transferapp-postman-collection.json)

> Caminho: `src/main/resources/static/transferapp-postman-collection.json`

A coleção contém:
- ✅ 6 testes válidos com taxas aplicadas
- ❌ 1 teste inválido (erro de agendamento acima de 50 dias)

Para utilizar:
1. Abra o Postman
2. Vá em **Import > Upload Files**
3. Selecione o arquivo JSON acima
4. Execute os testes apontando para `http://localhost:8080`






