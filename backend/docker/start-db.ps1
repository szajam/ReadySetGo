$envFile = Resolve-Path "..\..\.env"
docker compose --env-file $envFile up -d