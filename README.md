
[Memorious 바로가기](https://naver.com/)

<div align="center">

# Memorious - 우리 가족만을 위한 플랫폼
  <img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Front/assets/133538833/0b8a28b9-7d2f-4732-90d8-ebcd9e0de5da" width="40%">

  #### 코리아IT아카데미(부산) 
  #### 'AWS 기반 공공ㆍ빅데이터 활용 웹서비스 개발자 양성과정(2회차)'
  #### 최종 프로젝트 : 4조
</div>


## 목차
- [프로젝트 소개](프로젝트-소개)
- [팀 소개](팀-소개)
- [개발환경](개발-환경)
- [협업 방식](협업-방식)
- [팀 컨벤션](팀-컨벤션)
- [주요기능 설명](주요-기능-설명)
- [API 명세서](문서)
- [화면 구현 및 코드리뷰](화면-구현-및-코드리뷰)
- [느낀 점](느낀-점)


## 팀 소개
### 팀원 소개
| 주성광 | 우주영 | 한유정 |
| :---: | :---: | :---: |
| <img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/829c4522-360c-4c28-90b1-718c0d5fd3ab" width= 150px> | <img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/64acc27a-2f60-46de-a3d0-4a508d8bd2b5" width= 150px> | <img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/5d75c485-b451-4d8b-96a1-4ea88781ed5e" width= 150px> |
| [🔗Github](https://github.com/SeongGwangJu) | [🔗Github](https://github.com/JuyoungWoo) | [🔗Github](https://github.com/yoodeve) |



### 역할 분담
#### 주성광
- 가족 일정 공유 캘린더 기능 구현
	- 캘린더 조회
	- 일정 추가/조회/수정/삭제
- 가족 초대 기능 구현
- 프로필 사진 변경 기능 구현
- ERD 설계도 제작
- PPT 제작 및 발표
 
#### 우주영
- 네이버, 카카오 소셜 로그인 / 회원가입 기능 구현
- 가족페이지 생성 기능 구현
- 게시판 기능 구현
	- 전체 글 조회 및 검색
	- 상세 글 조회
	- 글 작성/수정/삭제
- JWT, Security 관리
- DB 설계 및 관리

#### 한유정
- 라우팅 설정
- 공통 UI, 사이드바 UI 구현
- 가족 건강 차트
- 메모 CRUD
- ESLint, Prettier 설정

#### 공동 작업

- 요구사항명세서 및 화면 정의서
- API 설계서 작성
- PPT 제작 및 발표

## 협업 방식
### 브랜치 전략 : Github-Flow


### 협업 툴
- 노션: 각종 요구사항 관련 문서, 회의록 등의 문서화를 통한 팀원들과 프로젝트 정보를 실시간으로 공유 및 기록하고, 프로젝트의 전반적인 체계를 잡아갈 수 있었습니다.

- Figma: 구현하기에 앞서 필요한 웹사이트의 화면 UI를 효율적으로 디자인할 수 있었습니다.


## 팀 컨벤션

### 커밋 컨벤션

```
- Feat: 새로운 기능에 대한 커밋
- Fix: 버그 수정에 대한 커밋
- Build: 빌드 관련 파일 수정에 대한 커밋
- Docs: 도큐먼트 수정에 대한 커밋(예: readme.md, json 파일 등 수정/ 문서 관련 라이브러리 설치 등)
- Chore: 그 외 자잘한 수정에 대한 커밋
- Comment: 필요한 주석 추가 및 변경
- Style: 코드 문법 또는 포멧 수정에 대한 커밋
- Refactor: 코드 리팩토링에 대한 커밋
- Test: 테스트 코드 수정에 대한 커밋
- Template: 라이브러리, 노드 수정에 대한 커밋
```


### 코드 컨벤션
([https://github.com/nailedReact/bokgungom-market](https://github.com/nailedReact/bokgungom-market)를 참고하였습니다.)

- 리액트 코딩에 주로 쓰이는 컨벤션을 참고하여 저희 조만의 코드 컨벤션을 만들었습니다.
- 문자열 처리 시 쌍따옴표/홑따옴표의 사용, 혹은 문장 끝 세미콜론의 사용여부와 같은 개인적 취향이 반영될 수 있는 항목들의 경우에는 사전 설문을 통해 다수결에 따라 지정했습니다.
- **컴포넌트로 분리된 파일은 PascalCase**으로 작성합니다.
- 컴포넌트가 아닌 파일, **함수명, 변수명은 camelCase**로 작성합니다.
- 다른 스타일 시트 파일(Emotion)은, **style.js**를 붙여주고, **앞글자는 소문자**로 합니다. (확장자는 .js)
- 만약 변수에 할당되는 값이 boolean인 경우에는 is를 접두사로 붙입니다.
- 상수는 대문자로만 작성합니다. (예시: FINAL_CONSTANT)
- 컴포넌트 파일 내 **import 순서는 모듈 → 컴포넌트 → 스타일컴포넌트 순**으로 합니다.
- 문자열을 처리할 때는 쌍따옴표를 사용하도록 합니다.
- 문장이 종료될 때는 세미콜론을 붙여줍니다.
- 가독성을 위해 한 줄에 하나의 문장만 작성합니다.(한줄 300자)
- 주석은 설명하려는 구문에 맞춰 들여쓰기 합니다.
- 연산자 사이에는 공백을 추가하여 가독성을 높입니다.
- 콤마 다음에 값이 올 경우 공백을 추가하여 가독성을 높입니다.
- 사용하지 않는 변수는 제거합니다.
- 객체값은 비구조화 할당으로 사용합니다.
- 코드 파일의 마지막 줄은 공백으로 둡니다.

#### ESLint, Prettier
- 정해진 규칙에 따라 자동적으로 코드 스타일을 정리해 코드의 일관성을 유지하고자 했습니다.
- 코드 품질 관리는 eslint에, 코드 포맷팅은 prettier에 일임해 사용했습니다.
- Airbnb의 코딩 컨벤션을 참고해 사용했고, 예외 규칙은 팀원들과 협의했습니다.
- 협업 시 매번 컨벤션을 신경 쓸 필요 없이 빠르게 개발하는 데에 목적을 두었습니다.

> 코드 품질 관리 <img src="https://img.shields.io/badge/Eslint-38297c?logo=eslint&logoColor=white">

```javascript
module.exports = {
	env: {
		browser: true,
		es2021: true,
		node: true,
	},
	extends: ["plugin:react/recommended", "plugin:react-hooks/recommended", "eslint:recommended", "airbnb", "prettier", "plugin:prettier/recommended", "airbnb/hooks"],
	parserOptions: {
		ecmaFeatures: {
			globalReturn: false,
		},
		ecmaVersion: "latest",
		sourceType: "module",
	},
	ignorePatterns: ["public", "config", "node_modules", "build", "dist"],
	plugins: ["jsx-a11y", "react-hooks", "react"],
	rules: {
		"react/no-unknown-property": ["error",  { ignore: ["css"] }],
		"import/extensions": [
			"error",
			"ignorePackages",
			{
				"": "never",
				js: "never",
				jsx: "never",
				ts: "never",
				tsx: "never",
			},
		],
		"react/jsx-filename-extension": ["error", { extensions: [".js", ".jsx"] }],
		"prettier/prettier": [
			"error",
			{
				singleQuote: false,
				endOfLine: "auto",
				useTabs: false,
			},
		],
		"no-unused-vars": ["error", { vars: "all", args: "after-used", ignoreRestSiblings: false  }],
		eqeqeq: ["error", "always", { null: "ignore"  }],
		"import/prefer-default-export": "off",
		"import/no-unresolved": "off",
		"jsx-a11y/label-has-associated-control": "off",
		"jsx-a11y/anchor-is-valid": "off",
		"no-console": "off",
		"react/react-in-jsx-scope": "off"
		"no-underscore-dangle": "off",
		"react/forbid-prop-types": "off",
		"react/jsx-one-expression-per-line": "off",
		"react/jsx-props-no-spreading": "off",
		"object-curly-newline": "off",
		"linebreak-style": "off",
		"no-param-reassign": "off",
		"no-multiple-empty-lines": "off",
		"react/jsx-indent": "off",
		"arrow-parens": "off",
		"react-hooks/rules-of-hooks": "error",
		"react-hooks/exhaustive-deps": "off",
		"react/jsx-no-useless-fragment": "off",
		"jsx-a11y/no-static-element-interactions": "off",
		"jsx-a11y/click-events-have-key-events": "off",
		"react/function-component-definition": "off",
		"react/button-has-type": "off",
		"operator-assignment": "off",
		"no-plusplus": "off",
		"react/require-default-props": "off",
		"react/prop-types": "off",
		"react/no-this-in-sfc": "off",
		"react/destructuring-assignment": [0, "always"],
		"consistent-return": "off",
		"import/no-extraneous-dependencies": "off",
	},
};

```


> 코드 포맷팅  <img src="https://img.shields.io/badge/Prettier-8c6414?logo=prettier&logoColor=white">

```javascript
{
	"printWidth": 300, // 줄 바꿈 시 폭 길이
	"tabWidth": 4, // 탭 너비
	"useTabs": false, // 탭 사용 여부
	"semi": true, // 세미콜론 사용 여부
	"trailingComma": "all", // 여러 줄을 사용할 때, 후행 콤마 사용 방식
	"arrowParens": "avoid" // 화살표 함수 괄호 사용 여부
}
```



 ## 요구사항명세서 및 화면 정의서
 #### [요구사항명세서 바로가기](https://platinum-infinity-b08.notion.site/1-77e4fdb543504afd90accdc80f808117?pvs=4)
 #### [화면 정의서 바로가기](https://www.figma.com/community/file/1317744980901381622/memorious-team-library)
## 기능 설명 및 코드리뷰
### 소셜 로그인인
<div>
	<img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Front/assets/133538833/a1d7d556-e790-4439-954e-64ff997bcc59" width=60%>
</div>

- 네이버, 카카오를 통한 회원가입을 지원합니다.

  <details>
    <summary>Code Review</summary>
    
	 #### Front-End
	```javascript
      const fetchData = async () => {
      };
    ```
   (코드 설명)

	#### Back-End
	```java
    public class BootSpringBootApplication {
      public static void main(String[] args) {
        System.out.println("Sample");
      }
    }
    ```
    (코드 설명)
  </details>


### 소셜 로그인 / 회원가입
<div>
     <img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Front/assets/133538833/a1d7d556-e790-4439-954e-64ff997bcc59" width=80%>
</div>

   - 사용자가 로그인이 되어있지 않을 시 표시되는 로그인 화면입니다.
   - 네이버, 카카오를 통한 로그인을 지원합니다. 
 
<div>
  <img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/5cefc769-d3f0-4df5-add1-11e41e4580b2" width=80%>
</div>

- 소셜 계정으로 로그인 시도했을 시 회원가입이 되어있지 않을 경우 추가 입력(회원가입) 화면으로 이동합니다.

<details>
	<summary>Code Review</summary>

#### Front-End
```javascript
	const fetchData = async () => {
	};
```
(코드 설명)

#### Back-End

```java
   public class BootSpringBootApplication {
     public static void main(String[] args) {
       System.out.println("Sample");
     }
   }
```
(코드 설명)
</details>



### 가족 페이지 생성
<div>
	<img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/550dd4de-29be-4f18-93e0-99844ac3b574" width=80%>
</div>

- 가족 구성원과의 추억과 일정 등을 기록할 우리 가족만의 페이지를 만드는 기능입니다.
- 로그인 후 소속된 가족이 없을 경우 다음 화면이 표시됩니다.

  <details>
    <summary>Code Review</summary>
    
	 #### Front-End
	```javascript
      const fetchData = async () => {
      };
    ```
   (코드 설명)

	#### Back-End
	```java
    public class BootSpringBootApplication {
      public static void main(String[] args) {
        System.out.println("Sample");
      }
    }
    ```
    (코드 설명)
  </details>

### 게시판
<div>
	<img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/5ef99699-3497-4486-8bcf-bfa0c66f7528" width=70%>
	<img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/2d9e78ad-f5c5-4708-839c-9199dca303ad" width=70%>
	<img src="https://github.com/KoreaIt-J-23-2-5/Memorious-Back/assets/97303815/7145c35a-dff7-4b0c-a782-8efa6fed3a53" width=70%>

</div>

- 가족 구성원이 회의록, 식단, 건의사항 등 카테고리를 지정하고 사진과 글을 포스팅할 수 있는 기능입니다.
- 전체 글 목록이 게시판 형태로 표시됩니다.
- 카테고리별로 게시글을 조회할 수 있고, 또한 검색 범위와 입력한 검색어에 따른 검색이 가능합니다.

  <details>
    <summary>Code Review</summary>
    
	 #### Front-End
	```javascript
      const fetchData = async () => {
      };
    ```
   (코드 설명)

	#### Back-End
	```java
    public class BootSpringBootApplication {
      public static void main(String[] args) {
        System.out.println("Sample");
      }
    }
    ```
    (코드 설명)
  </details>


### 가족 초대
- 가족의 이메일을 입력해 가족을 초대할 수 있으며, 입력한 이메일로 초대 메일이 전송됩니다.
- 초대를 받은 회원은 가족이 입력한 이메일로 회원가입 시 초대가 완료됩니다.

### 캘린더
<!-- 조회부 GIF -->
- 한 달 단위로 가족이 추가한 일정을 조회할 수 있으며 각각의 일정은 알고리즘이 정한 순서에따라 보기좋게 배치됩니다.
- '오늘' '다음 월' '이전 월' 버튼과 연도 dropdown을 통해 원하는 월로 간편하게 이동할 수 있습니다.
-  일정이 많아 모든 일정을 보여주지 못할 경우, 'n개 더보기'를 클릭해 모달창에서 모든 일정을 확인 할 수 있습니다.
<!-- 추가 GIF -->
- '일정 추가' 버튼 또는 일정 셀을 클릭해 일정을 추가할 수 있습니다.
- 반복 주기(5가지)와 종료일 또는 횟수를 직접 정하여 반복되는 일정을 추가할 수 있습니다.
<!-- 상세조회 / 수정 / 삭제 GIF -->
- 조회된 일정을 클릭하면 나오는 모달창을 통해 세부 내용을 조회하고, 수정 또는 삭제할 수 있습니다.
  
  <details>
    <summary>Code Review</summary> 
    
    ```javascript
      const fetchData = async () => {
      };
    ```
    
    {코드 설명...}
  </details>
  
## API 명세서
#### [🔗API 명세서 바로 가기](https://platinum-infinity-b08.notion.site/3-API-888f25b818f24f9abfce0d1f1c274c8b?pvs=4)

## 느낀 점
#### 우주영
- 프로젝트를 진행하면서 문서 작업을 통한 프로젝트의 체계화, Git을 통한 형상 관리, 코드 리뷰 등을 통해 협업에 있어 좋은 경험이 되었습니다.
- 수업을 들었을 땐 잘 이해가 되지 않았던 JWT, Security, OAuth2 소셜 로그인 관련 기능을 직접 맡아 구현해보면서 이해도를 높이는데 큰 도움이 되었습니다.
- 외부 라이브러리의 도큐먼트를 찾아보며 우리 프로젝트에 적용시키는 부분에서 어려움이 많았는데, 오래 걸리더라도 직접 찾아서 적용해보면서 스스로 문제를 해결하는 능력을 기를 수 있어 좋았습니다.
- 팀원 간 견해 차이로 인한 팀 분위기 상 소통이 부족했음을 인지하였고, 소통의 중요성을 깨달았습니다. 다음 팀 프로젝트에서는 더욱 적극적으로 소통하기 위해 팀원 모두의 합의점을 찾아가는 등의 노력이 필요함을 느꼈습니다.
