# Little Lemon 🍋

![Language](https://img.shields.io/badge/Language-Kotlin-purple) ![UI Toolkit](https://img.shields.io/badge/UI-Jetpack%20Compose-green) ![Database](https://img.shields.io/badge/Database-Room-blue) ![Networking](https://img.shields.io/badge/Networking-Ktor-red)

A native Android application developed as the **Final Capstone Project** for the Meta Android Developer Professional Certificate.

## 📱 App Screenshots

| Onboarding | Home (Hero) | Search & Results |
|:---:|:---:|:---:|
| <img src="onboarding.png" width="200"/> | <img src="home-hero.png" width="200"/> | <img src="search-results.png" width="200"/> |

| Filter: Starters | Filter: Mains | Profile Screen |
|:---:|:---:|:---:|
| <img src="filter-starters.png" width="200"/> | <img src="filter-mains.png" width="200"/> | <img src="profile.png" width="200"/> |

---

## 🏗 Architecture
The app follows the **MVVM (Model-View-ViewModel)** pattern:

1. **Model:** Data entities for `MenuItem` (Room) and JSON response objects.
2. **ViewModel:** Manages UI state and filter logic.
3. **View:** Modularized Compose functions.

Developed by **Baran Cenk Dinçsoy**
