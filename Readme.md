# 概要

グラフデータベースにNeo4j、WEBアプリケーションフレームワークにSpring Boot 1.3.0.M3とSpring Data Neo4j 4.0.0-RC2を使用した検索アプリケーションです。
検索機能がメインですが一部更新系も実装しています。
このアプリケーションで使用するデータセットは、Neo4jに用意されてるNorthwind Graphを使わせて貰いました。

**開発環境**

* Windows7 (64bit)
* Java 1.8.0_45
* Spring Boot 1.3.0.M3
* Spring Data Neo4j 4.0.0-RC2
 * Neo4j OGM 1.1.0
* Neo4j Community Edition 2.2.2
* Eclipse 4.4
* Maven 3.3.3


## 実行

Neo4jサーバーを起動した状態で、下記のmvnコマンドでアプリケーションを実行します。

```bash
> mvn spring-boot:run
```

http://localhost:9000/customer/

