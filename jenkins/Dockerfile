# Use official Jenkins LTS image
FROM jenkins/jenkins:lts

# Install Docker inside Jenkins container
USER root
RUN apt-get update && \
    apt-get install -y sudo docker.io curl git && \
    curl -fsSL https://get.docker.com/ | sh

# Install Docker Compose
RUN curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose

# Add Jenkins user to Docker group (optional, but necessary if you want Jenkins to run Docker commands)
RUN usermod -aG docker jenkins    

# Set Jenkins user back to normal


# Expose Jenkins port
EXPOSE 8080
