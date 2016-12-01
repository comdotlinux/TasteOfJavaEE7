FROM airhacks/payara-monitored:latest
ENV MYSQL_CONNECTOR_JAVA_VERSION 6.0.5
RUN curl -k -L -o ${PAYARA_HOME}/domains/${DOMAIN_NAME}/lib/ext/mysql-connector-java.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/${MYSQL_CONNECTOR_JAVA_VERSION}/mysql-connector-java-${MYSQL_CONNECTOR_JAVA_VERSION}.jar
RUN asadmin start-domain ${DOMAIN_NAME} && \
    asadmin --passwordfile=payarapwd create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype javax.sql.DataSource --property user=xxxxxx:password=xxxxxxxxx:DatabaseName=sakila:ServerName=sakila:port=3306 mysql-pool && \
	asadmin --passwordfile=payarapwd create-jdbc-resource --connectionpoolid mysql-pool --enabled jdbc/MysqlPool && \
    asadmin stop-domain ${DOMAIN_NAME}
COPY target/movie-info.war ${DEPLOYMENT_DIR}