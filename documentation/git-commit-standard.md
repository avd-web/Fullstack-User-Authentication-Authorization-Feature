# Git-commit-standard

### Commit standard structure: 
This standard uses a specific format for the subject line: type(scope): subject. 

- type: Describes the type of change (e.g., feat, fix, refactor, docs, test, chore).  

- scope (optional): Specifies the area of the codebase affected (e.g., a specific module or component).  

- subject: A concise description of the change.
  (Keep the subject line under 50 characters.)  
___

### Examples:

- feat: Add user registration functionality
- fix(security): Fix security vulnerability in login form
- refactor(service): Improve performance of user service
- docs: Update API documentation for new features
- test: Add unit tests for new user registration functionality
___
### Additional Tips:

- Capitalize the first letter of the subject line and use imperative mood.
- Use a blank line to separate the subject line from the body.
- Optionally, include a body message: This can provide more details about the change, such as:
- Why the change was made.
- How the change works.
- Related issues or pull requests.
- Use clear and concise language: Avoid jargon and technical terms that others might not understand.
- Focus on the impact of the change: Explain what the change does, not how it was done.
- Reference relevant issues or pull requests: This helps others track the context of the change.
- Use consistent formatting: This improves readability and maintainability of the commit history.

### Specific Practices for Spring projects:

- You can refer to the contributing guidelines of specific Spring projects, such as Spring Framework or Spring Boot, for any additional recommendations. These guidelines might suggest specific types or scopes to use in commit messages.
- Consider mentioning the affected Spring components or configurations in the message.
___
#### "By following these best practices, you can write clear, concise, and informative commit messages that improve the collaboration and understanding of your project's history."