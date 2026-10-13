# HowAreYou

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.x-blue.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%25%20Compose-orange.svg)](https://developer.android.com/jetpack/compose)
[![Hilt](https://img.shields.io/badge/DI-Hilt-yellow.svg)](https://dagger.dev/hilt/)
[![Firebase](https://img.shields.io/badge/Backend-Firebase-orange.svg)](https://firebase.google.com)

**HowAreYou** is a modern Android application built with Kotlin and Jetpack Compose, designed to allow users to share their status, check in with others, and interact in real time using Firebase Realtime Database.

App stage of production - **MVP**.

---

## ✨ Features

- **Real-Time Status Sharing**: Post updates on how you are doing and view posts from others instantly.
- **Interactive UI**: Clean, modern interface built entirely with Jetpack Compose and Material 3 design principles.
- **Floating Action & Bottom Sheet**: Intuitive content creation and interaction flows.
- **Reactive Architecture**: Powered by Kotlin Coroutines, StateFlow, and modern Android lifecycle components.

---

## 🛠️ Tech Stack & Architecture

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with [Material 3](https://m3.material.io).
- **Architecture**: Clean Architecture principles separating concerns into `data`, `domain`, and `ui` layers following MVVM pattern.
- **Dependency Injection**: [Hilt (Dagger)](https://dagger.dev/hilt/) for robust, testable dependency management.
- **Backend**: [Firebase Realtime Database](https://firebase.google.com/docs/database) for persistent, real-time data synchronization.
- **Local Storage**: SharedPreferences for local configuration/state.
- **Utilities**: ThreeTenABP for robust date and time handling and support 15 Android API level.

---

## 📁 Project Structure

```tree
app/src/main/java/pl/jawegiel/howareyou/
├── core/
│   ├── di/                 # Hilt Modules (Firebase, Repository, SharedPreferences)
├── data/
│   ├── datasource/         # Remote and local data sources (Firebase & SharedPreferences)
│   ├── model/              # Data models (Post, Interaction)
│   └── repository/         # Repository implementations
├── domain/
│   └── repository/         # Repository contracts / interfaces
├── ui/
│   ├── main/               # Main activity, ViewModel, components, and screens
│   └── theme/              # Material 3 theme, colors, and typography
└── App.kt                  # Application class annotated with @HiltAndroidApp
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** (Koala / Jellyfish or newer recommended)
- **JDK 11** or higher
- **Android SDK** (Compile SDK 37, Min SDK 24)
- A [Firebase Project](https://console.firebase.google.com/) with Realtime Database enabled.

### Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/HowAreYou.git
   ```
2. **Open in Android Studio**:
   Open the cloned project folder in Android Studio.
3. **Configure Firebase**:
   - Add your Android app package (`pl.jawegiel.howareyou`) to your Firebase Console project.
   - Download the `google-services.json` file.
   - Place `google-services.json` inside the `app/` directory (`app/google-services.json`).
4. **Sync Project with Gradle Files**:
   Let Android Studio download dependencies and sync Gradle.
5. **Run the App**:
   Connect an Android device or start an emulator and click **Run** (`Shift + F10`).

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
