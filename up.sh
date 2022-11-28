#!/bin/bash

docker run -d -it -p 8080:8080 -p 26257:26257 -e "DATABASE_NAME=test" -e "MEMORY_SIZE=.5" timveil/cockroachdb-single-node:latest