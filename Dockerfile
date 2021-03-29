FROM bespalovdn/my_centos_jdk_git_maven_make:7
RUN  git clone https://github.com/BDNBESPALOV/jclient.git && cd jclient/
WORKDIR jclient/
CMD make

# просмотр используемого докером дискового пространства
# docker system df
# очистка кеша
# docker builder prune -a