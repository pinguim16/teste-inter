# Api Banco Inter - Teste

Api para avaliação de código Banco Inter

**Para subir application:**
` mvnw spring-boot:run `

**To run unit tests:**
` mvn test `

**Base URL:**
```
 http://localhost:8080/
 [HEADER] Content-Type: application/json
```
### User CRUD

- **List Users**
```
[GET] api/usuario
```

- **Find User**
```
[GET] api/usuario/{Integer}
```

- **Create User**
```
[POST] api/usuario
[BODY] { 
  "name": String, 
  "email": String" 
}
```

- **Update User**
```
[PUT] api/usuario/{Integer}
[BODY] { 
  "name": String, 
  "email": String 
}
```
- **Delete User**
```
[DELETE] api/usuario/{Integer}
```

### Digits CRUD

- **List Digit**
```
[GET] api/digitoUnico
[HEADER] user: {Integer}
```

- **Create Digit**
```
[POST] api/digitoUnico
[HEADER] user: {Optional Integer}
[BODY] { 
  "k": Integer, 
  "n": String containing positive number
}
```
