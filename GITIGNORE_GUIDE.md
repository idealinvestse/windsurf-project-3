# .gitignore Guide for IntelliServe

This document explains the comprehensive `.gitignore` configuration for the IntelliServe Android project.

## 📋 Overview

The `.gitignore` file contains **299 lines** of carefully curated patterns to keep your repository clean, secure, and professional.

---

## 🔐 Security-Critical Sections

### API Keys and Secrets
```gitignore
**/secrets.properties
**/api_keys.properties
**/keystore.properties
local.properties
secrets/
.env
.env.local
```

**Why**: Prevents accidental exposure of API keys, database credentials, and other sensitive information.

**Best Practice**: Store API keys in `local.properties` or environment variables, never commit them!

### Signing Files
```gitignore
*.jks
*.keystore
key.properties
signing.properties
```

**Why**: Protects your app signing keys from being exposed. Compromised signing keys can allow malicious actors to publish fake versions of your app.

---

## 🏗️ Build & Generated Files

### Build Outputs
```gitignore
*.apk
*.aab
*.aar
build/
.gradle/
```

**Why**: Build artifacts are generated and can be recreated. They bloat the repository and cause merge conflicts.

### Generated Code
```gitignore
generated/
**/*_MembersInjector.class
**/*_Factory.class
```

**Why**: Annotation processors (Dagger, Hilt, Room) generate code automatically. No need to version control it.

### Compose Compiler Reports
```gitignore
**/compose_compiler_config.json
**/compose-reports/
```

**Why**: Jetpack Compose generates compilation reports for debugging. These are developer-specific and regenerated on each build.

---

## 💻 IDE-Specific Files

### Android Studio / IntelliJ IDEA
```gitignore
*.iml
.idea/workspace.xml
.idea/tasks.xml
.idea/gradle.xml
.idea/misc.xml
```

**Why**: These files contain developer-specific settings (window positions, recent files, etc.) that differ between team members.

**Keep**: `.idea/codeStyles/`, `.idea/inspectionProfiles/` for team-wide code style consistency.

### VSCode
```gitignore
.vscode/
*.code-workspace
```

**Why**: VSCode users have different configurations. Keep these local.

### Eclipse
```gitignore
.metadata
.settings/
.project
.classpath
```

**Why**: Eclipse-specific project files that conflict with Android Studio.

---

## 🧪 Testing & Quality

### Test Results
```gitignore
test-results/
androidTest-results/
.test/
```

**Why**: Test outputs are generated on each run. Only test code should be versioned.

### Coverage Reports
```gitignore
coverage/
*.exec
jacoco.exec
```

**Why**: Coverage reports are generated from test runs. Keep the configuration, not the output.

### Lint Reports
```gitignore
lint/intermediates/
lint/generated/
lint/outputs/
```

**Why**: Lint reports are generated on each analysis. Keep `lint.xml` configuration instead.

### Code Quality Tools
```gitignore
detekt-report.html
detekt-report.xml
.ktlint
```

**Why**: Tool outputs are regenerated. Version control the configuration files instead.

---

## 📦 Dependencies & Libraries

### Gradle
```gitignore
.gradle/
.gradle-cache/
```

**Why**: Gradle caches dependencies locally. Each developer downloads their own.

### Node.js (if applicable)
```gitignore
node_modules/
npm-debug.log*
yarn-error.log*
```

**Why**: Dependencies are specified in `package.json`. Each developer installs their own.

### Maven
```gitignore
target/
pom.xml.releaseBackup
```

**Why**: Maven build outputs and backup files.

---

## 🗄️ Database Files

### Local Databases
```gitignore
*.db
*.db-shm
*.db-wal
*.sqlite
*.sqlite3
```

**Why**: Local database files used during development/testing. Production databases are separate.

### Realm
```gitignore
*.realm
*.realm.lock
*.realm.management/
```

**Why**: Realm database files are binary and developer-specific.

