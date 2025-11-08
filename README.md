# IntelliServe - AI Chat Android App

An Android application that allows users to chat with different AI models from x.ai (Grok) and Groq Cloud.

## Features

- 🤖 **Multiple AI Models**: Support for various AI models including:
  - x.ai Grok Beta
  - Groq Llama 3 70B
  - Groq Llama 3 8B
  - Groq Mixtral 8x7B
  - Groq Gemma 7B

- 💬 **Modern Chat Interface**: Clean, intuitive chat UI built with Jetpack Compose
- 🎨 **Material Design 3**: Beautiful, modern UI following Material Design guidelines
- ⚡ **Fast Performance**: Leveraging Groq's ultra-fast inference infrastructure
- 🔄 **Model Switching**: Easily switch between different AI models mid-conversation

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Clean Architecture
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Async**: Kotlin Coroutines + Flow
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## Setup Instructions

### Prerequisites

1. Android Studio Hedgehog or later
2. JDK 17
3. Android SDK 34

### API Keys

You need to obtain API keys from:

1. **x.ai (Grok)**: Get your API key from [x.ai](https://x.ai/)
2. **Groq Cloud**: Get your API key from [Groq Console](https://console.groq.com/)

### Configuration

1. Clone the repository
2. Open the project in Android Studio
3. Navigate to `app/src/main/java/com/example/intelliserve/di/NetworkModule.kt`
4. Replace the placeholder API keys:

   ```kotlin
   private const val XAI_API_KEY = "YOUR_XAI_API_KEY"
   private const val GROQ_API_KEY = "YOUR_GROQ_API_KEY"
   ```

### Build and Run

1. Sync Gradle files
2. Build the project
3. Run on an emulator or physical device

## Project Structure

```text
app/
├── src/main/java/com/example/intelliserve/
│   ├── data/
│   │   ├── api/              # API service interfaces
│   │   │   ├── models/       # API request/response models
│   │   │   ├── XAIApiService.kt
│   │   │   └── GroqApiService.kt
│   │   └── repository/       # Repository implementations
│   │       └── ChatRepository.kt
│   ├── di/                   # Dependency injection modules
│   │   ├── NetworkModule.kt
│   │   └── RepositoryModule.kt
│   ├── ui/
│   │   ├── chat/            # Chat screen and ViewModel
│   │   ├── models/          # UI data models
│   │   ├── navigation/      # Navigation setup
│   │   └── theme/           # App theming
│   └── IntelliServeApplication.kt
```

## API Documentation

### x.ai (Grok)

- Base URL: `https://api.x.ai/`
- Endpoint: `/v1/chat/completions`
- Documentation: [x.ai API Docs](https://docs.x.ai/)

### Groq Cloud

- Base URL: `https://api.groq.com/`
- Endpoint: `/openai/v1/chat/completions`
- Documentation: [Groq API Docs](https://console.groq.com/docs)

## Security Notes

⚠️ **Important**: Never commit your API keys to version control!

For production apps, consider:

- Using environment variables
- Implementing a backend proxy server
- Using Android's BuildConfig for API keys
- Storing keys in local.properties (gitignored)

## Future Enhancements

- [ ] Message persistence with Room database
- [ ] User authentication
- [ ] Conversation history
- [ ] Message editing and regeneration
- [ ] Custom system prompts
- [ ] Image support for multimodal models
- [ ] Streaming responses
- [ ] Dark/Light theme toggle
- [ ] Export conversations
- [ ] Settings screen for API key management

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues and questions, please open an issue on GitHub.
