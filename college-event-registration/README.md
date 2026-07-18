# College Event Registration - Full Stack Project

A simple full-stack college event registration system.

## Features
- Students can view available college events.
- Students can register for an event.
- Organizers can view registered participants for each event.
- Spring Boot REST API backend.
- React frontend.
- H2 in-memory database for easy setup.

## Project Structure
- `backend/` - Spring Boot REST API
- `frontend/` - React + Vite frontend

## Backend Setup
Requirements:
- Java 17+
- Maven 3.9+

Run:
```bash
cd backend
mvn spring-boot:run
```

Backend runs at:
`http://localhost:8080`

## Frontend Setup
Requirements:
- Node.js 18+

Run:
```bash
cd frontend
npm install
npm run dev
```

Frontend runs at:
`http://localhost:5173`

## API Endpoints
- `GET /api/events`
- `POST /api/events`
- `GET /api/events/{eventId}/registrations`
- `POST /api/registrations`

## Sample Student Registration
```json
{
  "studentName": "Santhosh Reddy",
  "email": "santhosh@example.com",
  "department": "CSE",
  "year": "3rd Year",
  "eventId": 1
}
```
