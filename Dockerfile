FROM maven:3.9.4-eclipse-temurin-17

# Instalar Chrome y herramientas necesarias
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    curl \
    gnupg \
    && curl -fsSL https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-linux-signing-keyring.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-linux-signing-keyring.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y \
    google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Directorio de trabajo
WORKDIR /usr/src/app

# --- CACHE DE DEPENDENCIAS MAVEN ---
# Copiamos solo los archivos de definición de dependencias
COPY pom.xml ./

# Pre-descarga dependencias para cachear en esta capa
RUN mvn -B dependency:go-offline

COPY . .

# Por defecto, usamos el repo local dentro del contenedor
ENV MAVEN_OPTS="-Dmaven.repo.local=/root/.m2"

