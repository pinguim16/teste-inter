# Api Banco Inter - Teste

Api para avaliação de código Banco Inter

**Para subir application:**
` mvnw spring-boot:run `

**To run unit tests:**
` mvnw test `
* Test unitários aplicados apenas para services, pois o teste de intregração está no postman.

**Base URL:**
```
 http://localhost:8080/api
 [HEADER] Content-Type: application/json
```
### User CRUD

- **Listagem dos usuários**
```
[GET] /usuario?size={Integer}&page={Integer}
```

- **Busca Usuário**
```
[GET] /usuario/{Integer}
```

- **Criação do Usuário**
```
[POST] /usuario
[BODY] { 
  "nome": String, 
  "email": String" 
}
```

- **Atualização Usuário**
```
[PUT] /usuario
[BODY] { 
  "id": Long
  "name": String, 
  "email": String 
}
```
- **Apagar usuário**
```
[DELETE] /usuario/{Integer}
```

- **Buscar cálculos usuário**
```
[GET] /listar-calculos/{Integer}
```

### Digito Único

- **Cálculo Digito Único**
```
[GET] /digito-unico/{String}/{Integer}
[Body] user: {Integer}
```

### Criptografia
- **Criptografia dos dados do usuário**
```
[POST] /criptografia
[Body] chavePublica: {String}
```
- Como não foi informado na documentação o que fazer com os dados criptografados, os mesmo foram salvos no banco para validação

- **Gera Chave Pública**
```
[POST] /criptografia/gerar-chave-publica/{Integer}
```