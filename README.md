# Weather Service
A Java weather microservice.


## Prerequisite
1.  Java 11+
2.  Maven 3.6.2+
3.  [kn](https://github.com/knative/client) (make sure it's installed and properly configured to connect to your cluster)
4.  Valid account and API token from [OpenWeather](https://openweathermap.org/api)


### Mac
Install Java and Maven
```
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk11
export JAVA_HOME=`/usr/libexec/java_home -v 11`
brew install maven
```


## Setup
`echo 'WEATHER_API_TOKEN=YOUR_TOKEN_HERE' > .env`


## Tests
`./mvnw test`


## Development
`./mvnw quarkus:dev`


## OpenShift
To deploy on OpenShift, run the following, but make sure to replace with your own Dockerhub username and your workspace name.  Note uses `kn`.

```
cd $PROJECT_ROOT
cp ./src/main/docker/Dockerfile.jvm .
docker login
docker build -t your_dockerhub_username_here/weather-service .
docker push your_dockerhub_username_here/weather-service
kn service create weather-service --namespace yournamespace_here --image registry.hub.docker.com/your_dockerhub_username_here/weather-service:latest --env WEATHER_API_TOKEN=your_weather_api_token_here --force
```


## CodeReady workspace
Open a terminal and run the following.

```
ssh-keygen -t rsa
cat ~/.ssh/id_rsa.pub  # Add this key to your github profile
git config --global user.name "Jon Keam"
git config --global user.email jkeam@redhat.com
```

Also create a `~/.ssh/config` that looks like
```
Host github.com
IdentityFile ~/.ssh/id_rsa
```

Now you can clone your repo, something like this:
```
git clone git@github.com:jkeam/weather-service.git`
cd weather-service
```


## Misc
### Packaging

The application can be packaged using `./mvnw package`.
It produces the `weather-service-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/weather-service-1.0-SNAPSHOT-runner.jar`.

### Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/weather-service-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
