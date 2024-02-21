## Adatbázis:
- Azure SQL Edge Docker image-et használtam
```yaml
    docker run --cap-add SYS_PTRACE -e 'ACCEPT_EULA=1' -e 'MSSQL_SA_PASSWORD=KesmarkiMssqlPW1!' \
    -p 1433:1433 --name mssql-container \
    -d mcr.microsoft.com/azure-sql-edge
```
- Spring datasource:
```yaml
spring:
  datasource:
    url: jdbc:sqlserver://;serverName=localhost;databaseName=kesmarkiDb;encrypt=true;trustServerCertificate=true;
    username: SA
    password: KesmarkiMssqlPW1!
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

## Extra függőségek:
```xml
        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>2.4.4</version>
        </dependency>
  
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
```

## SQL file-ok:
- Resources folder-ban található mind a DDL és DML file.

