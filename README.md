# boot-rsocket

This is a my first [RSocket](https://rsocket.io/) server application.

## Run

```
./gradle bootRun
```

##  CLI

use [rsc](https://github.com/making/rsc)  

```
// download rsc
$ cd rsc
$ wget https://github.com/making/rsc/releases/download/0.6.1/rsc-0.6.1.jar

// request-response
$ sh request-response.sh

// fire-and-forget
$ sh fire-and-forget.sh

// request-stream
$ sh request-stream.sh
```

## Monitoring

Micrometer Prometheus

```
http://localhost:18080/actuator/prometheus
```

Metrics

```
rsocket_request_response_seconds_count{signal_type="CANCEL",} 0.0
rsocket_request_response_seconds_sum{signal_type="CANCEL",} 0.0
rsocket_request_response_seconds_count{signal_type="ON_ERROR",} 0.0
rsocket_request_response_seconds_sum{signal_type="ON_ERROR",} 0.0
rsocket_request_response_seconds_count{signal_type="ON_COMPLETE",} 2.0
rsocket_request_response_seconds_sum{signal_type="ON_COMPLETE",} 0.003192502
rsocket_request_response_seconds_max{signal_type="CANCEL",} 0.0
rsocket_request_response_seconds_max{signal_type="ON_ERROR",} 0.0
rsocket_request_response_seconds_max{signal_type="ON_COMPLETE",} 0.001779824
rsocket_request_channel_total{signal_type="CANCEL",} 0.0
rsocket_request_channel_total{signal_type="ON_ERROR",} 0.0
rsocket_request_channel_total{signal_type="ON_COMPLETE",} 0.0
rsocket_metadata_push_total{signal_type="CANCEL",} 0.0
rsocket_metadata_push_total{signal_type="ON_ERROR",} 0.0
rsocket_metadata_push_total{signal_type="ON_COMPLETE",} 0.0
rsocket_request_stream_total{signal_type="CANCEL",} 0.0
rsocket_request_stream_total{signal_type="ON_ERROR",} 0.0
rsocket_request_stream_total{signal_type="ON_COMPLETE",} 1.0
rsocket_request_fnf_total{signal_type="CANCEL",} 0.0
rsocket_request_fnf_total{signal_type="ON_ERROR",} 0.0
rsocket_request_fnf_total{signal_type="ON_COMPLETE",} 0.0
```

