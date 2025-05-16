# Aboriginal Art Gallery - Backend

This is the Spring Boot backend for the Aboriginal Art Gallery Web Application. It handles the core business logic, including role-based authentication, auction and bid management, artifact lifecycle operations, and data persistence using PostgreSQL.

**Frontend Repository:** [https://github.com/Gamingapple0/art-gallery](https://github.com/Gamingapple0/art-gallery)  
(Built with React and Firebase OAuth)

---

## 📦 Tech Stack

- **Java 17**  
- **Spring Boot**  
- **PostgreSQL**  
- **Spring Data JPA** (Hibernate ORM)  
- **Firebase Admin SDK** (for verifying Firebase authentication tokens)  
- **Event-Driven Architecture** with **Domain-Driven Design (DDD)**  
- **JUnit** (for unit testing)  
- **6 Bounded Contexts**:  
  - Artist  
  - Artifact  
  - Art Fact  
  - Art Type  
  - User  
  - Bid

---

## 🚀 Key Features

- Full **CRUD** support for Artifacts, Artists, Bids, Art Facts, and Art Types  
- **Google OAuth** authentication via Firebase  
- **Auction lifecycle management**: listing, bidding, closing  
- **Role-based access control**:  
  - *Read-only* access for all users  
  - *Bidding* allowed for authenticated users  
  - *Write* access restricted to admins  
- **Event logging** and **audit trails** for key actions  
- **Unit testing** implemented using JUnit

---

## 🔐 Authentication

All modifying endpoints require a valid Firebase Bearer token, verified by the Firebase Admin SDK.

Example request header:

## 📁 Project Structure

The backend uses Firebase Admin SDK to verify tokens from the frontend. All modifying endpoints require a valid Bearer token.

```Authorization: Bearer <FIREBASE_ID_TOKEN>```

## Aggregate Canvas

![Aggregate Canvas](https://firebasestorage.googleapis.com/v0/b/portfolio-310fe.appspot.com/o/images%2Fart-gallery-aggregate-design-canvas.png?alt=media&token=ca38d134-1370-4aaa-9866-9de2d0335daf)

## Application Workflow

![Application Workflow](https://firebasestorage.googleapis.com/v0/b/portfolio-310fe.appspot.com/o/images%2Fartgallery-application-workflow.png?alt=media&token=c726f6ef-2b2b-4bae-9213-aeab2ac87533)

## Entity Relation Diagram

![ERD](https://firebasestorage.googleapis.com/v0/b/portfolio-310fe.appspot.com/o/images%2Ferd-art-gallery.png?alt=media&token=05c5efc8-3d5c-4708-87b6-8f46a2205c5e)


