

# Spring Actuator

[Java 微服务](https://developer.aliyun.com/article/231274)

[Spring Actuator](https://www.cnblogs.com/chanshuyi/p/06_prometheus_with_springboot_actuator.html)   & [Spring Actuator](https://www.ityouknow.com/springboot/2018/02/06/spring-boot-actuator.html)

[Spring Boot 微服务应用集成Prometheus + Grafana 实现监控告警](https://juejin.cn/post/6844904052417904653#heading-28)





**启动**

> docker run -d \
>     -p 9000:9090 \
>     -v /home/hadoop/hcn/workspace/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml \
>     prom/prometheus
>     
> docker run -d --name=grafana -p 3000:3000 grafana/grafana 



**web**

> http://10.83.1.137:9000
>
> http://10.83.1.137:3000





# ELK[^1]

## Elasticsearch



```shell

docker network create elastic
docker pull docker.elastic.co/elasticsearch/elasticsearch:8.13.2
docker run -d --name es01 --net elastic -p 9200:9200 -it -m 1GB docker.elastic.co/elasticsearch/elasticsearch:8.13.2
# or
docker run -d \
	--name es01 \
	-e "cluster.name=es-docker-single" \
	-e "http.host=0.0.0.0" \
    -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
    -e "discovery.type=single-node" \
    -v es-data:/usr/share/elasticsearch/data \
	-v es-logs:/usr/share/elasticsearch/logs  \
    -v es-plugins:/usr/share/elasticsearch/plugins \
    --privileged \
    --network elastic	 \
    -p 9200:9200 \
    -p 9300:9300 \
docker.elastic.co/elasticsearch/elasticsearch:8.13.2

http://124.71.76.190:9200

elastic:changeme  # 默认密码
docker exec -it "es01" /bin/bash  # 交互模式
#改密1
elasticsearch-setup-passwords interactive
elastic:elasticsearch  #elastic,apm_system,kibana,kibana_system,logstash_system,beats_system,remote_monitoring_user


# 改密码2
docker exec -it es01 /usr/share/elasticsearch/bin/elasticsearch-reset-password -u elastic
docker exec -it es01 /usr/share/elasticsearch/bin/elasticsearch-create-enrollment-token -s kibana

```



启动前清空目录

```shell
docker run -d \
    --name es01 \
    -e "cluster.name=es-docker-single" \
    -e "http.host=0.0.0.0" \
    -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
    -e "discovery.type=single-node" \
    -v es-data:/usr/share/elasticsearch/data \
    -v es-logs:/usr/share/elasticsearch/logs \
    -v es-plugins:/usr/share/elasticsearch/plugins \
    --privileged \
    --network elastic \
    -p 9200:9200 \
    -p 9300:9300 \
    docker.elastic.co/elasticsearch/elasticsearch:8.13.0 \
    /bin/bash -c 'rm -rf /usr/share/elasticsearch/data/* /usr/share/elasticsearch/plugins/*  /usr/share/elasticsearch/logs/* && /usr/share/elasticsearch/bin/elasticsearch'
	
```







## Kibana



**Install**

```shell

docker pull kibana:8.13.0

docker run -d \
--name kibana \
-e ELASTICSEARCH_HOSTS=http://124.71.76.190:9200 \
--network elastic \
-p 5601:5601  \
kibana:8.13.0

http://124.71.76.190:5601

```



**中文配置**

```shell

#进入容器
docker exec -it kibana /bin/bash

#进入配置文件目录
cd /usr/share/kibana/config

#编辑文件kibana.yml
vi kibana.yml

#在最后一行添加如下配置
i18n.locale: zh-CN

#保存退出
exit

#并重启容器
docker restart kibana


```











# REF

[^1]: [Docker安装ElasticSearch教程](https://juejin.cn/post/7074115690340286472)





