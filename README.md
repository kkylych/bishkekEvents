### **BishEvents**  

**BishEvents** – это веб-платформа для организации, бронирования и просмотра мероприятий в городе Бишкек.  
Она предоставляет удобный интерфейс для поиска событий, онлайн-бронирования мест и создания собственных мероприятий.  

---

## **Функциональность**  

### **Для пользователей:**  
- Просмотр списка мероприятий с возможностью фильтрации по категориям, дате и популярности.  
- Отображение мероприятий на интерактивной карте.  
- Регистрация на платформе с подтверждением email.  
- Онлайн-бронирование мест.  

### **Для организаторов:**  
- Создание и управление мероприятиями.  
- Аналитика посещаемости и популярности событий.  

---

## **Технологии**  

Проект разработан с использованием следующих технологий:  

- **Backend:**  
  - Java  
  - Spring Boot  
  - JPA/Hibernate  

- **Database:**  
  - PostgreSQL  

- **Frontend:**  
  - HTML  
  - CSS  
  - JavaScript (в разработке)  

- **Инструменты:**  
  - Maven – управление зависимостями.  
  - GitHub – контроль версий.  
  - Agile/Scrum – методология разработки.  

---

## **Установка и запуск проекта**  

### **1. Клонирование репозитория**  
```bash
git clone https://github.com/kkylych/bishkekEvents.git
cd bishkekEvents
```  

### **2. Настройка базы данных**  
1. Убедитесь, что PostgreSQL установлен и запущен.  
2. Создайте базу данных для проекта:  
   ```sql
   CREATE DATABASE bishevents;
   ```  
3. Укажите параметры подключения в файле `application.properties`:  
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/bishevents
   spring.datasource.username=postgres
   spring.datasource.password=123456
   ```  

### **3. Сборка и запуск приложения**  
1. Соберите проект с помощью Maven:  
   ```bash
   mvn clean install
   ```  
2. Запустите приложение:  
   ```bash
   mvn spring-boot:run
   ```  

### **4. Доступ к приложению**  
Откройте браузер и перейдите по адресу:  
```
http://localhost:8080
```  

---

## **Основные эндпоинты**  

### **Пользователи:**  
- **POST /register** – регистрация нового пользователя.  
- **POST /login** – авторизация.  

### **Мероприятия:**  
- **GET /events** – получение списка мероприятий.  
- **POST /events** – создание нового мероприятия (только для организаторов).  
- **PUT /events/{id}** – обновление информации о мероприятии.  
- **DELETE /events/{id}** – удаление мероприятия.  

---

## **Планы на будущее**  
- Реализация онлайн-оплаты бронирований.  
- Интеграция с внешними API для событий.  
- Создание мобильного приложения.  
- Улучшение аналитики для организаторов.  

---

## **Контакты**  
- **Email:** kylychbek38@gmail.com  
- **GitHub:** [BishEvents Repository](https://github.com/kkylych/bishkekEvents)  

---

### **Дополнительно**  
- **Презентация:** [Canva Design](https://www.canva.com/design/DAGXmji6Q5I/Tu3PkAfkMgLFIhrVsv76rg/edit?utm_content=DAGXmji6Q5I&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)  
- **Техническое задание:** [Google Docs](https://docs.google.com/document/d/1fiNgQz8Sd9iUlZiqXA99iBnUpl3AmLEm9ES7hFAdGr8/edit?usp=sharing)  

---

**BishEvents – сделаем мероприятия доступнее вместе!**  
