# Transaction API - Spring Boot + JdbcTemplate

API REST construite avec Spring Boot 3.3.x, Java 21, Spring Web, Spring JDBC et PostgreSQL.

Aucune dépendance JPA/Hibernate n'est utilisée. L'accès aux données est réalisé avec `JdbcTemplate` et des requêtes SQL explicites.

## Démarrage

1. Créer la base et les tables : exécuter `database.sql` depuis `psql`.
2. Insérer les données de test : exécuter `inserts.sql`.
3. Configurer `DB_USERNAME` et `DB_PASSWORD` ou modifier `application.properties`.
4. Lancer l'application :

```bash
mvn spring-boot:run
```

## Endpoints

### GET /transactions?type=IN|OUT
Retourne toutes les transactions. Le paramètre `type` est optionnel.

### GET /accounts/{id}/transactions
Retourne les transactions d'un compte.

### POST /transaction
Exemple de corps JSON :

```json
{
  "accountId": "ACC-001",
  "transactionType": "IN",
  "amount": 250.00,
  "reason": "Deposit"
}
```

### GET /account/{id}/balance
Retourne le solde calculé comme :

`SUM(IN) - SUM(OUT)`
