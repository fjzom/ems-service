docker login --username foviedom --password 123docker

docker kill emsdb
docker rm emsdb
docker run -d -p 5432:5432 --name emsdb -e POSTGRES_PASSWORD=emsdb -d emsdb
docker cp ${SCRIPT_DIR}/data.sql emsdb:data.sql
docker cp schema.sql emsdb:schema.sql
echo "Waiting for psql to start..."
sleep 5

# first -d flag: detached mode (Docker), second: database for postgres
docker exec -d emsdb psql postgres -d postgres -f schema.sql
docker exec -d emsdb psql postgres -d postgres -f data.sql
#docker run -it --rm --link local-postgres:postgres postgres psql -h postgres -U postgres -W

