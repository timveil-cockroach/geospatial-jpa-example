start local single node crdb instance by running...
```
./up.sh
```

run the following to build and execute the test...

```
./mvnw clean spring-boot:run
```

the application automatically creates the database schema using JPA. A table called `Aircraft` will be created with the following sql...

```
CREATE TABLE public.aircraft (
  id UUID NOT NULL,
  location GEOMETRY NULL,
  CONSTRAINT "primary" PRIMARY KEY (id ASC),
  FAMILY "primary" (id, location)
)
```

once the database is created a simple insert of the `geometry` data type is completed.