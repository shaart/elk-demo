# Preparations
You'll need installed Docker on your machine.

## With docker-compose
Run command
```shell script
./gradlew build
docker-compose up
```
Check the containers
```shell script
docker ps
```
Check the applications in container:
```shell script
# elk-demo
curl 192.168.99.100:8080/actuator/health

# Response:
# {"status":"UP"}


# elastic
curl -X GET "192.168.99.100:9200/_cat/nodes?v&pretty"

# Response:
# ip         heap.percent ram.percent cpu load_1m load_5m load_15m node.role master name
# 172.18.0.3           33          99   7    0.08    0.24     0.15 dilmrt    *      d052db167119


# kibana
curl -I 192.168.99.100:5601

# Response:
# HTTP/1.1 302 Found
# location: /spaces/enter
# kbn-name: kibana.example.org
# kbn-license-sig: 6d7f5f3be5e9ea9c3fc8534e7eba519ed0a5a447f6f9c7a9a6cda1a5adeb7092
# kbn-xpack-sig: 81d3c61af2eabc8b8afc2034647ad614
# cache-control: private, no-cache, no-store, must-revalidate
# content-length: 0
# Date: Fri, 31 Jul 2020 22:31:45 GMT
# Connection: keep-alive


# logstash
curl 192.168.99.100:9600

# Response:
# {"host":"8687db32b302","version":"7.8.1","http_address":"0.0.0.0:9600","id":"9aee5549-e6c4-48ff-b8d6-6ffc6195ce8d","name":"8687db32b302","ephemeral_id":"7805affc-4752-41f8-9068-52f2f7710549","status":"green","snapshot":false,"pipeline":{"workers":1,"batch_size":125,"batch_delay":50},"build_date":"2020-07-21T19:19:46+00:00","build_sha":"5dcccb963be4c163647232fe4b67bdf4b8efc2cb","build_snapshot":false}
```
_____

## Without docker-compose
#### Application
* Build image with command
```shell script
./gradlew build
docker build -f docker/Dockerfile -t elk-demo .
```
#### Elastic
Link: https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html

Pull the image
```shell script
docker pull docker.elastic.co/elasticsearch/elasticsearch:7.8.1
```
#### Kibana
Link: https://www.elastic.co/guide/en/kibana/current/docker.html

Pull the image
```shell script
docker pull docker.elastic.co/kibana/kibana:7.8.1
```
#### Logstash
Links: 
- https://www.elastic.co/guide/en/logstash/current/docker.html
- https://www.elastic.co/guide/en/logstash/current/docker-config.html

Pull the image:
```shell script
docker pull docker.elastic.co/logstash/logstash:7.8.1
```

## How to run
#### Application
Run the container in the detached mode:
```shell script
docker run -d --name elk-demo -p 8080:8080 elk-demo:latest
```
Check application:
```shell script
curl 192.168.99.100:8080/actuator/health
```
If the answer is `{"status":"UP"}` then it's all right.

### Elastic
Run the container in the detached mode:
```shell script
docker run -d --name elastic -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.8.1
```
Check elasticsearch:
```shell script
curl -X GET "192.168.99.100:9200/_cat/nodes?v&pretty"
```
Answer should be like:
```
ip         heap.percent ram.percent cpu load_1m load_5m load_15m node.role master name
172.18.0.3           33          99   7    0.08    0.24     0.15 dilmrt    *      d052db167119
```

#### Kibana
Run the container in the detached mode:
```shell script
docker run -d --name kibana --link elastic -p 5601:5601 docker.elastic.co/kibana/kibana:7.8.1
```
Check Kibana via browser: http://192.168.99.100:5601/app/kibana#/home

#### Logstash
Run the container in the detached mode:
```shell script
docker run -d --name logstash -e "xpack.monitoring.collection.enabled=false" -e "xpack.monitoring.enabled=false" -e "monitoring.elasticsearch.hosts=http://elastic:9200" -p 5001:5001 -p 9600:9600 docker.elastic.co/logstash/logstash:7.8.1
```
Check Logstash:
```shell script
curl 192.168.99.100:9600
```
The answer should be like
```
{"host":"8687db32b302","version":"7.8.1","http_address":"0.0.0.0:9600","id":"9aee5549-e6c4-48ff-b8d6-6ffc6195ce8d","name":"8687db32b302","ephemeral_id":"7805affc-4752-41f8-9068-52f2f7710549","status":"green","snapshot":false,"pipeline":{"workers":1,"batch_size":125,"batch_delay":50},"build_date":"2020-07-21T19:19:46+00:00","build_sha":"5dcccb963be4c163647232fe4b67bdf4b8efc2cb","build_snapshot":false}
```