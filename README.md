# Private APIでのAPIGatewayを使ったLambda/Java/SpringBoot&CloudFunctionのAWS SAMサンプルAP

* 今後、記載予定

```sh
# ビルド
mvn clean package

# 1回目
sam deploy -t template.yaml --guided
# 2回目以降
sam deploy -t template.yaml

# 削除
sam delete
```

