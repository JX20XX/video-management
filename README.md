# video-management Project Instructions
## The instructions of the project directories
1. `deploy` folder used to store the configuration of the project deployment.
    - `vms.sh` is a shell script file. It is used to start, stop, restart and status the project progress.
    - `assembly.xml` is a configuration file to package the `vms.sh` and the project jar file in to a zip file.
      It is used in the `pom.xml` file:
      It is used in the `pom.xml` file:
        ```xml
       <build>
           ...
           <plugins>
               ...
               <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <!-- specify the assembly configure file path -->
                    <descriptors>
                        <descriptor>deploy/assembly.xml</descriptor>
                    </descriptors>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <!-- run one time -->
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
               </plugin>
               ...
           </plugins>
           ...
       </build>
        ```
        - The `deploy` sub-folder is used to store the environment configurations, like: DB connection configuration, server port etc.
2. `pom.xml` file is Maven configure file. It is used to configure the dependency of the third part lib used by the project and has some configurations related to the `deploy` folder.
   This is related to `deploy` folder configuration in the `pom.xml`
     ```xml
    <project>
        ...
        <profiles>
            <profile>
                <id>local</id>
                <activation>
                    <activeByDefault>true</activeByDefault>
                </activation>
                <properties>
                    <profileActive>local</profileActive>
                </properties>
            </profile>
            <profile>
                <id>test</id>
                <properties>
                    <profileActive>test</profileActive>
                </properties>
            </profile>
            <profile>
                <id>production</id>
                <properties>
                    <profileActive>production</profileActive>
                </properties>
            </profile>
        </profiles>
        ...
        <build>
          ...
          <plugins>
            ...
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>properties-maven-plugin</artifactId>
                <version>1.0.0</version>
                <executions>
                    <execution>
                        <id>default-cli</id>
                        <phase>initialize</phase>
                        <goals>
                            <goal>read-project-properties</goal>
                        </goals>
                        <configuration>
                            <files>
                                <file>${project.basedir}/deploy/${profileActive}/profile.properties</file>
                            </files>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            ...
          </plugins>
          ...
        </build>
        ...
    </project>
     ```
    3. `src` folder is source code directory.
4. `.gitignore` file is git configure file. This file can tell Git which files are to ignore when the files modified, committed and pushed.

## Use Maven package
### Use IDEA integrated Maven
1. Select menu "Run" and select the "Edit Configurations" submenu.
2. The "Run/Debug Configurations" window display.
3. Click the "+" button on the left top. "Add New Configuration" pop-up window comes.
4. Select "Maven", configuration detail will display on the right.
5. In the "Command line:" item, input the maven command:
    - local: clean package -P local -DskipTests -f pom.xml // this is used for local development environment
    - test: clean package -P test -DskipTests -f pom.xml // this is used for test server
    - production: clean package -P production -DskipTests -f pom.xml // this is used for online environment

APIs of the VMS:
1. Add a new video
   URL:http://localhost:8080/videos
   Method: POST
   Data:
   ```json
   {
      "title":"add with RESTful client",
      "description": "this is a test video metadata",
      "uploadTimestamp":"2024-04-03T23:50:40+01:00",
      "duration":1800,
      "resolution":"1080P",
      "format":"AVC/H.264"
   }
   ```
   Response:
   ```json
   {
      "code": 200,
      "msg": "Saved",
      "data": null
   }
   ```
2. Get a video list
   URL: http://localhost:8080/videos
   Method: GET
   Response:
   ```json
   {
      "code": 200,
      "msg": "SUCCESS",
      "data": [
         {
            "id": 1,
            "title": "add with RESTful client",
            "description": "this is a test video metadata",
            "uploadTimestamp": "2024-04-03T23:50:40",
            "duration": 1800,
            "resolution": "1080P",
            "format": "AVC/H.264",
            "status": "PENDING"
         }
      ]
   }
   ```
3. Get a video metadata detail
   URL: http://localhost:8080/videos/{id}
   Method: GET
   Response:
   ```json
   {
     "code": 200,
     "msg": "SUCCESS",
     "data": {
        "id": 2,
        "title": "add with RESTful client",
        "description": "this is a test video metadata",
        "uploadTimestamp": "2024-04-03T23:50:40",
        "duration": 1800,
        "resolution": "1080P",
        "format": "AVC/H.264",
        "status": "PENDING"
     }
   }
   ```
4. Update a video status
   URL: http://localhost:8080/videos/{id}
   Method: PUT
   Response:
   ```json
   {
     "code": 200,
     "msg": "The video is already under processing",
     "data": {
        "id": 2,
        "title": "add with RESTful client",
        "description": "this is a test video metadata",
        "uploadTimestamp": "2024-04-03T23:50:40",
        "duration": 1800,
        "resolution": "1080P",
        "format": "AVC/H.264",
        "status": "PROCESSING"
     }
   }
   ```
5. Delete a video metadata
   URL: http://localhost:8080/videos/{id}
   Method: DELETE
   Response:
   ```json
   {
     "code": 200,
     "msg": "The video has been deleted",
     "data": {
        "id": 2,
        "title": "add with RESTful client",
        "description": "this is a test video metadata",
        "uploadTimestamp": "2024-04-03T23:50:40",
        "duration": 1800,
        "resolution": "1080P",
        "format": "AVC/H.264",
        "status": "COMPLETED"
     }
   }
   ```