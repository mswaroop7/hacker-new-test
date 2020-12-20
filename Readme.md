Spring Boot Project that provides APIs on the top news and comments from  Hacker News API. (https://github.com/HackerNews/API)

/top-stories - returns the top 10 best stories ranked by score in the last 10 minutes.
/past-stories - returns all the past top stories that were served previously
/comments?itemId=${itemId} - returns the top 10 parent comments on a given story (sorted by the total number of comments. 

Steps to build the project:

1. run ./mvnw -DskipTests package
2. run docker build -t springboot/hackernews-api .
3. docker-compose up

To see the list of apis visit:
http://localhost:8080/swagger-ui.html

Note- This project is built using Java SE 8 version.
This project also uses redis cache and postgres which are included in docker-compose.