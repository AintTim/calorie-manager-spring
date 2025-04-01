# Calorie-manager API

## Описание
Проект `Calorie-manager API` - это REST API сервис для отслеживания дневной нормы калорий пользователей и учета съеденных блюд

## Установка и запуск
1. Клонировать репозиторий
```
git clone https://github.com/AintTim/calorie-manager-spring.git
cd calorie-manager-spring
```
2. Запустить проект
```
docker-compose up -build
```

## API Endpoints

### Base URL
`
http://localhost:8080/api
`
### User
* `POST /users` - создать пользователя

**Тело запроса:**
``` json
{
    "name": "John",
    "mail": "john@doe.com",
    "age": 20,
    "sex": "MALE",
    "weight": 72,
    "height": 175,
    "goal": "WEIGHT_GAIN"
}
```
* `GET /users/{id}` - получить пользователя по id
* `GET /users` - получить всех пользователей

### Meal
* `POST /meals?userId={id}&date=<YYYY-MM-DD>` - создать прием пищи
  * date - по умолчанию текущая дата (Опционально)
  
**Тело запроса:**
``` 
    [<dishId>, <dishid>, ...]
```

### Dish
* `POST /dishes` - создать пользователя

**Тело запроса:**
``` json
{
    "name":"Chocolate Cheesecake",
    "calories": 350,
    "proteins": 15,
    "fats": 20,
    "carbs": 50
}
```
### Report
* `GET /reports/{id}?date=<YYYY-MM-DD>` - получить дневной отчет пользователя за конкретный день
    * date - по умолчанию текущая дата (Опционально)
* `GET /reports/history/{id}?start=<YYYY-MM-DD>&&end=<YYYY-MM-DD>` - получить историю питания за указанный период
    * start - по умолчанию текущая дата (Опционально)
* `GET /reports/check-limit/{id}?date=<YYYY-MM-DD>` - получить дневной отчет пользователя за конкретный день
  * date - по умолчанию текущая дата (Опционально)