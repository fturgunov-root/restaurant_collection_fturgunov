#!/bin/sh
../setup-env.sh

docker-compose \
-f restaurant_ft_postgres.dev.yml \
up -d --force-recreate "${@}"
