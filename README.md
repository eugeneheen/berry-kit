# berry-kit
本工程封装了Java中常用的一些工具箱，开箱即用的工具方法，需要JDK1.8

## 工程构建及文档生成
工程采用Gradle作为构建工具，常用命令：
- 构建模块
```
$ gradle build
```

- 生成文档
```
$ gradle javadoc
```

> 无论是构建生成的jar文件，还是生成API文档均在对项目根目录的build文件夹中


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
<version>1.11</version>
</dependency>
```

Gradle：
```
compile 'commons-codec:commons-codec:1.11'
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

- OkHttp3
```
<dependency>
 <groupId>com.squareup.okhttp3</groupId>
 <artifactId>okhttp</artifactId>
 <version>3.11.0</version>
</dependency>
```

Gradle：
```
compile 'com.squareup.okhttp3:okhttp:3.11.0'
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
