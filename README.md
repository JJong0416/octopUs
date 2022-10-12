# 🐙Octopus (우리끼리 함께 하니깐)


### *몸과 마음이 건강해지는 도전 **Octopus** (22.07 ~ 21.08)*

**Github :** [***https://github.com/JJong0416/octopus***](https://github.com/JJong0416/octopus)

**Server :** [***www.octopus-ssafy.site***](http://octopus-ssafy.site/)

**Notion :** [*https://www.notion.so/jjongdev/OctopUS-040789f552d24058b076c0dcbd4ea4af*](https://www.notion.so/Octopus-6f9cbbed20ce4c0aa335964cf98efe80)

### 프로젝트 간략 소개

**Octopus**는 자신에게 필요한 도전과 습관을 시도할 수 있는 **웹앱 애플리케이션 플랫폼**입니다. 

같은 목표를 가진 사람들과 함께 **몇 주간의 작은 미션**을 진행하며, **사용자들의 사진 촬영을 통해 인증**이 진행되는 시스템입니다. 소액의 돈을 걸고, 자신이 진행한 미션의 달성률에 따른 **환급**이 발생하게 되며, 꾸준히 인증을 진행한 사용자는 환급에 대한 재미를 느낄 수 있습니다.

## System Architecture
![image](https://user-images.githubusercontent.com/73544708/195254162-4b06e476-51e6-4265-b181-ba64d785d9b8.png)

## ERD(Entity Relationship Diagram)

![image](https://user-images.githubusercontent.com/73544708/195254272-3ab93fc8-9f40-432a-9924-e418b92f532e.png)

## Stack

![image](https://user-images.githubusercontent.com/73544708/195254325-f4d88ed4-1eef-4eea-9229-338966ffa579.png)

## Project Core Features

1. 서버의 트래픽과 정합성 이슈를 해소하기 위해 세션방식이 아닌 **토큰방식으로 로그인 처리**
2. 소셜로그인을 위해 **Oauth2를 이용한 카카오 로그인**
3. 특정 시간에 대용량 데이터의 상태를 변경하기 위해 **스프링 스케줄러**와 **스프링 배치** 사용
4. 클라이언트 사진을 Google Cloud Storage로 관리하고, 사진의 보안성을 높이기 위해 **Pre-Signed 방식 사용**
5. Scale Out과 Nginx를 통해 **서버 트래픽 분산 처리**
6. Jenkins를 통해 GCP 인스턴스 **무중단 배포 및 서버 인스턴스 관리**

# `프로젝트를 진행하며 개선에 노력한 부분😊`

### 1. Artillery Test를 통해 부하테스트 진행

![image](https://user-images.githubusercontent.com/73544708/195254803-fa1f3d2e-5c78-431b-b1c6-868e1df70e3e.png)

- API 서버나 웹 애플리케이션 서버를 만들면 성능테스트가 필요
- **병목 지점**을 찾아야 할 필요도 있고, **서버를 얼마나 트래픽을 받을 수 있는지** 확인하기 위해 진행
    - 클라이언트의 복잡한 사용패턴을 확실하게 알 순 없지만, **단순한 패턴은 확인이 가능**
- 서비스에 수많은 사용자를 포용해야하기 때문에, **부하테스트를 통해 트래픽을 확인 후 서버 증설**

### 2. Enum Type과 Enum Converter를 통해 DB에 String이 아닌 Integer 변환

![image](https://user-images.githubusercontent.com/73544708/195254959-adf9d7d1-6838-4046-87ce-41193a7a7cd1.png)


- 단순히 Status Code만 보고 어떤 상태인지 단번에 알기가 힘들기 때문에, Enum Class를 통해서 Status Code를 관리할 수 있게 하였다.
- 뿐만 아니라, JPA에서 **`@enumerated(enumtype.xxxx)`** 옵션을 줘서 타입을 지정해줄 수 있지만, String Type은 쓸모없이 Database의 메모리를 잡아먹고, Integer Type은 추가로 타입이 들어왔을 때, 기존의 데이터들과 정합성 이슈가 발생할 수 있다.
- 해당 문제를 해결하기 위해서 Enum Class으로 Status Code를 관리하고, 데이터가 들어오고 나갈때, **EnumConverter를 통해 String 타입을 Integer 타입으로 변경**

### 3. Business Custom Exception을 Enum Type으로 관리

![image](https://user-images.githubusercontent.com/73544708/195256268-29b0c2cc-5bae-4e0c-9d2f-e3e505741982.png)

- 모든 예외처리에 있어서 문자열이 들어가게 되서 유지보수에 어려움을 느꼈다.
- 뿐만 아니라, 관련 정보들을 그룹으로 관리할 수 없었으며 특정 예외를 위해서 Exception Class를 빈번히 생성해야하는 불편함이 생겼다.
- Custom Exception을 **Enum Type**으로 관리하면서, 유지보수의 장점을 얻어갈 수 있었고, **관련 정보들을 그룹으로 관리**할 수 있게 되었다.

# `프로젝트를 진행하며 이슈발생과 처리과정😊`

### 1. Spring Batch와 Spring Scheduler를 통해 특정 시간 DB 관리

![image](https://user-images.githubusercontent.com/73544708/195255598-ad2df53f-c5c4-43a7-803d-7d69888dac10.png)

- 개발 초기엔 사용자들이 미션을 생성 후, **사용자들이 직접 특정 시간에 맞춰서 미션**을 진행
- 사용자들의 편의를 위해서 시스템적으로 자동으로 진행되는 서비스 제공
    - **스프링 스케쥴러**와 **스프링 배치**를 통해 **매일 정각에 미션 진행과 데이터 통계 분석**

### 2. 수동배포의 한계와 CI/CD 파이프라인 구축
![image](https://user-images.githubusercontent.com/73544708/195256007-10fdafde-2071-471e-81e0-6da6073b4ebe.png)

- 프로젝트를 진행하며 **개발 환경**과 **배포 환경**이 많이 **다르다는 것**을 확인
- 개발을 진행하며 배포된 상황을 공유할 필요가 있다는 것을 발견.
- 일일이 서버들의 배포를 진행하는 것이 어려워, Jenkins를 통해 **CI/CD 배포 파이프라인을 작성**

### 3. Json Web Token 로그인을 통해 정합성 이슈 완화와 서버 부담 감소

![image](https://user-images.githubusercontent.com/73544708/195256157-80e0aa88-0aa1-48ee-8979-2dc48db9f94b.png)

- 세션 방식으로 로그인을 처리했을 때, 서버가 증설이 되면 **정합성이 문제가 발생**
- 해결하기 위해선 Session Storage를 따로 만들어줘야 하는데, **요청에 대한 성능 감소**
- 토큰 방식을 채택해 **따로 스토리지를 찔러볼 필요 없이**, 클라이언트가 토큰을 넘겨주면 **서버가 가지고 있는 Key 값을 가지고 인증과 인가가 처리가 가능**해진다.
