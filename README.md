# CodeAdvisors Content Service 📚

![CodeAdvisors Logo](http://167.172.78.79:8090/api/v1/files/preview?fileName=b5d01918-2824-48d7-83e0-fb557ce6bd73_2024-12-21T18-28-24.856529397.jpg)

## Handle By ***Thoeng Mengseu***

## Overview 🌐
The **Content Service** is a core component of the **CodeAdvisors** platform, designed to manage educational content and related tags. This service allows administrators and users to create, update, search, and manage content efficiently. Additionally, it integrates with the **Tag Service** to categorize and manage content using tags, helping to organize and filter content more effectively.

## Technologies Used ⚙️
- **Spring Boot**: Microservice framework for creating RESTful APIs.
- **MongoDB**: Database for storing content and tags.
- **Kafka**: For asynchronous communication (if integrated with other services).
- **JPA**: For handling relational data persistence.
- **Eureka**: Service discovery for seamless microservice interaction.
- **Config**: Centralized configuration management.

## Content Services 📝

### Content Management 📄
The Content Service handles the creation, retrieval, update, deletion, and search of content items. Key features include:

- **Create Content**: Add new educational content.
- **Update Content**: Modify existing content.
- **Search Content**: Find content based on queries like title or slug.
- **Soft Delete**: Soft delete content without removing it from the system.
- **Pagination**: Support for paginated content listings.

#### Endpoints 🚀

- **POST /api/v1/contents**: Create new content.
- **GET /api/v1/contents/{id}**: Get content by ID.
- **PATCH /api/v1/contents/{id}**: Update content by ID.
- **DELETE /api/v1/contents/{id}**: Soft delete content by ID.
- **GET /api/v1/contents/search**: Search content by title, slug, or other criteria.
- **GET /api/v1/contents/all**: Retrieve all content with pagination.

### Tag Management 🏷️
The **Tag Service** is responsible for managing tags associated with content. Tags are used to categorize content, making it easier to search and filter.

#### Endpoints 🚀

- **GET /api/v1/tags/all**: Retrieve all tags, optionally sorted by ascending or descending order.
- **GET /api/v1/tags/{name}**: Get tag details by name.

## Setup and Installation 🛠️

### Prerequisites 📦
Before running the services, ensure the following are installed and configured:
- **JDK 21** (for building and running the application)
- **MongoDB** (for content and tag storage)
- **Kafka** (if used for inter-service communication)

### Steps to Run 🚶‍♂️

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/codeadvisors-content-service.git
   cd codeadvisors-content-service
   ```

2. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

3. Run the services:
   ```bash
   ./gradlew bootRun
   ```

4. Ensure MongoDB and Kafka (if applicable) are running before starting the services.

## License 📜
This project is licensed under the MIT License - see the LICENSE file for details.

---
Built with ❤️ by the CodeAdvisors Team