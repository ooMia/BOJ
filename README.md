# GitHub Pages

### 1. [백준허브(BaekjoonHub)](https://github.com/BaekjoonHub/BaekjoonHub)와 연동된 레포 준비

### 2. GitHub Actions 생성
- [.github/workflows/gh-pages-deploy.yaml](.github/workflows/gh-pages-deploy.yaml)
  
    - 반드시 지정된 디렉토리 `.github/workflows`에 생성한다.
    - 파일명은 변경 가능하다.
    - `main` 브랜치에 대한 push가 이루어졌을 때 동작한다. 

### 3. GitHub Pages 자동 배포 설정
- 레포 > `settings` > `pages`
  
    1. ![image](https://github.com/ooMia/BOJ/assets/96914905/41e8c660-43b3-4ccc-927d-a6ee705924a0)
      
    2. ![image](https://github.com/ooMia/BOJ/assets/96914905/2d501408-6c1f-4566-b04e-fe52f55c1cfd)
- #### 기본 브랜치 설정
    - 위의 순서대로 진행했다면 `gh-pages` 브랜치가 생성되어 있을 것이다.
    - ![image](https://github.com/ooMia/BOJ/assets/96914905/6f6078c7-6ca6-4f5b-ae8b-47430efb53ff)
