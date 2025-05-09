# Aboriginal Art Gallery — Backend

This is the Spring Boot backend for the Aboriginal Art Gallery Web Application. The backend is responsible for business logic, role-based authentication, bid management, artifact lifecycle handling, and persistence using PostgreSQL.

## 📦 Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Spring Data JPA
- Firebase Admin SDK (for verifying Firebase Auth tokens)
- Event-Driven Architecture with Domain Driven Design
- JUnit Testing Framework
- 6 Bounded Contexts (Artist, Artifact, Art Fact, Art Type, User, Bid)

---

## 🚀 Features

- CRUD operations for Artifacts, Bids, Artists, Art Facts, and Art Types
- Firebase-authenticated OAuth Google user verification
- Auction lifecycle handling (listing, bidding, closing)
- Role-based access policies (read-only access for all; bid-access for signed-in users, write acess for admins)
- Event logging and audit trails
- Unit testing using JUnit

---

## 📁 Project Structure

The backend uses Firebase Admin SDK to verify tokens from the frontend. All modifying endpoints require a valid Bearer token.

```Authorization: Bearer <FIREBASE_ID_TOKEN>```

## Aggregate Canvas

![Aggregate Canvas](https://firebasestorage.googleapis.com/v0/b/portfolio-310fe.appspot.com/o/images%2Fart-gallery-aggregate-design-canvas.png?alt=media&token=ca38d134-1370-4aaa-9866-9de2d0335daf)

## Application Workflow

![Application Workflow](https://firebasestorage.googleapis.com/v0/b/portfolio-310fe.appspot.com/o/images%2Fartgallery-application-workflow.png?alt=media&token=c726f6ef-2b2b-4bae-9213-aeab2ac87533)

## Entity Relation Diagram

![ERD](https://firebasestorage.googleapis.com/v0/b/portfolio-310fe.appspot.com/o/images%2Ferd-art-gallery.png?alt=media&token=05c5efc8-3d5c-4708-87b6-8f46a2205c5e)


