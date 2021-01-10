port=5432
if [ -z "$1" ]
	then
		echo "Default port 5432 is used for PostgreSQL database"
  else
    port=$1
fi
docker run --name hiraeth-postgres -e POSTGRES_USER=hiraeth -e POSTGRES_PASSWORD=hiraeth -e POSTGRES_DB=hiraeth -p $port:5432 -d postgres
