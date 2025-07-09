
---

# 💰 Personal Finance Visualizer

A responsive full-stack finance tracking app built with **Next.js App Router**, **Spring Boot**, **MongoDB**, **shadcn/ui**, and **Recharts**. It helps you manage expenses, visualize spending patterns, and stay within budget.

![Next.js](https://img.shields.io/badge/Next.js-000000?style=for-the-badge\&logo=nextdotjs\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge\&logo=spring-boot\&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge\&logo=mongodb\&logoColor=white)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge\&logo=tailwind-css\&logoColor=white)

---

## 🚀 Features

### ✅ Stage 1: Transactions

* Add / edit / delete transactions
* Inputs: Amount, Description, Date
* Clean and responsive design

### ✅ Stage 2: Categories

* Predefined categories (Food, Transport, Health, etc.)
* Assign category to each transaction
* Category-wise pie chart
* Dashboard summary cards:

  * Total expenses
  * Category breakdown
  * Recent transactions

### ✅ Stage 3: Budgeting & Insights

* Set monthly budgets per category
* Budget vs actual comparison bar chart
* Remaining budget visualization
* Simple spending insights

---

## 🛠️ Tech Stack

* **Frontend**: Next.js (App Router), Tailwind CSS, shadcn/ui
* **Backend**: Spring Boot (Java)
* **Database**: MongoDB (via Spring Data)
* **Visualization**: Recharts
* **Icons**: Lucide-react

---

## 📦 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Sushant-Joshilkar04/YardStick_Assignment.git
cd YardStick_Assignment
```

---

### 2. Frontend Setup (Next.js)

```bash
cd frontend
npm install
npm run dev
```

Open in browser: [http://localhost:3000](http://localhost:3000)


---

### 3. Backend Setup (Spring Boot)


```bash
cd backend
./mvnw spring-boot:run
```

Ensure the following environment variable or config exists:

```properties
spring.application.name=finance_management

spring.data.mongodb.uri=mongodb://localhost:27017/finance_management

spring.data.mongodb.database=finance_db

spring.data.mongodb.auto-index-creation=true

server.port=8080


spring.web.cors.allowed-origins=http://localhost:3000

spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS

spring.web.cors.allowed-headers=*

spring.web.cors.allow-credentials=true

logging.level.org.springframework.web=DEBUG

logging.level.com.example.finance_management=DEBUG
```

The backend will be running on: [http://localhost:8080](http://localhost:8080)

---

## 📁 Project Structure

```
YardStick_Assignment/
├── frontend/                  # Next.js Frontend
│   ├── src/
│   │   ├── app/               # Pages and Routes
│   │   ├── components/        # UI Components
│   │   ├── lib/               # Utility functions
│   │   
│   └── ...
│
├── backend/                   # Spring Boot Backend
│   ├── src/main/java/
│   │   └── com/example/...
│   │       ├── controller/    # API Controllers
│   │       ├── service/       # Business Logic
│   │       ├── model/         # MongoDB Models
│   │       └── repository/    # MongoDB Repos
│   └── ...
│
└── README.md
```

---

## 🧭 Usage

### Adding Transactions

1. Go to the "Transactions" page
2. Click “Add Transaction”
3. Fill in amount, description, category, and date
4. Submit to sync with backend via REST API

### Viewing Analytics

* **Dashboard**: High-level spending summary
* **Pie Chart**: Category-wise breakdown
* **Budget Bar Chart**: Budget vs actual
* **Insights**: Spending habits & alerts

### Managing Budgets

1. Visit the Budgets section
2. Set monthly limits per category
3. Track current spending vs budget
4. Receive alerts on over-spending

---

## 🌟 Key Components

### shadcn/ui

* `Card`: Dashboard stats
* `Button`, `Input`, `Select`: Forms
* `Dialog`, `Alert`: Modals and notifications

### Recharts Visualizations

* `PieChart`: Category breakdown
* `BarChart`: Budget comparisons
* `LineChart`: Monthly trends
* `AreaChart`: Spending patterns

---

