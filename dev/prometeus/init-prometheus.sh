cp prometheus.yaml /tmp/.
docker run -d -p 9090:9090 -v /tmp/prometheus.yaml:/etc/prometheus/prometheus.yml prom/prometheus