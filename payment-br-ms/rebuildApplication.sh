#!/bin/bash

function _downCompose () {
    docker-compose down
}
function _deleteDockerImage () {
    echo "Deleting last docker image..."
    docker rmi payment-br-test-ms:0.0.1
}

function _buildDockerImage () {
    echo "Building docker image..."
    gradle buildDockerImage
}

function _upCompose () {
    echo "Docker componse up..."
    docker-compose up
}

time(_downCompose)
time(_deleteDockerImage)
time(_buildDockerImage)
time(_upCompose)



