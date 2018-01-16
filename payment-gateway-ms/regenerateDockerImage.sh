#!/bin/bash

function _deleteDockerImage () {
    echo "Deleting last docker image..."
    docker rmi -f payment-gateway-ms:0.0.1
}

function _buildDockerImage () {
    echo "Building docker image..."
    gradle buildDockerImage
}

time(_deleteDockerImage)
time(_buildDockerImage)




