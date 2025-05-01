# Short Video Android App (Jetpack Compose + AWS Backend)

This is a short video mobile application developed using Jetpack Compose and MVVM architecture. The app connects to a fully serverless backend on AWS, providing video streaming, commenting, likes, and user login features.

## Features

- **Home Screen**: Displays short videos in a vertical feed with auto-play. Supports like and comment actions.
- **Comment Screen**: View and post comments for each video.
- **Discover Screen**: Grid view to explore available videos.
- **Publish Screen**: Upload new videos (recording feature available, but emulator cannot access the camera).
- **User Center**: Shows user profile and personal video stats. Includes login with AWS Cognito.
- **AWS Backend Integration**:
  - Videos and comments data loaded from DynamoDB.
  - Video files stored in S3.
  - Like and comment actions connected to Lambda APIs.
  - User login verified with Cognito.

## Tech Stack

**Frontend**
- Kotlin 
- Jetpack Compose
- Media3 (ExoPlayer)
- Coil (image loading)
- Retrofit2
- Ktor Client (for login API)
- Jetpack Navigation Compose
- StateFlow and mutableStateOf

**Backend**
- AWS API Gateway
- AWS Lambda
- DynamoDB
- S3 Bucket
- Cognito User Pool
- Infrastructure provisioned using AWS CDK

## Emulator Limitation

The app includes camera recording in the Publish screen, but due to emulator restrictions, video capture cannot function. This does not affect the backend upload logic, which is fully implemented.

## How to Run

1. Clone the repository.
2. Set up an emulator or connect an Android device.
3. Build and run the app.

## Architecture

The app follows **MVVM**:
- Model: `Video.kt`, `Comment.kt`
- ViewModel: `HomeViewModel`, `CommentViewModel`, `DiscoverViewModel`, `SharedUiViewModel`
- UI: Composable screens

## License

This project was developed as part of an academic assignment and is not intended for commercial use.

