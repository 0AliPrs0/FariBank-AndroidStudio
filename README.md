# 📱 FariBank - Android Version

This project is a new phase in the development of the "FariBank" neo bank, defined with the goal of creating a dedicated Android application using the Android SDK and the XML design language.

---

## 📖 Table of Contents

- [🎯 Main Project Goals](#main-project-goals)
- [✨ Application Features (User Section)](#application-features-user-section)
  - [Login and Sign-up Page](#login-and-sign-up-page)
  - [Main Dashboard](#main-dashboard)
  - [Contact Management](#contact-management)
  - [Money Transfer](#money-transfer)
  - [Investment Funds](#investment-funds)
  - [Support Requests](#support-requests)
  - [Loans and Installments](#loans-and-installments)
- [🤖 Automated System Management (Backend Section)](#automated-system-management-backend-section)
- [🛠 Implementation Guide with Android Studio](#implementation-guide-with-android-studio)

---

## 🎯 Main Project Goals

1.  GUI Design and Implementation: Replacing the text-based interface (CLI) with an attractive and user-friendly graphical environment for Android users.
2.  Decoupling Logic from View: Completely separating the core application logic code from the user interface code.
3.  Concurrent Processing Implementation: Using multi-threading to automate System Administrator tasks.
4.  Advanced Error Handling: Implementing appropriate mechanisms for handling errors in a graphical environment.

---

## ✨ Application Features (User Section)

### Login and Sign-up Page
- Login Page: Includes fields for phone number and password, along with a login button. Appropriate error messages are displayed for incorrect information.
- Sign-up Page: A form for new users to register by providing their first name, last name, phone number, national ID, and a strong password.
  > Note: The password must be strong and include a combination of uppercase and lowercase letters, numbers, and special characters.

### Main Dashboard
The main screen of the application after a successful login, which includes:
- 📊 Display of the current account balance.
- 📜 A list of recent transactions.
- ➕ A button for direct account top-up.
- 🗂 Quick access buttons to important sections like Money Transfer and Investment Funds.

### Contact Management
- ➕ Add Contact: By providing a first name, last name, and phone number.
- 📝 Contact List: Displays contacts in a list (RecyclerView) with edit and delete capabilities.

### Money Transfer
One of the key sections of the application with the following features:
- Select Destination: Ability to enter the destination account number manually, select from the contact list, or choose from recent accounts.
- Enter Amount: A field for entering the transfer amount.
- Final Confirmation: Displays a confirmation dialog including the destination account holder's name and the amount before finalizing the transaction.
- Transfer Methods:

| Method Name | Destination | Transfer Speed | Fee |
| :--- | :--- | :--- | :--- |
| Fari-to-Fari | Internal FariBank Accounts | Instant | Free |
| Card-to-Card| Other Banks | Instant | Fixed |
| Paya / Pol | Other Banks | Per Banking Cycle | Variable |

### Investment Funds
The user can manage their investment funds in this section:
- Create New Fund: By setting a fund name and type.
- Fund List: Displays the user's funds in a list.
- Manage Fund: Ability to deposit money into or withdraw from the fund.
- View Details: Displays complete details of each fund (balance, type, transactions).

### Support Requests
- Submit Request: Ability to send a support ticket to the system administrator.
- View Response: View the details of the requests and the administrator's responses.

### Loans and Installments
This new section allows users to manage their loans:
- Loan Request: A form to submit a new loan request by specifying the amount, repayment period, and loan type.
- Installment Management:
| Feature | Description |
| :--- | :--- |
| Loan List | Displays the user's active loans, including the total amount, number of installments, and start date. |
| Loan Details | By selecting a loan, more details are displayed, such as installment number, amount, payment date, and status. |
| Pay Installment | A button to pay the selected installment, which deducts the amount from the user's main account after confirmation. |

---

## 🤖 Automated System Management (Backend Section)

This section does not require a graphical user interface and performs administrator tasks automatically in separate Threads:
- Fund Interest Payment: An automated Thread that periodically (e.g., monthly) deposits interest for investment funds into user accounts.
- Automated Money Transfer: Processes transfers that require administrator approval.
- Automated Support Response: Sends an initial, automated reply ("Our colleagues will contact you soon") to support tickets.
- Loan Request Review: A smart Thread that checks the user's history and automatically approves or denies loan requests.

---

## 🛠 Implementation Guide with Android Studio

To build the application, use the following components and principles in the Android environment:

| Key Principles | Key Widgets |
| :--- | :--- |
| Development Environment: Android Studio | Text Input: EditText |
| UI Design: XML Files | Button: Button |
| Page Management: Activity & Fragment | Text Display: TextView |
| Optimized Lists: RecyclerView | Cards: CardView |
| Navigation: Intents | Dialogs: AlertDialog |
| | Progress Bar: ProgressBar |
| | Selection: Spinner |
