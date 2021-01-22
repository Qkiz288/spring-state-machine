[![Qkiz288](https://circleci.com/gh/Qkiz288/mssc-beer-service-kamil.svg?style=shield)](https://circleci.com/gh/Qkiz288/mssc-beer-service-kamil)

Repository created to complete Microservices course on Udemy

#### Default Port Mappings - For Single Host
| Service Name | Port | 
| --------| -----|
| Brewery Beer Service | 8080 |
| [Brewery Beer Order Service](https://github.com/Qkiz288/mssc-beer-order-service) | 8081 |
| [Brewery Beer Inventory Service](https://github.com/Qkiz288/mssc-beer-inventory-service) | 8082 |

In order to run the application with local MySQL DB:
 - MySQL service has to be installed locally.
 - When running the main class, in `Edit Configuration / VM Options` add `-Dspring.profiles.active=localmysql` profile. 