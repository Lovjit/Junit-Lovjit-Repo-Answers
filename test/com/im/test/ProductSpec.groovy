package com.im.test

import spock.lang.Specification

class ProductSpec extends Specification {


    def "Testing of Sorting of Products on the basis of Price"(){
        setup:
        List<Product> products = [new Product(price: 200),new Product(price: 450),new Product(price: 300)]
        when:
        List<Product> sortedProductsActual = Product.getSortedPricesOfAllProducts(products)
        then:
        sortedProductsActual == [200,300,450]
    }

    def "Testing Employee grouping by Price"() {
        setup:
        List<Product> products = [new Product(price: 4500),new Product(price:8500),
                                  new Product(price : 1547854)]
        when:
        Map<String,List<Product>> employeeGroupToProduct = Product.getEmployeesGroupedByPrice(products)
        then:
        employeeGroupToProduct.entrySet().size() == 3
    }

    def "Testing sorting of products by name and then salary"() {

        setup:
        List<Product> products = [new Product(name: "A",price: 100),new Product(name: "C","price":200),new Product(name: "B","price" : 300)]
        when:
        List<Product> productsSortedByNameAndSalaryActual = Product.getProductsSortedByNameAndSalary(products)
        then:

        productsSortedByNameAndSalaryActual.get(0).name == "A"
        and:
        productsSortedByNameAndSalaryActual.get(1).name == "B"
        productsSortedByNameAndSalaryActual.get(2).name == "C"



    }


}
