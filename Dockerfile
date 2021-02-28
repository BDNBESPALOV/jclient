#FROM bespalovdn/mycent:0.1
FROM centos:7
RUN yum install java-1.8.0-openjdk -y && yum install git -y && yum install make -y && yum install maven -y && git clone https://github.com/BDNBESPALOV/jclient.git && cd jclient/ 
WORKDIR jclient/
CMD make build