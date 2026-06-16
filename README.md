# ✈️ Voyager Airlines — Android Booking System

Voyager Airlines is a modern, standalone Android mobile application designed to simulate airline ticket booking, search, and cost-optimization services. Developed using **Kotlin** and **Jetpack Compose** with a robust **MVVM** architecture, the app utilizes a local **Room Database (SQLite)** to provide a fully functioning, fast, offline experience without external API dependencies.

---

## 📱 Pre-Built APK (Ready to Run!)

For quick evaluation and testing, a fully compiled and functional debug APK is included directly inside this repository. You can download and install it on any physical Android device or emulator.

*   **Location in Repo:** [`app/build/outputs/apk/debug/app-debug.apk`](file:///c:/Users/Govardhan/Desktop/airlines/app/build/outputs/apk/debug/app-debug.apk)
*   **Compatibility:** Android 8.0 (Oreo / API Level 26) and above.
*   **Fully Functional:** Operating completely offline using local pre-populated database models, all application screens—from search to seat selection and history—are fully operational.

### How to Install:
1. Download the [`app-debug.apk`](file:///c:/Users/Govardhan/Desktop/airlines/app/build/outputs/apk/debug/app-debug.apk) file to your Android device.
2. Enable installation from unknown sources in your device settings if prompted.
3. Install and run the application instantly!

---

## ✨ Features & Functionalities

### 1. 🔐 Mock Authentication & Login Screen
*   A clean user authentication interface validating user credentials locally.
*   Allows quick bypass logic for convenient academic demonstration.

### 2. 📊 Home Dashboard Screen
*   Central hub showcasing navigation actions: flight searches, booking history, cheapest flights, and promotions.
*   Shows pre-loaded city highlights and visual shortcuts.

### 3. 🔍 Flight Search & Real-time Results
*   Enables search filters using Source City, Destination City, and Departure Dates.
*   Displays available flights sorting by price.
*   Handles search parameters dynamically matching airport codes (e.g., SFO, JFK, LHR, CDG) or city names.

### 4. 💺 Dynamic Seat Selection Screen
*   Interactive, grid-based graphical seat selector.
*   Supports seat toggling, seat limits (up to 4 seats per booking), and live passenger count updates.
*   Updates ticket fares dynamically based on seats selected.

### 5. 📉 Cheapest Flight Suggestions
*   A dedicated section filtering and highlighting low-price flight entries.
*   Perfect for simulating low-demand periods or promotions.

### 6. 🔗 Cost Optimization & Connecting Flights
*   Compares cost structures of direct flights versus connecting flights utilizing stops (e.g., stopover airport codes like DFW).
*   Visualizes intermediate layovers, total durations, and net savings.

### 7. 🎁 Offers and Cashback System
*   Integrated booking codes and cashback promotion display.
*   Promotional values applied to base price calculations.

### 8. 📜 Booking History Management
*   Local database logs persisting all historical bookings.
*   Displays flight details, booked seat numbers, date, passenger counts, and total paid fare.

---

## 🛠️ Technology Stack

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Language** | [Kotlin](https://kotlinlang.org/) | Modern, expressive language for Android development. |
| **UI Toolkit** | [Jetpack Compose](https://developer.android.com/compose) | Declarative native UI framework with Material 3 components. |
| **Architecture** | MVVM (Model-View-ViewModel) | Clean separation of business logic, database layer, and UI state. |
| **Local Storage** | [Room Database (SQLite)](https://developer.android.com/training/data-storage/room) | Database abstraction layer over SQLite for local caching and offline capabilities. |
| **Reactive Flows** | Coroutines & Flow/StateFlow | Structured concurrency for background database ops and state handling. |
| **Image Loading** | [Coil (Compose)](https://coil-kt.github.io/coil/) | Lightweight, fast image loading library. |
| **Dependency Injection** | AndroidViewModel | Context-aware ViewModels for database resource initialization. |

---

## 📂 Project Architecture & Package Structure

```
voyager-airlines/
├── .github/workflows/          # Github Actions CI/CD configuration (android.yml)
├── app/
│   ├── build.gradle.kts        # Core app dependency definitions & SDK configuration
│   └── src/main/
│       ├── AndroidManifest.xml # Core app configuration (Permissions, Activities)
│       └── java/com/voyager/airlines/
│           ├── MainActivity.kt # Entry-point launcher hosting VoyagerTheme & Navigation
│           ├── data/
│           │   ├── Entities.kt # Room Entity models (Airline, City, Flight, Booking)
│           │   ├── Daos.kt     # Room DAOs defining SQLite queries & details wrappers
│           │   ├── MockData.kt # Seed dataset (Cities, Airlines, initial Flights)
│           │   └── VoyagerDatabase.kt # Room database builder & initializer
│           ├── viewmodel/
│           │   └── VoyagerViewModel.kt # Key business flow, database state & handlers
│           └── ui/
│               ├── AppNavigation.kt    # Navigation Host controlling Route flow
│               ├── theme/      # Style guides, Colors, Typography, Shapes
│               └── screens/    # Compose views: Home, Login, Search, Seat Selection, History
└── design_assets/              # Visual assets, screenshots, and Project SRS document
```

---

## 🗄️ Database Schema & Entities

The application represents its local state using four inter-linked Room entities:

1.  **`Airline`**: Represents flight operators.
    *   `id` (String - Primary Key)
    *   `name` (String)
    *   `logoUrl` (String)
    *   `isLowCost` (Boolean)
2.  **`City`**: Defines airport hubs.
    *   `id` (String - Primary Key)
    *   `name` (String)
    *   `country` (String)
    *   `airportCode` (String)
    *   `imageUrl` (String)
3.  **`Flight`**: Tracks route plans, prices, and layovers.
    *   `id` (String - Primary Key)
    *   `airlineId` (String - Relation key)
    *   `sourceCityId` (String)
    *   `destinationCityId` (String)
    *   `departureTime` (Long)
    *   `arrivalTime` (Long)
    *   `basePrice` (Double)
    *   `isConnecting` (Boolean)
    *   `stopoverCityId` (String - Optional)
    *   `availableSeats` (Int)
4.  **`Booking`**: Holds order confirmations.
    *   `id` (String - Primary Key)
    *   `flightId` (String)
    *   `userId` (String)
    *   `passengerCount` (Int)
    *   `totalFare` (Double)
    *   `bookingTime` (Long)
    *   `seatNumbers` (String)

---

## 💻 Local Compilation & Setup

If you wish to build, inspect, or modify the source code locally, follow these steps:

### Prerequisites:
*   **Android Studio** (Hedgehog / Iguana / Jellyfish or newer recommended).
*   **JDK 17** configured in your development environment.
*   **Android SDK 34** (Compile SDK version).

### Setup Instructions:
1.  Clone this repository or extract the zip file.
2.  Open Android Studio and select **File -> Open**, then browse to the project root folder.
3.  Let Gradle sync finish loading dependencies (Room, Compose, Coil, etc.).
4.  To run the app: Connect an Android Device (with developer settings active) or run a Virtual Device (AVD Emulator).
5.  Click the **Run (Green Play Button)** in Android Studio.

---

## 📄 License & Course Metadata
*   **Course:** Mobile Application Development (Android Kotlin)
*   **Development Platform:** Android Studio
*   **Primary Language:** Kotlin
*   **Purpose:** Academic / Simulation Project
