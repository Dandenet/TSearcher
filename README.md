# To start server
`mvn spring-boot:run`

# Sign in
`POST <ip>:8080/api/auth/signin`
```
{
    "username": "user1",
    "password": "1111"
}
```

# Sign up
`POST <ip>:8080/api/auth/signup`
```
{
    "username": "user1",
    "email": "user1@mail.ru"
    "password": "1111"
}
```

# Get product
`<ip>:8080/api/product/<code>`

# Get cart
`<ip>:8080/api/cart/user/<username>`

# Update cart
`POST <ip>:8080/api/cart/user<username>`

```
{
    "id": "1",
    "name": "product1",
    "quantity": "3"
}
```