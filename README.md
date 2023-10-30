# This is to run mysql server in background
docker run -d -p 3306:3306 -e MYSQL_DATABASE=db -e MYSQL_USER=sherlock -e MYSQL_PASSWORD=elementary -e MYSQL_ALLOW_EMPTY_PASSWORD=true mysql:8

# This is to supply the environment variables to use the above DB url and credentials
export DATASOURCES_DEFAULT_URL=jdbc:mysql://localhost:3306/db
export DATASOURCES_DEFAULT_USERNAME=sherlock
export DATASOURCES_DEFAULT_PASSWORD=elementary

# This is to build and run the application
./gradlew run &

# This is to verify if the new genre entries are added and fetched successfully or not
curl -X "POST" "http://localhost:8080/genres"      -H 'Content-Type: application/json; charset=utf-8'      -d $'{ "name": "music" }'
curl -v http://localhost:8080/genres/list

