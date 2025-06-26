# 📱 FariBank - Android Version

This project is a new phase in the development of the "FariBank" neo-bank, defined with the goal of creating a dedicated Android application using the **Android SDK**, **Java/Kotlin**, and the **XML** design language.

---

## 📖 Table of Contents

- [🎯 Main Project Goals](#-main-project-goals)
- [✨ Application Features (User Section)](#-application-features-user-section)
  - [Login and Sign-up Page](#login-and-sign-up-page)
  - [Main Dashboard](#main-dashboard)
  - [Contact Management](#contact-management)
  - [Money Transfer](#money-transfer)
  - [Investment Funds](#investment-funds)
  - [Support Requests](#support-requests)
  - [Loans and Installments](#loans-and-installments)
- [🤖 Automated System Management (Backend Section)](#-automated-system-management-backend-section)
- [🛠️ Implementation Guide with Android Studio](#️-implementation-guide-with-android-studio)

---

## 🎯 Main Project Goals

1.  **Native Android GUI**: Creating an attractive and user-friendly graphical environment specifically for Android users, following Material Design guidelines.
2.  **Decoupling Logic from View**: Completely separating the core application logic from the UI code using modern Android architectures like **MVVM** (Model-View-ViewModel) or **MVP** (Model-View-Presenter).
3.  **Asynchronous Operations**: Using tools like **Coroutines** (for Kotlin) or **AsyncTask/Threads** (for Java) to perform network requests and database operations without blocking the main UI thread.
4.  **Advanced Error Handling**: Implementing robust mechanisms for handling network errors, input validation, and other exceptions in a mobile environment.

---

## ✨ Application Features (User Section)

### Login and Sign-up Page
- **Login Page**: Includes fields for phone number and password, along with a login button. Displays appropriate error messages (`Toast` or `Snackbar`) for incorrect information.
- **Sign-up Page**: A multi-step form for new users to register by providing their first name, last name, phone number, national ID, and a strong password.
  > **Note**: The password must be strong and include a combination of uppercase and lowercase letters, numbers, and special characters.

### Main Dashboard
The main screen of the application after a successful login, built as an `Activity` containing multiple `Fragments`:
- 📊 Display of the current account balance in a prominent `CardView`.
- 📜 A list of recent transactions using a `RecyclerView`.
- ➕ A Floating Action Button (FAB) for direct account top-up.
- 🗂 A `BottomNavigationView` for quick access to important sections.

### Contact Management
- **Add Contact**: By providing a first name, last name, and phone number. May request permission to access device contacts.
- **Contact List**: Displays contacts in a `RecyclerView` with options to edit and delete each item.

### Money Transfer
One of the key sections of the application with the following features:
- **Select Destination**: Ability to enter the destination account number manually, select from the contact list, or choose from recent accounts.
- **Enter Amount**: An `EditText` field formatted for currency input.
- **Final Confirmation**: Displays an `AlertDialog` with the destination account holder's name and the amount before finalizing the transaction.
- **Transfer Methods**:

| Method Name | Destination | Transfer Speed | Fee |
| :--- | :--- | :--- | :--- |
| **Fari-to-Fari** | Internal FariBank Accounts | Instant | Free |
| **Card-to-Card**| Other Banks | Instant | Fixed |
| **Paya / Pol** | Other Banks | Per Banking Cycle | Variable |

### Investment Funds
The user can manage their investment funds in this section:
- **Create New Fund**: By setting a fund name and type using `EditText` and `Spinner` widgets.
- **Fund List**: Displays the user's funds in a `RecyclerView`.
- **Manage Fund**: Ability to deposit money into or withdraw from the fund.
- **View Details**: Tapping a fund opens a new `Activity` or `Fragment` showing complete details (balance, type, transactions).

### Support Requests
- **Submit Request**: A form to send a support ticket to the system administrator.
- **View Response**: A list of tickets and their statuses, showing the administrator's responses when available.

### Loans and Installments
This new section allows users to manage their loans:
- **Loan Request**: A form to submit a new loan request by specifying the amount, repayment period, and loan type.
- **Installment Management**:
| Feature | Description |
| :--- | :--- |
| **Loan List** | Displays active loans in a `RecyclerView`, including amount, installments, and start date. |
| **Loan Details** | By selecting a loan, more details are displayed, such as installment details and payment status. |
| **Pay Installment**| A button to pay the selected installment, which initiates the payment process after confirmation. |

---

## 🤖 Automated System Management (Backend Section)

This section describes backend logic that the Android app interacts with, but does not implement directly. The app calls an API, and these tasks are handled by the server:
- **Fund Interest Payment**: An automated server-side job that periodically deposits interest into user accounts.
- **Automated Money Transfer**: Server-side processing of transfers that require administrator approval.
- **Automated Support Response**: The server sends an initial, automated reply to support tickets.
- **Loan Request Review**: A server-side smart engine that checks the user's history and automatically approves or denies loan requests.

---

## 🛠️ Implementation Guide with Android Studio

To build the application, use the following components and principles in the Android environment:

| Key Principles & Architecture | Key UI Widgets & Libraries |
| :--- | :--- |
| **IDE**: Android Studio | **Text Input**: `EditText` |
| **UI Design**: XML Layouts & Material Design | **Buttons**: `Button`, `FloatingActionButton` |
| **Core Components**: `Activity` & `Fragment` | **Text Display**: `TextView` |
| **Optimized Lists**: `RecyclerView` with `ViewHolder` | **Layouts**: `ConstraintLayout`, `LinearLayout` |
| **Navigation**: `Intent`, Navigation Component | **Cards**: `CardView` |
| **Architecture**: MVVM with `ViewModel` & `LiveData` | **Dialogs**: `AlertDialog`, `BottomSheetDialog` |
| **Networking**: Retrofit / Volley | **Progress**: `ProgressBar` |
| **Local Storage**: Room / SharedPreferences | **Selection**: `Spinner` |