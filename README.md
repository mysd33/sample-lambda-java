# Private APIでのAPIGatewayを使ったLambda/Java/SpringBoot&CloudFunctionのAWS SAMサンプルAP

* Spring Cloud Functionを利用したLambda、AWS SAMに関する参考サイト
    * https://catalog.workshops.aws/java-on-aws-lambda/en-US/03-snapstart
    * https://maciejwalkowiak.com/blog/create-spring-cloud-function-aws-sam/
    * https://docs.spring.io/spring-cloud-function/docs/current/reference/html/

* 実行方法
    * 今後、記載の充実化を予定

```sh
# ビルド
mvn clean package
# Windowsにmakeをインストールすればmakeでもいけます
make

# デプロイ
# 1回目
sam deploy -t template.yaml --guided
# Windowsにmakeをインストールすればmakeでもいけます
make deploy_guided

# 2回目以降
sam deploy -t template.yaml
# Windowsにmakeをインストールすればmakeでもいけます
make deploy

# AP動作確認
curl -X POST -H "Content-Type: application/json" -d '{ "userName" : "Taro"}' https://42b4c7bk9g.execute-api.ap-northeast-1.amazonaws.com/Prod/users

# スタック削除
sam delete
```

