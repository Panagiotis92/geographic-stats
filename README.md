<h1>A Spring Boot web app for geographic statistics</h1>
<p>
  Requirements:
  <ul>
    <li>JDK 21 or later</li>
    <li>MySQL database</li>
  </ul>
</p>
<p>
  Build & Run:
  <ul>
    <li>
      Start MySQL database
    </li>
    <li>
      Build the application: <strong>mvn clean package -Dspring.datasource.url={yourURL} -Dspring.datasource.username={yourUsername} -Dspring.datasource.password={yourPassword}</strong>
    </li>
    <li>
      Run the application: <strong>java -Dspring.datasource.url={yourURL} -Dspring.datasource.username={yourUsername} -Dspring.datasource.password={yourPassword} -jar geographic_stats-0.0.1-SNAPSHOT.jar</strong>
    </li>
  </ul>
</p>
<p>
  Try out the REST API at <strong>http://localhost:8080/geographic-stats/swagger-ui/index.html</strong>
</p>
