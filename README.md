# springPictureUploader

사진 업로드기능이 추가된 웹 페이지입니다.
심심할 것 같아서 이미지 classification 기능을 추가했습니다.
(https://github.com/amitrajitbose/cat-v-dog-classifier-pytorch) 참고

![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/52907198/190114870-1f77e706-d5fb-482b-a3e8-67689433648d.gif)

시도한 것
1. 썸네일 만들어서 표시
2. 파일 중복 체크 꼭 해야댐 => uuid+ id , 날짜, https://doublesprogramming.tistory.com/128 완
3. 하다보니까 서버를 내려야 이미지가 업로드댐 => 해결법, static폴더로 이미지를 저장하여 업로드 해서 단순이 이미지를 뛰울수 없다. https://toshi15shkim.github.io/articles/2019-09/spring-resources-location /해결완
4. null, 등 예외처리 
5. 부트스트랩
6. 페이징기능 /https://wikidocs.net/161873 
