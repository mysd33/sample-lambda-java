# Private APIでのAPIGatewayを使ったLambda/Java/SpringBoot&CloudFunctionのAWS SAMサンプルAP

## 構成イメージ
* 今後、記載の充実化を予定

* Spring Cloud Functionを利用したAPIGateway/LambdaによるJavaアプリケーションを実装
* コールドスタートの高速化対策のため、Lambda SnapStartを利用
* 参考サイト
    * https://catalog.workshops.aws/java-on-aws-lambda/en-US/03-snapstart
    * https://maciejwalkowiak.com/blog/create-spring-cloud-function-aws-sam/
    * https://docs.spring.io/spring-cloud-function/docs/current/reference/html/

## TODO: 各リソースの作成
* 今後、記載の充実化を予定

```sh
# ビルド
mvn clean package
# Windowsでもmakeをインストールすればmakeでいけます
make

# デプロイ
# 1回目
sam deploy -t template.yaml --guided
# Windowsでもmakeをインストールすればmakeでいけます
make deploy_guided

# 2回目以降
sam deploy -t template.yaml
# Windowsでもmakeをインストールすればmakeでいけます
make deploy

# AP動作確認
## ユーザサービス
### POST
curl -X POST -H "Content-Type: application/json" -d '{ "user_name" : "Taro"}' https://42b4c7bk9g.execute-api.ap-northeast-1.amazonaws.com/Prod/users
### 以下で返却
{"user_id":"d5813014-065b-4b3f-b674-66c2571f8848","user_name":"Taro"}

### GET（パスにユーザID指定）
curl https://42b4c7bk9g.execute-api.ap-northeast-1.amazonaws.com/Prod/users/d5813014-065b-4b3f-b674-66c2571f8848

## Todoサービス
### POST
curl -X POST -H "Content-Type: application/json" -d '{ "todo_title" : "ミルクを買う"}' https://civuzxdd14.execute-api.ap-northeast-1.amazonaws.com/Prod/todo
{"todo_id":"c6818bde-b8fc-46bb-8ac6-9eddfbff40cf","todo_title":"ミルクを買う"}
### GET（パスにTODO ID指定）
curl https://civuzxdd14.execute-api.ap-northeast-1.amazonaws.com/Prod/todo/c6818bde-b8fc-46bb-8ac6-9eddfbff40cf

# スタック削除
sam delete
# Windowsでもmakeをインストールすればmakeでいけます
make delete
```