### Room Schemas (Optional)
```gitignore
# schemas/
```

**Why**: Commented out by default. Uncomment if you don't want to version Room migration schemas.

---

## 🖥️ OS-Specific Files

### macOS
```gitignore
.DS_Store
.DS_Store?
._*
.Spotlight-V100
.Trashes
```

**Why**: macOS creates these hidden files for folder metadata. They're useless in version control.

### Windows
```gitignore
Thumbs.db
ehthumbs.db
```

**Why**: Windows thumbnail cache files.

### Linux
```gitignore
*~
```

**Why**: Backup files created by various Linux editors.

---

## 🔧 Development Tools

### Firebase
```gitignore
google-services.json
firebase-debug.log
```

**Why**: `google-services.json` contains project-specific configuration. Use different files for dev/staging/prod.

### Crashlytics
```gitignore
crashlytics.properties
crashlytics-build.properties
fabric.properties
```

**Why**: Crashlytics configuration files with API keys.

### Profiling
```gitignore
*.hprof
*.dump
```

**Why**: Memory dump files from profiling sessions. These are large binary files.

---

## 📝 Temporary & Backup Files

### Temporary Files
```gitignore
*.tmp
*.temp
.tmp/
.temp/
```

**Why**: Temporary files created during development.

### Backup Files
```gitignore
*.bak
*.backup
*.swp
*.swo
```

**Why**: Editor backup files and swap files.

### Patch Files
```gitignore
*.patch
*.orig
*.rej
```

**Why**: Patch files from merge conflicts or manual patches.

---

## 🎯 Recommended Practices

### ✅ DO Version Control

- Source code (`.kt`, `.java`, `.xml`)
- Build configuration (`build.gradle`, `settings.gradle`)
- Resources (`res/`, `assets/`)
- Proguard rules (`proguard-rules.pro`)
- Gradle wrapper (`gradle/wrapper/`)
- README and documentation
- `.gitignore` itself

### ❌ DON'T Version Control

- Build outputs (`.apk`, `.aab`)
- Generated code
- IDE-specific files
- API keys and secrets
- Local configuration
- Temporary files
- Dependencies (downloaded via Gradle)

---

## 🔒 Security Checklist

Before committing, ensure:

- [ ] No API keys in code
- [ ] No passwords or tokens
- [ ] No signing keys (`.jks`, `.keystore`)
- [ ] No `local.properties` with sensitive data
- [ ] No `google-services.json` with production keys
- [ ] No database files with real data

---

## 🛠️ Customization

### For Your Team

You may want to **keep** these files (remove from `.gitignore`):

- `.idea/codeStyles/` - Shared code formatting
- `.idea/inspectionProfiles/` - Shared code inspections
- `schemas/` - Room database migration schemas

### For CI/CD

Your CI/CD pipeline should provide:

- `local.properties` with SDK path
- `google-services.json` from secure storage
- Signing keys from environment variables
- API keys from secrets management

---

## 📚 Additional Resources

- [Git Documentation](https://git-scm.com/docs/gitignore)
- [GitHub's Android .gitignore](https://github.com/github/gitignore/blob/main/Android.gitignore)
- [Android Developer Guide](https://developer.android.com/studio/build)

---

## 🎓 Pro Tips

1. **Check before committing**: Use `git status` to verify what's being committed
2. **Use git-secrets**: Install git-secrets to prevent committing secrets
3. **Review .gitignore regularly**: Update as your project evolves
4. **Document exceptions**: If you must commit something unusual, document why
5. **Use .gitignore templates**: Start with standard templates and customize

---

## 📊 Statistics

Current `.gitignore` configuration:

- **Total patterns**: 299 lines
- **Security patterns**: 15+
- **Build patterns**: 30+
- **IDE patterns**: 40+
- **OS patterns**: 10+
- **Tool patterns**: 20+

---

**Remember**: A good `.gitignore` keeps your repository clean, secure, and professional! 🎉
