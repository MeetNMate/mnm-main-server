# Meet & Mate Main Server

## Conventions

협업규약을 지켜주세요.

## contributors
<a href="https://github.com/MeetNMate/mnm-main-server/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=MeetNMate/mnm-main-server" />
</a>

### Branch Convention

현재 보호되고 있는 브랜치는 develop과 master 이며, master는 배포용입니다.

브랜치는 다음과 같이 명명합니다.

- feature/작업날짜6자리-기능명-본인식별문자열
- 예) feature/210901-add-edit-house-song

### Commit Convention

- feat : 새로운 기능 추가
- fix : 버그 수정
- docs : 문서 수정
- style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- refactor : 코드 리펙토링
- test : 테스트 코드, 리펙토링 테스트 코드 추가
- chore : 빌드 업무 수정, 패키지 매니저 수정

### PR Convention

- approve는 1명 이상.

### Code Convention

#### Controller

- *** List() : 목록 조회의 경우
- *** Details() : 특정 데이터의 조회의 경우
- *** Save() : CUD 가 동시에 일어나는 경우
- *** Add() : 등록만 하는 경우
- *** Modify() : 수정만 하는 경우
- *** Remove() : 삭제만 하는 경우

컨트롤러에서는 Service의 호출과 Exception 처리만 이루어져야 합니다.

#### Service

- find ***() : 조회 유형일 경우
- add ***() : 등록 유형일 경우
- modify ***() : 변경 유형일 경우
- remove ***() : 삭제 유혈일 경우
- save ***() CRD 가 동시에 일어나는 경우

도메인명의 Service의 생성을 피해야합니다.

#### Mapper

- select ***() : 조회 유형일 경우
- insert ***() : 등록 유형일 경우
- update ***() : 변경 유형일 경우
- delete ***() : 삭제 유혈일 경우

#### DTO

- https://tecoble.techcourse.co.kr/post/2020-09-20-validation-in-spring-boot/
