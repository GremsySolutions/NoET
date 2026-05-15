# NoET 📚✨

NoET is an Android application built with **Jetpack Compose + MVVM Architecture** to help users learn English vocabulary and translate English text into Vietnamese using AI.

The application supports:

* Vocabulary translation
* Paragraph translation
* OCR image scanning
* AI-powered translation
* Local data storage
* Translation history management

---

## ✨ Features

### 📖 Vocabulary Translation

* Translate English vocabulary into Vietnamese
* Get:

  * Vietnamese meaning
  * Part of speech
  * Pronunciation
  * Example sentence in English
  * Vietnamese example translation
* Save favorite vocabulary

---

### 📝 Paragraph Translation

* Translate English paragraphs into Vietnamese
* Save translation history
* Organize paragraphs by category

---

### 📷 OCR Image Scan

* Capture or upload images
* Extract text using **ML Kit OCR**
* Automatically translate extracted English text into Vietnamese using AI

---

### 🗂 Category Management

* Organize vocabulary and paragraphs by category:

  * Work
  * Study
  * Travel
  * School
  * etc.

---

### 🕘 History

* Save translation history locally using Room Database
* View previously translated vocabulary and paragraphs

---

## 🏗 Tech Stack

### Android

* Kotlin
* Jetpack Compose
* MVVM Architecture
* Navigation Compose

### Local Database

* Room Database

### Dependency Injection

* Hilt

### Networking

* Retrofit
* OkHttp

### AI & OCR

* OpenAI API
* ML Kit Text Recognition

### Async

* Kotlin Coroutines

---

## 📂 Project Architecture

```txt
data
domain
presentation
di
```

Architecture follows:

* Clean MVVM
* Repository Pattern
* UseCase Layer separation

---

## 📱 Screens

* Home
* Category
* Vocabulary Detail
* Paragraph Translation
* OCR Scan
* History

---

## 🚀 Future Improvements

* Dark mode
* Offline translation cache
* Speech pronunciation
* Flashcard learning
* Quiz/Test mode
* Firebase sync
* Multi-language support

---

## 👩‍💻 Developer

Developed by:
**Pham Thi Cam Hong**

---

## 📌 Note

This project is built for:

* Learning Android modern development
* Practicing MVVM Architecture
* Applying AI APIs in mobile applications
* Portfolio & academic purposes
