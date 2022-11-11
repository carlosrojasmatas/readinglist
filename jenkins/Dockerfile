FROM jenkins/jenkins:lts-jdk17
# if we want to install via apt
USER root
RUN apt-get update
RUN apt-get install -y ca-certificates curl
RUN curl -fsSLo /usr/share/keyrings/kubernetes-archive-keyring.gpg https://packages.cloud.google.com/apt/doc/apt-key.gpg
RUN echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | tee /etc/apt/sources.list.d/kubernetes.list
RUN apt-get update && apt-get install -y make inetutils-ping docker kubectl vim
# drop back to the regular jenkins user - good practice
USER jenkins
git