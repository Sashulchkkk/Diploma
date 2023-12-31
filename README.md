# Дипломный проект профессии тестировщика

В рамках дипломного проекта требовалось автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Для покупки тура есть два способа: с помощью карты и в кредит. В приложении используются два отдельных сервиса оплаты: Payment Gate и Credit Gate.

## Начало работы

1. чтобы получить копию этого проекта нужно ввести команду:
```
   git clone git@github.com:Sashulchkkk/Diploma.git
```

### Prerequisites
1. Установить и запустить IntelliJ IDEA;
2. Установать и запустить Docker Desktop
3. Установить Git
4. Установить Chrome

### Установка и запуск

Пошаговый процесс установки и запуска

1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой:
```
 docker-compose up -d
```
2. В новой вкладке терминала запустить тестируемое приложение:
   * Для MySQL: 
   ```
   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
   ```
   * Для PostgreSQL: 
   ```
   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
   ```
3. Для запуска тестов необходимо ввести следующую команду
   * Для MySQL:
```
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
```
* Для PostgreSQL:
```
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
```
## Лицензия

Этот проект лицензирован под лицензией MIT - подробности можно найти в файле [LICENSE.md](LICENSE.md)
