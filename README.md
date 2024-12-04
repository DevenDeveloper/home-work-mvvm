### 1. **Clone the Basic Project**

- Download the basic project from the link you provided.
- Unzip and open the project in **Android Studio**.

### 2. **Set Up Your Development Environment**

Make sure you are using the required versions:

- **Android Studio**: 4.2.1
- worked on **Room** for local database.

### 3. **Implemented the Login Functionality**

#### a) **MVVM Design Pattern**

Follow the MVVM architecture for organizing your code. Create three layers:

- **Model**: Defines the data structure (e.g., for login data).
- **ViewModel**: Handles the business logic for login and interacts with the model.
- **View**: The UI layer that interacts with the user (e.g., `Activity` or `Fragment`).

#### b) **Room Database for Local Storage**

Use **Room** for storing the `data` fields locally after a successful login.

1. Create an entity class for storing login data (like `UserData`).
2. Create a DAO (Data Access Object) interface with methods to store and retrieve the login data.
3. Create a Room database instance to persist the data.

#### c) **API Integration**

- Call the login API with the provided details when the user clicks the login button. Use **Retrofit** for making network requests.
- On success, save the `data` fields in the local Room database.
  
Here’s a basic example of how to structure the login API call:

```kotlin
interface ApiService {
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val errorCode: String,
    val errorMessage: String,
    val data: LoginData?
)

data class LoginData(
    val userId: String,
    val userName: String,
    val isDeleted: Boolean
)
```

- **Development Standards Followed**:
  - MVVM design pattern
  - Clean code practices
  - Use of Room for local database storage
  - Retrofit for network calls

- **Difficulties Encountered**:
 - The project was set up on an older version of Android Studio and utilizes outdated libraries that are no longer compatible with the latest version of Android Studio.

- **Advice for Future Homework**:
  - We need to update the libraries and Android Studio version to take advantage of the latest features and optimize performance.

### 5. **Recording of the Demo Video**

[Demo Video](https://we.tl/t-AVJZDUJrgA)


### 6. **Debug APK**

[Debug APK LINK]((https://i.diawi.com/ermdbW)

### 7. **Conclusion**

In this task, I successfully implemented the login functionality using the MVVM architecture with Android Studio, adhering to the specified requirements. The key components, such as the Room database for local storage and Retrofit for API integration, were utilized to ensure the seamless saving of user data after a successful login.

The development process followed clean coding practices and the principles of MVVM, which helped in maintaining a well-structured and testable codebase. However, some challenges were faced due to the project being set up on an older version of Android Studio with outdated libraries. These libraries were not compatible with the latest version of Android Studio, and it is recommended to update both the libraries and the IDE to optimize performance and access the latest features.

Overall, the task was completed by following industry best practices, ensuring maintainability, and addressing challenges along the way.
