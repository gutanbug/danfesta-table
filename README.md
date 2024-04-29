# 단페스타-테이블
단페스타 테이블 관리 서비스를 위한 레포지토리입니다.
<hr>

## 유저(/user)

<aside>
💡 엔티티 테이블
1. 이름
2. 전화번호
3. 성별
4. 로그인 ID
5. 유저 Role

</aside>

- 로그인 : OAuth (카카오, 네이버) 이용 → 접근성 용이

## 매칭테이블(/table)

<aside>
💡 엔티티 테이블
1. 테이블 번호
2. 하트 수
3. List<유저>
4. List<상품교환>

</aside>

- 테이블 정보 조회 : 총 하트 수, 상품 교환 기록, 같은 테이블 사용자 정보 및 총 인원수
- 테이블 입장
    - QR 코드로 각 테이블에 접근할 수 있도록 (토큰 필요)
    - 테이블 방은 그때마다 생성되는 로직
    - 인원 수 제한은 두지 않을 것
    - 기본 하트 수는 2로 고정
- 테이블로 하트와 메시지 보내기
    - 하트 수는 제한 없이 둘 예정
    - 메시지는 SMS로 할지 / 웹 푸시알림 으로 구현할지 미정
- 하트 구매
    - KG 이니시스 API를 가져와서 구현할 예정
    - 실제 거래 서비스이다 보니 테스트 코드 확실하게 설정

## 상품교환요청

<aside>
💡 엔티티 테이블
1. 상품 이름
2. 상품 정보
3. 상품 수량
4. 하트 개수
5. 요청 상태(ENUM)
6. List<매칭테이블>

</aside>

- 상품 교환 요청
    - 자신의 테이블에 있는 하트 수를 가지고 상품 교환을 요청할 수 있음
    - 상품 요청 시 관리자 페이지에서 승인 / 취소를 해줄 수 있음
    - 하트 감소는 승인 후 변경
- 상품 전체 조회
    - 상품 정보를 리스트로 보여줌

## 관리자 페이지

- 상품 교환 승인 및 취소
- 현재 테이블 초기화
- 전체 테이블 대시보드 ( 총 인원수, 하트 개수 )
- 상품 관리 페이지 (추가, 삭제, 수정)
