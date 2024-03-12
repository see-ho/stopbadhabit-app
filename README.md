![image](https://github.com/see-ho/StopBadHabit/assets/107612802/dbfe2e3e-455b-498b-8c81-bba3d0dd27ed)

# 💡 주제

**보이지 않는 버릇이라는 개념**을 접근성 좋은 모바일 플랫폼을 통해 **시각화**하여 주기적으로 인지할 수 있게 해 교정에 대한 도움을 제공하는 것이 목표인 Android App

<br/>

# 🔗 Link

**Source - 깃허브**

[GitHub - see-ho/StopBadHabit](https://github.com/see-ho/StopBadHabit)

**Download - 플레이스토어**

[StopBadHabit - Apps on Google Play](https://play.google.com/store/apps/details?id=com.seeho.stopbadhabit)

<br/>

# ✋ 맡은 파트

- 전체 기획(개인 프로젝트)
- **안드로이드 앱 전체 디자인 및 개발**
    - 버릇 등록
    - 버릇 상세 조회
    - 반성일기 작성
    - 버릇 히스토리 조회
    - 결과 리포트 조회

<br/>

# 🛠 사용 기술

|  |  |
| --- | --- |
| Language | Kotlin |
| Architecture | MVVM |
| DI | DaggerHilt |
| Asynchronous | Coroutines |
| JetPack | LiveData, ViewModel, Navigation, ViewBinding |
| Local DB | Room |
| Image | Glide, Lottie |
| Collaboration | Notioin |
| Design Tool | Figma, Vectornator |

<br/>


# 🗂 프로젝트 구조

- 🗂 App
    - 📁 DI
    - 📁 data
        - 📁 model
            - 📁 Habit
            - 📁 HabitAndDiary
            - 📁 PresentHabiy
            - 📁 repository
    - 📁 UI
        - 📁 adapater
        - 📁 viewmodel
    - 📁 util
<br/>


# ⭐️ 주요 기능

### 버릇 등록
  
- 버릇을 교정하는 과정을 몬스터를 물리치는 것에 비유하여 각 난이도와 목표 날짜에 맞게 몬스터의 생김새와 목표 라이프를 다르게 설정하였습니다.

<img src="https://github.com/see-ho/StopBadHabit/assets/107612802/e091a1f2-3b37-460e-890c-531f64b39c93" align="center" width="32%">  

### 반성일기의 작성 및 조회, 결과 리포트

- 버릇을 등록한 후 세부 사항의 조회와 반성일기의 작성 및 조회가 가능합니다.
- 목표 일이 지나면 결과에 따른 리포트를 조회할 수 있습니다.

<p align="center">  
  <img src="https://github.com/see-ho/StopBadHabit/assets/107612802/442ec387-24d9-4d7c-80da-79f41f736266" align="center" width="32%">  
  <img src="https://github.com/see-ho/StopBadHabit/assets/107612802/b31a148e-4cb3-4fbe-8660-19a0c94c92ef" align="center" width="32%">  
  <img src="https://github.com/see-ho/StopBadHabit/assets/107612802/2a190cbd-508d-4958-8eb6-c6deb5c8f574" align="center" width="32%"> 
</p>

### 히스토리 페이지


- 히스토리 페이지에서 역대 도전 기록을 조회할 수 있습니다.
- 몬스터 난이도, 기간, 라이프, 작성했던 반성일기를 조회할 수 있습니다.

<img src="https://github.com/see-ho/StopBadHabit/assets/107612802/b09653ba-50e7-4069-805c-e05f08f8b386" align="center" width="32%">  

<br/>

# 🤔 어려웠던 점 & 배운 점

### 💡 MVVM 패턴 + Repository 패턴 적용


- 기존에 Activity나 Fragment에 작성된 코드를 MVVM 아키텍처의 ViewModel로 옮겨서, UI 로직과 비즈니스 로직을 명확하게 분리하였습니다. 이로써 UI 컴포넌트는 간결해지고 유지보수가 용이해졌습니다.
- Repository 패턴을 도입하여 데이터의 원본(로컬 DB)을 추상화하고, ViewModel은 Repository를 통해 데이터를 가져오도록 설계하였습니다. 이로써 데이터 소스를 쉽게 교체하거나 테스트하기 용이한 구조를 구축하였습니다.
<br/>

### 💡 Room 데이터베이스를 활용한 로컬 데이터 관리


- 이 프로젝트는 안드로이드 앱 개발 경험을 향상시키기 위해 기존에 연습으로 사용해보았던 Room 라이브러리를 활용하여 로컬 데이터베이스에 데이터를 저장하고 읽어오는 작업을 주요 목표로 하였습니다.
- Room은 안드로이드 앱에서 SQLite 데이터베이스를 더 쉽게 다룰 수 있게 해주는 라이브러리로, 데이터베이스 스키마를 정의하고 데이터를 관리하는데 큰 도움이 되었습니다.
<br/>

### 💡 LiveData와 Observer 적절히 사용하기


- LiveData를 사용하여 데이터가 변경될 때마다 UI가 자동으로 업데이트되도록 구현하였습니다. 이로 인해 사용자는 언제든지 최신 정보를 확인할 수 있습니다.
- LiveData를 활용하면 데이터 변경을 감지하고 UI 업데이트를 처리하는 데 필요한 별도의 코드 작성이 필요하지 않습니다. 이러한 과정을 통해 코드의 간결성과 유지보수성을 높였습니다.
<br/>

### 💡 철저한 문서화와 계획 수립, 이행의 중요성


- 개인 프로젝트의 진행중에 문서화가 미흡하여 기능의 세부사항의 변동이 잦았던 경험을 통해 개발자는 팀프로젝트나 실무에 있어 개발 능력뿐만 아니라 체계적인 문서화 능력도 중요하다는 것을 깨달았습니다.
- 탄탄한 설계는 프로젝트의 기반이 되므로 잘 수립하는 것이 중요하다는 것을 깨달았습니다.
- 일정 산출에 대한 이해도가 부족하여 프로젝트 기간이 예상보다 길어졌음. 일정산출에 대한
중요성을 깨닫는 계기가 되었습니다.
