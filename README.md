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

a single row insert is attempted using JPA/Hibernate and fails with the following error when using the `org.hibernate.dialect.CockroachDB201Dialect` but succeeds when using `org.hibernate.spatial.dialect.postgis.PostgisDialect`...
```
java.lang.IllegalStateException: Failed to execute ApplicationRunner
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:798) ~[spring-boot-2.4.0.jar:2.4.0]
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:785) ~[spring-boot-2.4.0.jar:2.4.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:333) ~[spring-boot-2.4.0.jar:2.4.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1309) ~[spring-boot-2.4.0.jar:2.4.0]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1298) ~[spring-boot-2.4.0.jar:2.4.0]
	at DemoApplication.main(DemoApplication.java:12) ~[classes/:na]
Caused by: org.springframework.orm.jpa.JpaSystemException: could not execute statement; nested exception is org.hibernate.exception.GenericJDBCException: could not execute statement
	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:331) ~[spring-orm-5.3.1.jar:5.3.1]
	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:233) ~[spring-orm-5.3.1.jar:5.3.1]
	at org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:566) ~[spring-orm-5.3.1.jar:5.3.1]
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:743) ~[spring-tx-5.3.1.jar:5.3.1]
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:711) ~[spring-tx-5.3.1.jar:5.3.1]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:637) ~[spring-tx-5.3.1.jar:5.3.1]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:390) ~[spring-tx-5.3.1.jar:5.3.1]
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:134) ~[spring-tx-5.3.1.jar:5.3.1]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.1.jar:5.3.1]
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137) ~[spring-tx-5.3.1.jar:5.3.1]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.1.jar:5.3.1]
	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:174) ~[spring-data-jpa-2.4.1.jar:2.4.1]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.1.jar:5.3.1]
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97) ~[spring-aop-5.3.1.jar:5.3.1]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.3.1.jar:5.3.1]
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215) ~[spring-aop-5.3.1.jar:5.3.1]
	at com.sun.proxy.$Proxy61.save(Unknown Source) ~[na:na]
	at DataLoader.run(DataLoader.java:26) ~[classes/:na]
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:795) ~[spring-boot-2.4.0.jar:2.4.0]
	... 5 common frames omitted
Caused by: org.hibernate.exception.GenericJDBCException: could not execute statement
	at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:47) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:113) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:99) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:200) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3254) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3781) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.action.internal.EntityInsertAction.execute(EntityInsertAction.java:107) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:604) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.engine.spi.ActionQueue.lambda$executeActions$1(ActionQueue.java:478) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at java.base/java.util.LinkedHashMap.forEach(LinkedHashMap.java:723) ~[na:na]
	at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:475) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:348) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.event.internal.DefaultFlushEventListener.onFlush(DefaultFlushEventListener.java:40) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:102) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.internal.SessionImpl.doFlush(SessionImpl.java:1362) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.internal.SessionImpl.managedFlush(SessionImpl.java:453) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.internal.SessionImpl.flushBeforeTransactionCompletion(SessionImpl.java:3212) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.internal.SessionImpl.beforeTransactionCompletion(SessionImpl.java:2380) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.engine.jdbc.internal.JdbcCoordinatorImpl.beforeTransactionCompletion(JdbcCoordinatorImpl.java:447) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl.beforeCompletionCallback(JdbcResourceLocalTransactionCoordinatorImpl.java:183) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl.access$300(JdbcResourceLocalTransactionCoordinatorImpl.java:40) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl$TransactionDriverControlImpl.commit(JdbcResourceLocalTransactionCoordinatorImpl.java:281) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.hibernate.engine.transaction.internal.TransactionImpl.commit(TransactionImpl.java:101) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	at org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:562) ~[spring-orm-5.3.1.jar:5.3.1]
	... 21 common frames omitted
Caused by: org.postgresql.util.PSQLException: ERROR: wkb: unknown byte order: 10101100
	at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2553) ~[postgresql-42.2.18.jar:42.2.18]
	at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2285) ~[postgresql-42.2.18.jar:42.2.18]
	at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:323) ~[postgresql-42.2.18.jar:42.2.18]
	at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:473) ~[postgresql-42.2.18.jar:42.2.18]
	at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:393) ~[postgresql-42.2.18.jar:42.2.18]
	at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:164) ~[postgresql-42.2.18.jar:42.2.18]
	at org.postgresql.jdbc.PgPreparedStatement.executeUpdate(PgPreparedStatement.java:130) ~[postgresql-42.2.18.jar:42.2.18]
	at com.zaxxer.hikari.pool.ProxyPreparedStatement.executeUpdate(ProxyPreparedStatement.java:61) ~[HikariCP-3.4.5.jar:na]
	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.executeUpdate(HikariProxyPreparedStatement.java) ~[HikariCP-3.4.5.jar:na]
	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.executeUpdate(ResultSetReturnImpl.java:197) ~[hibernate-core-5.4.23.Final.jar:5.4.23.Final]
	... 41 common frames omitted
```

## New issues with `5.4.30`
The new `org.hibernate.spatial.dialect.cockroachdb.CockroachDB202SpatialDialect` incorrectly generates the `create table` sql.

With `org.hibernate.spatial.dialect.cockroachdb.CockroachDB202SpatialDialect` the table is generated like this... 

```
2021-03-23 09:37:39.468  INFO 81039 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.spatial.dialect.cockroachdb.CockroachDB202SpatialDialect
Hibernate:

    drop table if exists public.aircraft cascade
Hibernate:

    create table public.aircraft (
       id GEOMETRY not null,
        location geometry,
        primary key (id)
    )

```

and fails with...

```
2021-03-23 09:53:07.908  WARN 81885 --- [           main] com.zaxxer.hikari.pool.ProxyConnection   : HikariPool-1 - Connection org.postgresql.jdbc.PgConnection@224d86d2 marked as broken because of SQLSTATE(0A000), ErrorCode(0)

org.postgresql.util.PSQLException: ERROR: unimplemented: column id is of type geometry and thus is not indexable
  Hint: You have attempted to use a feature that is not yet implemented.
See: https://go.crdb.dev/issue-v/35730/v20.2
	at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2553) ~[postgresql-42.2.19.jar:42.2.19]
```

As you can see the Aircraft Entity is specifying the `@Id` column as type `UUID` not `geometry`.  The table is properly generated when using the traditional (non-spatial) dialect (`org.hibernate.dialect.CockroachDB201Dialect`)

### Workaround
Since both `geometry` and `uuid` are mapped to the java `SQLType.OTHER` there is type collision occurring.  This will not be properly resolved until Hibernate 6.  In the meantime, migrate UUID columns to their String representation or annotate your UUID column with this ` @Column(columnDefinition = "uuid")` to workaround this issue.
