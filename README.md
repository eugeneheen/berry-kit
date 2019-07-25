# berry-kit[`:cn:``:strawberry:`浆果工具箱`:cherries:``:grapes:`]
本工程封装了Java中常用的一些工具箱，开箱即用的工具方法，需要JDK1.8

## 版本发布说明
- 当前发布版本号：1.0.12
- Maven依赖
```
<dependency>
    <groupId>com.github.eugeneheen</groupId>
    <artifactId>berry-kit</artifactId>
    <version>1.0.12</version>
</dependency>
```
- Gradle依赖
```
compile 'com.github.eugeneheen:berry-kit:1.0.12'
```
- 发布特性说明
  -  新增DES、3DES、AES对称加密工具方法
  -  新增RSA非对称加密工具方法
- Fix：
  - 完善CodecKitTest单元测试
  - 重构CodecKit工具包原有部分方法实现
  - 完善CodecKit文档注释

## API文档
- 最新版本1.0.12在线文档：https://eugeneheen.gitee.io/berrykit-doc
- 历史版本API文档，请自行生成本地API文档：
```
$ gradle javadoc
```
>生成的本地API文档，存放在项目根目录的build\docs\javadoc文件夹中，直接运行index.html查看API文档。

## 工程构建
工程采用Gradle作为构建工具，常用命令：
- 构建berry-kit
```
$ gradle build
```
> 通过命令```gradle build```构建生成的jar文件，存放在项目根目录的build文件夹中

- 发布berry-kit至本地Maven仓库
```
$ gradle publishToMavenLocal
```

## 项目的第三方依赖
封装工具箱的部分工具方法，也是用到主流的第三方依赖，具体版本说明见下文

- Apache Commons Lang3

Maven：
```
<dependency>
 <groupId>org.apache.commons</groupId>
 <artifactId>commons-lang3</artifactId>
 <version>3.7</version>
</dependency>
```

Gradle：
```
compile 'org.apache.commons:commons-lang3:3.7'
```
 
- Apache Commons Collections4
 
Maven：
```
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-collections4</artifactId>
  <version>4.2</version>
</dependency>
```

Gradle：
```
compile 'org.apache.commons:commons-collections4:4.2'
```
 
- Apache Commons Codec

Maven：
```
<dependency>
<groupId>commons-codec</groupId>
<artifactId>commons-codec</artifactId>
<version>1.12</version>
</dependency>
```

Gradle：
```
compile 'commons-codec:commons-codec:1.12'
```
 
- Apache Commons Text

Maven：
```
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-text</artifactId>
<version>1.4</version>
</dependency>
```

Gradle：
```
compile 'org.apache.commons:commons-text:1.4'
```
 
- Apache Commons HttpComponents HttpClient

Maven：
```
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <version>4.5.6</version>
</dependency>
```

Gradle：
```
compile 'org.apache.httpcomponents:httpclient:4.5.6'
```

- Jackson

Maven：
```
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>2.9.6</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.9.6</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-annotations</artifactId>
  <version>2.9.6</version>
</dependency>
```

Gradle：
```
compile 'com.fasterxml.jackson.core:jackson-core:2.9.6'
compile 'com.fasterxml.jackson.core:jackson-databind:2.9.6'
compile 'com.fasterxml.jackson.core:jackson-annotations:2.9.6'
```

- jjwt
```
<dependency>
 <groupId>io.jsonwebtoken</groupId>
 <artifactId>jjwt</artifactId>
 <version>0.9.1</version>
</dependency>
```

Gradle：
```
compile 'io.jsonwebtoken:jjwt:0.9.1'
```
