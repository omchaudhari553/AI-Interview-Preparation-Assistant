# AI Interview Preparation Assistant

A RAG-based AI Interview Preparation Assistant built with Java, Spring Boot, and Spring AI with Ollama.

## Project Structure

```
ai-interview-assistant/
│
├── config/
│   ├── SecurityConfig.java
│   ├── AiConfig.java
│
├── controller/
│   ├── AuthController.java
│   ├── DocumentController.java
│   ├── ChatController.java
│   ├── MockInterviewController.java
│   ├── RagController.java
│
├── service/
│   ├── AuthService.java
│   ├── DocumentService.java
│   ├── ChatService.java
│   ├── RagService.java
│   ├── DocumentIngestionService.java
│   ├── PdfService.java
│   ├── MockInterviewService.java
│
├── repository/
│   ├── UserRepository.java
│   ├── DocumentChunkRepository.java
│   ├── ChatHistoryRepository.java
│   ├── InterviewSessionRepository.java
│
├── model/
│   ├── User.java
│   ├── DocumentChunk.java
│   ├── ChatHistory.java
│   ├── InterviewSession.java
│   ├── ChatRequest.java
│   ├── ChatResponse.java
│   ├── Role.java
│
├── security/
│   ├── JwtService.java
│
├── dto/
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│
└── AiInterviewAssistantApplication.java
```

## Tech Stack

- Java 21
- Spring Boot 3.x
- Spring AI with Ollama
- Spring Security
- JWT Authentication
- Spring Data JPA
- PostgreSQL with pgvector
- Maven
- Lombok
- Docker

## Prerequisites

- Java 21
- Maven 3.x
- Docker
- Ollama installed and running

## Setup

1. Clone the repository

2. Start PostgreSQL using Docker:
```bash
docker-compose up -d
```

3. Enable pgvector in PostgreSQL:
```sql
CREATE DATABASE interviewdb;
\c interviewdb
CREATE EXTENSION IF NOT EXISTS vector;
```

4. Install and start Ollama:
```bash
ollama pull llama3
ollama pull nomic-embed-text
```

5. Run the application:
```bash
mvn spring-boot:run
```

## API Endpoints

### Authentication

- POST `/auth/register` - Register a new user
- POST `/auth/login` - Login and get JWT token

### Documents

- POST `/documents/upload` - Upload a PDF document

### Chat

- POST `/ai/ask` - Ask a question to AI

### RAG

- POST `/rag/ask` - Ask a question with RAG context

### Mock Interview

- POST `/interview/start` - Start a mock interview session
- POST `/interview/answer` - Submit an answer and get feedback

## Example API Usage

### Register User
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"test@example.com","password":"password123"}'
```

### Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'
```

### Upload Document
```bash
curl -X POST http://localhost:8080/documents/upload \
  -F "file=@document.pdf"
```

### Ask Question
```bash
curl -X POST http://localhost:8080/ai/ask \
  -H "Content-Type: application/json" \
  -d '{"question":"What is Java?"}'
```

### Ask Question with RAG
```bash
curl -X POST http://localhost:8080/rag/ask \
  -H "Content-Type: application/json" \
  -d '{"question":"What is HashMap?"}'
```

### Start Interview
```bash
curl -X POST http://localhost:8080/interview/start
```

### Submit Answer
```bash
curl -X POST http://localhost:8080/interview/answer \
  -H "Content-Type: application/json" \
  -d '"HashMap is thread-unsafe while ConcurrentHashMap is thread-safe"'
```

## Features

1. User Authentication with JWT
2. Document Upload (PDF)
3. Text Extraction from Documents
4. RAG-based Question Answering with pgvector
5. Mock Interview with AI Feedback
6. Vector Search with pgvector
7. Ollama integration for local AI

## Configuration

Update `src/main/resources/application.yml` with your settings:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/interviewdb
    username: postgres
    password: postgres

  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        model: llama3
      embedding:
        model: nomic-embed-text

    vectorstore:
      pgvector:
        initialize-schema: true

jwt:
  secret: mySuperSecretKeyForJwtAuthentication
```

## License

MIT License
