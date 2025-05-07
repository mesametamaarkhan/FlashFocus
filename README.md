# FlashFocus 📚✨

**FlashFocus** is a mobile-first AI-powered study assistant that helps learners extract maximum value from their notes. Simply upload class materials (PDF, DOCX, or text), and FlashFocus will summarize them, generate flashcards, and create quizzes — all in one place. Perfect for students, lifelong learners, and knowledge workers!

---

## 🚀 Features

* **📄 Upload Notes**: Upload PDFs or DOCX files and extract clean, usable study text.
* **🧠 Summarization**: AI-powered summarization of your content.
* **🎿 Flashcards**: Generate Q\&A flashcards for review, complete with flip animations.
* **📝 Quizzes**: Create multiple-choice or open-ended questions automatically.
* **📱 Mobile First**: Built in **React Native** for iOS and Android users.
* **🔔 Notifications**: Daily study reminders using **Firebase Cloud Messaging (FCM)**.

---

## 🧱 Technologies Used

| Layer        | Stack                                |
| ------------ | ------------------------------------ |
| **Frontend** | React Native + Expo                  |
| **Backend**  | Spring Boot (Java), REST APIs        |
| **Database** | MongoDB                              |
| **AI**       | Gemini API (for summarization, etc.) |
| **Storage**  | Firebase or AWS S3 (for notes)       |
| **Push**     | Firebase Cloud Messaging (FCM)       |

---

## ⚙️ Setup & Installation

### 🧑‍💻 Prerequisites

* Node.js (≥ 18.x)
* Java 17+
* MongoDB (local or Atlas)
* Firebase Project (for notifications)
* Gemini API Key
* Expo CLI (`npm install -g expo-cli`)

---

### 📦 Backend Setup

```bash
git clone https://github.com/your-username/flashfocus.git
cd flashfocus/backend
```

1. Set up MongoDB connection and Firebase credentials in `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/flashfocus
firebase.service.account.path=classpath:firebase-service-account.json
gemini.api.key=YOUR_GEMINI_KEY
```

2. Place your Firebase service account JSON in `src/main/resources`.

3. Run the app:

```bash
./mvnw spring-boot:run
```

API will run at `http://localhost:8080`.

---

### 📱 Frontend Setup (React Native)

```bash
cd flashfocus/mobile
npm install
npx expo start
```

Edit `api.js` in the frontend to point to your local IP (e.g., `http://192.168.1.5:8080`).

---

## 📂 Environment Variables

For mobile, you can use `.env` or a constants file:

```js
// constants.js
export const API_URL = "http://<YOUR_LOCAL_IP>:8080";
export const GEMINI_API_KEY = "your_gemini_api_key";
```

---

## 🔐 Firebase Setup

* Create Firebase project
* Enable **Cloud Messaging**
* Register your Android/iOS app
* Use the device token in backend to send reminders

---

## 🧪 API Overview

| Method | Endpoint                  | Description                 |
| ------ | ------------------------- | --------------------------- |
| POST   | `/api/notes/upload`       | Upload note (PDF/DOCX/text) |
| GET    | `/api/flashcards`         | Fetch user flashcards       |
| POST   | `/api/quizzes/generate`   | Generate quiz from note     |
| GET    | `/api/summary/:noteId`    | Get AI summary of a note    |
| POST   | `/api/notifications/send` | Send a push notification    |

---

## 🧐 AI Tasks Powered By Gemini

Gemini API is used for:

* Summarizing uploaded notes
* Generating flashcards (Q\&A format)
* Creating multiple-choice and open-ended quiz questions

---

## 🧑‍💼 Maintainers

* 👤 **Mesam E Tamaar Khan**
  📧 [mesametamaarkhan@gmail.com](mailto:mesametamaarkhan@gmail.com)

---

## 📃 License

MIT License © 2025 Mesamet Amaar Khan
