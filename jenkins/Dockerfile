FROM jenkins/jenkins:lts

USER root
RUN apt-get update \
      && apt-get install -y sudo curl\
      && apt-get install -y libltdl7\
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
  && tar xzvf docker-17.04.0-ce.tgz \
  && mv docker/docker /usr/local/bin \
  && rm -r docker docker-17.04.0-ce.tgz

RUN groupadd docker

#ensures that /var/run/docker.sock exists
RUN touch /var/run/docker.sock

#changes the ownership of /var/run/docker.sock
RUN chown root:docker /var/run/docker.sock

#gives jenkins user permissions to access /var/run/docker.sock
RUN usermod -a -G docker jenkins

COPY .pypirc /root/.pypirc

USER jenkins

RUN sudo chmod 666 /var/run/docker.sock


ENV JENKINS_HOME /var/jenkins_home
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

#WORKDIR /home/jenkins/workspace

# copy the config-as-code yaml file into the image
COPY casc.yaml /var/jenkins_home/casc.yaml
COPY job.groovy /home/jenkins/workspace/jobdsl/job.groovy

