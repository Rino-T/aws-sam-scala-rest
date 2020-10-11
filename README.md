# AWS SAM Scala Rest API Sample

## develop

### jar ファイル化
jarファイルをS3にデプロイして、それを利用するので下記でjarファイル化

```shell script
sbt assembly
```

### ローカルでの開発

```shell script
sam local start-api
```

ローカルでAPIサーバーが起動する。

## deploy

### 1 ソースコード配置用のS3を作成

手動でもコマンドでも可

```shell script
aws s3 mb s3://BUCKET_NAME
```


### 2 アプリケーションのパッケージング

```shell script
sam package \
    --output-template-file packaged.yaml \
    --s3-bucket REPLACE_THIS_WITH_YOUR_S3_BUCKET_NAME
```

* [sam package](https://docs.aws.amazon.com/ja_jp/serverless-application-model/latest/developerguide/sam-cli-command-reference-sam-package.html)

### 3 デプロイ

```shell script
sam deploy \
    --template-file packaged.yaml \
    --stack-name sam-app \
    --capabilities CAPABILITY_IAM
```


# 参考

* [scala-sam-app](https://github.com/fancellu/scala-sam-app)
* [How to create serverless applications using AWS SAM](https://github.com/aws/serverless-application-model/blob/master/HOWTO.md)