# ReadySetGo
Aplikacja - System Rezerwacji Usług Sportowych

> *TBD*

*TBD*

**Odznaki**:

![Platform](https://img.shields.io/badge/platform-Android-green)
![Kotlin](https://img.shields.io/badge/Kotlin-100%25-blue)

---

## Klasy dla branchy

**Sposób tworzenia brancha:**

`prefix`:`{Opis problemu}`

| Prefix       | Opis                                                                       |
|--------------|----------------------------------------------------------------------------|
| **feat**     | Nowa funkcjonalność (np. dodanie rezerwacji).                              |
| **fix**      | Naprawa błędu.                                                             |
| **docs**     | Zmiany w dokumentacji (readme, javadoc).                                   |
| **style**    | Zmiany formatowania, brakujące średniki, itd. (nie wpływa na logikę kodu). |
| **refactor** | Zmiana kodu, która ani nie naprawia błędu, ani nie dodaje funkcji.                                                                          |
| **chore**    | Zmiany w procesie budowania, narzędziach pomocniczych.                       |


---

## Styl dla PR'ów

| Typ        | Issue          | Podsumowanie  |
|------------|----------------|---------------|
| **Prefix** | *Issue z Jiry* | *Krótki opis* |

**Przykład:**

| Typ      | Issue    | Podsumowanie                             |
|----------|----------|------------------------------------------|
| **Docs** | (EX.123) | Zmiana dokumentacji, dodano nowy wygląd. |

---

## System rezerwacji usług sportowo rekreacyjnych

| Funkcjonalności           | Opis                                                 |
|---------------------------|------------------------------------------------------|
| **Autentykacja**          | Rejestracja i logowanie                              |
| **Przegląd Zajęć**        | Przeglądaj dostępne zajęcia                          |
| **Rezerwacja terminów**   | Rezerwój terminy zajęć                               |
| **Anulowanie**            | Anuluj swoje rezerwacje                              |
| **Panel administratora**  | Panel Administratora posiadający dostęp do strony    |

---

## Architektura

Struktura docelowa typu *MVVM* dla plików projektu:

```
ReadySetGo/
├── backend/                          # Pure Java/Kotlin backend (REST API)
│   ├── src/
│   │   └── main/
│   │       ├── java/com/ReadySetGo/backend/
│   │       │   ├── config/           # DB config, connection pool
│   │       │   ├── controller/       # REST endpoints (Ktor / Spring Boot)
│   │       │   ├── repository/       # JDBC queries live here
│   │       │   ├── model/            # Data classes / entities
│   │       │   └── service/          # Business logic
│   │       └── resources/
│   │           └── application.yml   # DB URL, credentials
│   ├── docker/
│   │   ├── docker-compose.yml        # Spins up Postgres/MySQL
│   │   └── init.sql                  # Schema seed
│   └── build.gradle.kts
│
├── frontend/                         # Android App (MVVM)
│   ├── src/
│   │   └── main/
│   │       ├── java/com/ReadySetGo/frontend/
│   │       │   ├── data/
│   │       │   │   ├── remote/       # Retrofit API service interfaces
│   │       │   │   ├── repository/   # Repository pattern (bridge VM ↔ API)
│   │       │   │   └── model/        # DTOs / response models
│   │       │   ├── ui/
│   │       │   │   ├── home/
│   │       │   │   │   ├── HomeFragment.kt      # View
│   │       │   │   │   └── HomeViewModel.kt     # ViewModel
│   │       │   │   ├── detail/
│   │       │   │   │   ├── DetailFragment.kt
│   │       │   │   │   └── DetailViewModel.kt
│   │       │   │   └── MainActivity.kt
│   │       │   ├── di/               # Dependency injection (Hilt modules)
│   │       │   └── utils/            # Extensions, constants
│   │       ├── res/
│   │       │   ├── layout/           # XML layouts per screen
│   │       │   ├── navigation/       # NavGraph XML
│   │       │   └── values/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
│
├── shared/                           # Shared models between front & back
│   └── src/main/kotlin/com/ReadySetGo/shared/
│       └── dto/                      # Shared data transfer objects
│
├── settings.gradle.kts
└── build.gradle.kts                  # Root build config
```

```
Layers TBD
```

---

## Technologie

| Technologie    | Zastosowanie                 |
|----------------|------------------------------|
| **Kotlin**     | *TBD*                        |
| **Java**       | *TBD*                        |
| **PostgreSQL** | Baza danych dla projektu     |
| **GitHub**     | Główne repozytorium porjektu |
| **Jira**       | Organizacja pracy zespołu    |

---

## Używanie aplikacji

### Wymagania

- Android Studio Hedgehog (2023.1.1) or later
- JDK 21
- A Firebase account (free Spark plan works)

### 1. Sklonuj repozytorium

```bash
git clone https://github.com/szajam/ReadySetGo.git
cd ReadySetGo
```

### 2. Wystartuj aplikacje

1. Open in Android Studio: **File → Open** → select `ReadySetGo/`
2. Wait for Gradle sync
3. Connect a device or start an emulator (API 26+)
4. Click **Run ▶️** or press `Shift+F10`

---

## PostgreSQL Struktura danych

```
TBD
```

---

## Design System

| Token | Color | Usage |
|-------|-------|-------|
| *TBD* | *TBD* | *TBD* |


---

## Roadmap

**In Jira**

---

## MVP

**Minimalna wersja systemu obejmuje:**
- Konto użytkownika
- Rezerwację zajęć
- Połączenie z bazą danych
- Podstawowy panel admina


---