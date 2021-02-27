FROM bespalovdn/mycent:0.1
RUN yum install git  && yum install make -y && git clone https://github.com/BDNBESPALOV/jclient.git && cd jclient/ && make
WORKDIR jclient/
CMD make run